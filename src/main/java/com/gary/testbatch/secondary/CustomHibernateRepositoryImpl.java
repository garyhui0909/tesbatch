package com.gary.testbatch.secondary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gary.testbatch.service.SecondaryDBService;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;


public class CustomHibernateRepositoryImpl<T> implements CustomHibernateRepository<T> {

    private static final Logger log = LoggerFactory.getLogger(CustomHibernateRepositoryImpl.class);
    @Autowired
    @Qualifier("secondaryEntityManagerFactory")
    EntityManagerFactory entityManagerFactory;

    @Override
    public <S extends T> int batchInsert(List<S> entity) {
        StatelessSession session = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
        Transaction tx = session.beginTransaction();

        entity.stream().forEach(e->session.insert(e));
        tx.commit();
        session.close();
        return entity.size();
    }

    @Override
    public <S extends T> int batchInsertForNoPKEntity(List<S> entity, String className) {

        StatelessSession session = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
        log.info("session.getJdbcBatchSize() :: " + session.getJdbcBatchSize());
        session.setJdbcBatchSize(1000);
        Transaction tx = session.beginTransaction();
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String sql = "INSERT INTO data (datetime, channel, value, c_district) VALUES(:datetime, :channel, :value, :cDistrict)";

        entity.stream().forEach( e-> {
            Map<String, Object> entityMap = oMapper.convertValue(e, Map.class);
            entityMap.remove("id");
//            log.info("entityMap :: " + entityMap);
            NativeQuery q = session.createNativeQuery(sql, e.getClass());
            for (Map.Entry<String, Object> entry : entityMap.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
            q.executeUpdate();
        });


        tx.commit();
        session.close();
        return entity.size();

    }

    @Override
    public <S extends T> int batchInsertJdbc(List<S> entity, String className) {


//        String sql = "INSERT INTO data (datetime, channel, value, c_district) VALUES(:datetime, :channel, :value, :cDistrict)";
        String sql = "INSERT INTO data (datetime, channel, value, c_district) VALUES(?, ?, ?, ?)";
        StatelessSession session = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
//        Transaction tx = session.beginTransaction();
        ObjectMapper oMapper = new ObjectMapper();
//        oMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        oMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                log.info("connection.getAutoCommit() :: " + connection.getAutoCommit());
                PreparedStatement ps = connection.prepareStatement(sql);

                entity.stream().forEach( e-> {
                    Map<String, Object> entityMap = oMapper.convertValue(e, Map.class);
                    try {
//                        int i = 1;
//                        ps.setTimestamp(i++, new Timestamp((Long) entityMap.get("datetime")));
//                        ps.setInt(i++, (Integer) entityMap.get("channel"));
//                        ps.setFloat(i++, (Float) entityMap.get("value"));
//                        ps.setString(i++, (String) entityMap.get("cDistrict"));

                        int i = 1;
                        for (Map.Entry<String, Object> entry : entityMap.entrySet()) {
                            ps.setObject(i, entry.getValue());
                            i++;
                        }

                        ps.addBatch();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                ps.executeBatch();

//                for(int i = 0; i < newObjList.size() ; i++ ){
//                    AttendanceUserDay obj = newObjList.get(i);
//                    ps.setString(1, obj.getId());
//                    ps.setString(2, obj.getUserId());
//                    ps.setDate(4, new java.sql.Date (obj.getDate().getTime()));
//                    ps.setString(6, obj.getRemark());
//                    ps.addBatch();
//                    if(i &gt; 0 &amp;&amp; i % 1000 == 0){ //每满1000条执行插入一次
//                        System.out.println(&#34;executeBatch : &#34;&#43; i);
//                        ps.executeBatch();
//                        ps.clearBatch();
//
//                    }
//                }
//                ps.executeBatch();
            }
        });

//        tx.commit();
        session.close();

        return 0;
    }
}
