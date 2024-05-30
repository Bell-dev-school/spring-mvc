package com.bellitegrator.springmvc.services;

import com.bellitegrator.springmvc.forms.OrderForm;
import com.bellitegrator.springmvc.models.Order;
import com.bellitegrator.springmvc.models.OrderPoint;
import com.bellitegrator.springmvc.models.User;
import com.bellitegrator.springmvc.repositories.OrderPointsRepository;
import com.bellitegrator.springmvc.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrdersServiceImpl implements OrdersService {

    private static final Logger LOGGER = LoggerFactory.getLogger("OrdersServiceImpl");

    private final OrdersRepository ordersRepository;
    private final OrderPointsRepository orderPointsRepository;


    @Override
    public List<Order> getOrdersByCustomer(Integer userId) {
        LOGGER.info("Получаем список заказов заказчика " + userId);
        return ordersRepository.findAllByCustomerIdOrderById(userId);
    }

    @Override
    public List<Order> getOrdersByDeliveryman(Integer userId) {
        LOGGER.info("Получаем список заказов курьера " + userId);
        return ordersRepository.findAllByDeliverymanIdOrderById(userId);
    }

    @Override
    public void addOrderToDeliveryman(User deliveryman, Integer orderId) {
        LOGGER.info("Получили курьера " + deliveryman.getId() + " и id заказа " + orderId);
        Order order = ordersRepository.getReferenceById(orderId);
        order.setDeliveryman(deliveryman);
        LOGGER.info("Записали курьера в заказ");
        ordersRepository.save(order);
        LOGGER.info("Сохранили курьера в репозиторий");
    }

    @Override
    public Order addNewOrder(User customer, User deliveryman, OrderForm orderForm) {
        LOGGER.info("Получили покупателя " + customer.getId() + ", курьера " + deliveryman.getId() + " и информацию по заказу");
        Order order  = Order.builder()
                .customer(customer)
                .deliveryman(deliveryman)
                .weight(orderForm.getWeight())
                .price(orderForm.getPrice())
                .build();
        LOGGER.info("Создали заказ");
        ordersRepository.save(order);
        LOGGER.info("Сохранили заказ в репозиторий");
        return order;
    }

    @Override
    public void addOrderPointsToOrder(Order order, List<OrderPoint> orderPoints) {
        LOGGER.info("Получили заказ " + order.getId() + " и его адреса");
        for (OrderPoint point : orderPoints) {
            OrderPoint orderPoint = OrderPoint.builder()
                    .order(order)
                    .numberOfPoint(point.getNumberOfPoint())
                    .nameOfClient(point.getNameOfClient())
                    .telNumber(point.getTelNumber())
                    .address(point.getAddress())
                    .description(point.getDescription())
                    .build();
            LOGGER.info("Создали список адресов(orderpoints)");
            orderPointsRepository.save(orderPoint);
            LOGGER.info("Сохранили адреса(orderpoints) в репозиторий");
        }
    }

    @Override
    public LinkedHashMap<Order, List<OrderPoint>> getMapOfOrderPoints(List<Order> ordersOfCustomer) {
        LOGGER.info("Создаем карту (order) -> (orderpoints)");
        return ordersOfCustomer.stream().map(Order::getId)
                .collect(Collectors.toMap(ordersRepository::getReferenceById,
                        orderPointsRepository::findAllByOrderIdOrderByNumberOfPoint,
                        (o1, o2) -> o1, LinkedHashMap::new));
    }

    @Override
    public Order getOrderById(Integer orderId) {
        LOGGER.info("Получаем заказ по id = " + orderId);
        return ordersRepository.getReferenceById(orderId);
    }

    @Override
    public void update(Order order) {
        ordersRepository.save(order);
        LOGGER.info("Обновили заказ " + order.getId());
    }

}
