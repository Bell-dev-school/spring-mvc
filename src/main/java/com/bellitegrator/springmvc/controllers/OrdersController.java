package com.bellitegrator.springmvc.controllers;

import com.bellitegrator.springmvc.forms.OrderForm;
import com.bellitegrator.springmvc.forms.OrderPointForm;
import com.bellitegrator.springmvc.models.Order;
import com.bellitegrator.springmvc.models.OrderPoint;
import com.bellitegrator.springmvc.models.User;
import com.bellitegrator.springmvc.services.OrdersService;
import com.bellitegrator.springmvc.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class OrdersController {

    private final UsersService usersService;
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(UsersService usersService, OrdersService ordersService) {
        this.usersService = usersService;
        this.ordersService = ordersService;
    }

    @GetMapping("/new_order")
    public String getOrderCreatePage(Model model) {
        OrderPoint orderPoint0 = new OrderPoint();
        OrderPoint orderPoint1 = new OrderPoint();
        List<OrderPoint> orderPoints = Arrays.asList(orderPoint0, orderPoint1);
        OrderPointForm form = new OrderPointForm();
        form.setOrderPoints(orderPoints);
        model.addAttribute("orderPointForm", form);
        return "new_order";
    }

    @PostMapping("/new_order")
    public String addNewOrder(OrderPointForm orderPointForm,
                              OrderForm orderForm,
                              BindingResult result,
                              RedirectAttributes forRedirectModel)  {
        if (result.hasErrors()) {
            forRedirectModel.addFlashAttribute("errors", "Что-то введено нерпавильно!");
            return "redirect:/new_order";
        }

        User customer = usersService.getAuthenticatedUser();
        User defaultDeliveryman = usersService.getUser(1);
        Order order = ordersService.addNewOrder(customer, defaultDeliveryman, orderForm);
        List<OrderPoint> orderPoints = new ArrayList<>();
        orderPoints.add(orderPointForm.getOrderPoint0());
        orderPoints.add(orderPointForm.getOrderPoint1());
        ordersService.addOrderPointsToOrder(order, orderPoints);
        return "redirect:/c_orders";
    }

    @GetMapping("/c_orders")
    public String getOrdersByCustomer(Model model) {
        Integer userId = usersService.getAuthenticatedUser().getId();
        List<Order> ordersOfCustomer = ordersService.getOrdersByCustomer(userId);
        LinkedHashMap<Order, List<OrderPoint>> mapOfCustomerOrders = ordersService.getMapOfOrderPoints(ordersOfCustomer);
        model.addAttribute("mapOfCustomerOrders", mapOfCustomerOrders);
        return "orders_of_customer";
    }

    @PostMapping("c_orders")
    public String addNewOrderPoint(OrderPointForm orderPointForm,
                                   @RequestParam("orderId") Integer orderId,
                                   @RequestParam("newPrice") Integer newPrice,
                                   BindingResult result,
                                   RedirectAttributes forRedirectModel)  {
        if (result.hasErrors()) {
            forRedirectModel.addFlashAttribute("errors", "Что-то введено нерпавильно!");
            return "redirect:/c_orders";
        }
        Order order = ordersService.getOrderById(orderId);
        List<OrderPoint> onePointList = Collections.singletonList(orderPointForm.getOrderPoint0());
        ordersService.addOrderPointsToOrder(order, onePointList);
        order.setPrice(newPrice);
        ordersService.update(order);
        return "redirect:/c_orders";
    }


    @GetMapping("/d_orders")
    public String getOrdersByDeliveryman(Model model) {
        Integer userId = usersService.getAuthenticatedUser().getId();
        List<Order> ordersOfDeliveryman = ordersService.getOrdersByDeliveryman(userId);
        LinkedHashMap<Order, List<OrderPoint>> mapOfDeliverymanOrders = ordersService.getMapOfOrderPoints(ordersOfDeliveryman);
        model.addAttribute("mapOfOrders", mapOfDeliverymanOrders);
        return "orders_of_deliveryman";
    }

    @GetMapping("/free_orders")
    public String getFreeOrders(Model model) {
        List<Order> freeOrders = ordersService.getOrdersByDeliveryman(1);
        LinkedHashMap<Order, List<OrderPoint>> mapOfFreeOrders = ordersService.getMapOfOrderPoints(freeOrders);
        model.addAttribute("mapOfFreeOrders", mapOfFreeOrders);
        return "free_orders";
    }

    @PostMapping("/free_orders")
    public String addOrderToDeliveryman(@RequestParam("takenOrderId") Integer takenOrderId) {
        User deliveryman = usersService.getAuthenticatedUser();
        ordersService.addOrderToDeliveryman(deliveryman, takenOrderId);
        return "redirect:/d_orders";
    }
}
