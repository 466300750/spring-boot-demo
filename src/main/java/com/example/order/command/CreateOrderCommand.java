package com.example.order.command;

import java.util.Map;

public class CreateOrderCommand {
    private String address;
    private Map<Long, Integer> items;

    public String getAddress() {
        return address;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public CreateOrderCommand(String address, Map<Long, Integer> items) {
        this.address = address;
        this.items = items;
    }

    public CreateOrderCommand() {
    }
}
