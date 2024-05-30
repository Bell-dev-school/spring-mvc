package com.bellitegrator.springmvc.repositories;

import com.bellitegrator.springmvc.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByCustomerIdOrderById(Integer userId);

    List<Order> findAllByDeliverymanIdOrderById(Integer userId);
}
