package com.tuc.hk.data.mergedb.repository;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.community.dialect.InformixDialect;
import org.hibernate.query.NativeQuery;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import jakarta.persistence.Column;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tuc.hk.productassembly.service.MergeDBService;

@Slf4j
public class CustomHibernateRepositoryImpl<T> implements CustomHibernateRepository<T>{

	@Autowired
	@Qualifier("mergedbEntityManagerFactory")
	EntityManagerFactory entityManagerFactory;

	@Override
	public <S extends T> int batchInsert(List<S> entity) {

		StatelessSession session = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
		Transaction tx = session.beginTransaction();

		entity.stream().forEach(e -> {
			session.insert(e);
		});
		tx.commit();
		session.close();

		return entity.size();
	}
  
//	@Override
//	public <S extends T> int batchInsertForNoPKEntity(List<S> entity, String className) {
//
//		StatelessSession session = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
//		Transaction tx = session.beginTransaction();
//		ObjectMapper oMapper = new ObjectMapper();
//		oMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//
//		String sql = NativeSqlForNoPKEntity.getSqlForNoPKEntity(className);
//
//		entity.stream().forEach(e -> {
//			Map<String, Object> entityMap = oMapper.convertValue(e, Map.class);
//			entityMap.remove("rowId");
//			NativeQuery q = session.createNativeQuery(sql, e.getClass());
//			for (Entry<String, Object> entry : entityMap.entrySet()) {
//				q.setParameter(entry.getKey(), entry.getValue());
//			}
//			q.executeUpdate();
//		});
//
//		tx.commit();
//		session.close();
//
//		return entity.size();
//	}
  
	@Override
	public <S extends T> int batchInsertForNoPK(List<S> entity) throws Exception {
		SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class);
		StatelessSession session = sf.openStatelessSession();
		Transaction tx = session.beginTransaction();

		session.doWork(work -> {
			Statement statement = work.createStatement();
			String insertSql = "";
			try {
				for (S obj : entity) {
					Insert insert = new Insert(new InformixDialect());
					Table table = obj.getClass().getAnnotation(Table.class);

					if (obj.getClass().getSuperclass() != null) {
						prepareInsertObj(insert, obj.getClass().getSuperclass(), obj);
					}

					prepareInsertObj(insert, obj.getClass(), obj);
					insert.setTableName(table.name());

					insertSql = insert.toStatementString();
					statement.addBatch(insertSql);
				}
			} catch (Exception e) {
//				e.printStackTrace();
				log.info("Insert failed on batchInsertForNoPK 1, error message: {}, insertSql: {} ", e, insertSql);
			} finally {

				if (statement != null) {
					try {
						statement.executeBatch();
					} catch (Exception e) {
						log.info("Insert failed on batchInsertForNoPK 2, error message: {}, insertSql: {} ", e, insertSql);
						throw e;
					}
					work.commit();
					work.close();
				}
			}

		});

		session.close();

		return entity.size();
	}
  
	private <S extends T> void prepareInsertObj(Insert insertObj, Class<?> cls, S entity) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (Field field : cls.getDeclaredFields()) {
			boolean isColumnField = false;
			String columnName = null;

			if (field.isAnnotationPresent(Column.class)) {
				isColumnField = true;
				Column column = field.getDeclaredAnnotation(Column.class);

				try {
					if (StringUtils.isEmpty(column.name())) {
						throw new Exception("column.name is null :" + field.getName());
					}

					columnName = column.name();

				} catch (Exception e) {
					throw new Exception(field.getName() + " got problem", e);
				}

			} else if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
				isColumnField = true;
				columnName = field.getName();
			}

			if (isColumnField) {
				field.setAccessible(true);
				String value = null;

				if (field.get(entity) == null) {
				} else if (field.getType().isAssignableFrom(Integer.class)) {
					value = String.valueOf(((Integer) field.get(entity)));
				} else if (field.getType().isAssignableFrom(String.class)) {
					String fieldStr = (String) field.get(entity);
					fieldStr = fieldStr.replace("'","''");
					value = "'" + fieldStr + "'";
				} else if (field.getType().isAssignableFrom(Date.class)) {
					Date date = (Date) field.get(entity);
					String dateStr = dateFormat.format(date);
					value = "TO_DATE(\"" + dateStr + "\", \"%Y-%m-%d %H:%M:%S\")";
				}
				// Skip column ROWID
				if (!columnName.equals("ROWID")) {
					insertObj.addColumn(columnName, value);
				}
			}
		}
	}
	
	@Override
	public <T> int truncateTable(Class<T> cls) {

		StatelessSession session = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
		Transaction tx = session.beginTransaction();
		
		Table table = cls.getAnnotation(Table.class);
		String tableName = table.name();

		String sql = "TRUNCATE TABLE " + tableName;
		NativeQuery q = session.createNativeQuery(sql, cls);
		int deleteCnt = q.executeUpdate();

		tx.commit();
		session.close();
		return deleteCnt;
	}


}
