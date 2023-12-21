package com.example.pos_system.bo.custom;

import com.example.pos_system.bo.SuperBO;
import com.example.pos_system.dto.ItemDTO;
import com.example.pos_system.dto.OrderDTO;

import java.util.List;

public interface OrderBO extends SuperBO {
    public boolean addOrder(OrderDTO orderDTO) throws Exception;
    public OrderDTO searchOrder(String id) throws Exception;
    public List<OrderDTO> getAllOrders() throws Exception;

}
