package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.commands.OrderCommand;
import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.model.UserOrder;

import java.util.List;

public interface IOrderService {
    UserOrder placeNewOrder(OrderCommand command, User user);
    OrderCommand createOrderCommand(Cart cart);
    List<UserOrder> getAllOrders();
    UserOrder getOrderById(Long id);
}
