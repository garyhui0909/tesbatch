package com.gary.testbatch.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MergeDataRepository extends JpaRepository<MergeData, Long>, CustomHibernateRepository<MergeData> {

}
