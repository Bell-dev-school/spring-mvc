package com.bellitegrator.springmvc.forms;

import com.bellitegrator.springmvc.models.OrderPoint;
import lombok.Data;

import java.util.List;

@Data
public class OrderPointForm {

    private List<OrderPoint> orderPoints;

    private OrderPoint orderPoint0;
    private OrderPoint orderPoint1;

}
