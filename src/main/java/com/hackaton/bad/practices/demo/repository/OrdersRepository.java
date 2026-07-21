package com.hackaton.bad.practices.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.bad.practices.demo.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long idUser);

}
