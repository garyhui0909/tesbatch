package com.gary.testbatch.secondary;

import java.util.List;

public interface CustomHibernateRepository<T> {
    public <S extends T> int batchInsert(List<S> entity);
    public <S extends T> int batchInsertForNoPKEntity(List<S> entity, String className);
    public <S extends T> int batchInsertJdbc(List<S> entity, String sql);
}
