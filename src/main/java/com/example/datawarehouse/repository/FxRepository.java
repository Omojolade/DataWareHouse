package com.example.datawarehouse.repository;

import com.example.datawarehouse.domain.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FxRepository extends JpaRepository<FxDeal, Long> {
}
