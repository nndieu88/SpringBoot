package com.smartosc.mobile.repository;

import com.smartosc.mobile.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    public Page<Orders> findAll(Pageable pageable);
}
