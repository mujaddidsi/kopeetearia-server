package com.accenture.ws.impl;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;

import java.util.List;

public class DiscountedBill extends OrderBill{

    public DiscountedBill() {}

    public DiscountedBill(CafeClerk clerk) {
        super(clerk);
    }

    @Override
    public double getTotalBill(List<Order> listOrder) {
        double totalOrder = 0;
        for (int i = 0; i < listOrder.size(); i++ ) {
            if (listOrder.get(i).isDiscounted() == true) {
                double discountAmount = listOrder.get(i).getPrice() * 0.05;
                totalOrder += listOrder.get(i).getPrice() - discountAmount;
            } else {
                totalOrder+= listOrder.get(i).getPrice();
            }
        }
        return totalOrder;
    }
}
