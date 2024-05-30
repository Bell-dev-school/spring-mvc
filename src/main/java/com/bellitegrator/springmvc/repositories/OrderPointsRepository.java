package com.bellitegrator.springmvc.repositories;

import com.bellitegrator.springmvc.models.OrderPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderPointsRepository extends JpaRepository<OrderPoint, Integer> {

    List<OrderPoint> findAllByOrderIdOrderByNumberOfPoint(Integer orderId);
}
