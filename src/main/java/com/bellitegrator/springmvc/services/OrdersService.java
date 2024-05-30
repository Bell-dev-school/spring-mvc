package com.bellitegrator.springmvc.services;

import com.bellitegrator.springmvc.forms.OrderForm;
import com.bellitegrator.springmvc.models.Order;
import com.bellitegrator.springmvc.models.OrderPoint;
import com.bellitegrator.springmvc.models.User;

import java.util.LinkedHashMap;
import java.util.List;

public interface OrdersService {

    List<Order> getOrdersByCustomer(Integer userId);

    List<Order> getOrdersByDeliveryman(Integer userId);

    void addOrderToDeliveryman(User deliveryman, Integer orderId);

    Order addNewOrder(User customer, User deliveryman, OrderForm orderForm);

    void addOrderPointsToOrder(Order order, List<OrderPoint> orderPoints);

    LinkedHashMap<Order, List<OrderPoint>> getMapOfOrderPoints(List<Order> ordersOfCustomer);

    Order getOrderById(Integer orderId);

    void update(Order order);
}
