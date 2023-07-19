package com.tuc.hk.data.mergedb.repository;

import java.util.List;

public interface CustomHibernateRepository<T> {
  public <S extends T> int batchInsert(List<S> entity);
//  public <S extends T> int batchInsertForNoPKEntity(List<S> entity, String className);
  public <S extends T> int batchInsertForNoPK(List<S> entity) throws Exception;
  public <T> int truncateTable(Class<T> cls);
}
