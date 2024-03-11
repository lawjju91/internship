package com.test3.dfs_3.repository;

import com.test3.dfs_3.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {
    // Add custom query methods if needed
}