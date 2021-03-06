package com.example.webpos.biz;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImp implements OrderService {

    @Autowired
    private StreamBridge streamBridge;

    private int idCounter = 0;


    @Override
    synchronized public void generateOrder(Cart cart) {
        streamBridge.send("order-service", new Order(cart, idCounter++));
    }
}
