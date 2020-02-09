package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.CartCommand;
import com.zelazobeton.bookstore.commands.OrderCommand;
import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.UserOrder;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.interfaces.ICartService;
import com.zelazobeton.bookstore.services.interfaces.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {
    private final IOrderService orderService;
    private final ICartService cartService;

    public OrderController(IOrderService orderService, ICartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping("/prepareOrder")
    public String showOrderForm(Model model,
                                @Valid @ModelAttribute("cart") CartCommand cartCommand,
                                final BindingResult result,
                                @AuthenticationPrincipal User user)
    {
        System.out.println("@ showOrderForm id: " + cartCommand.getId());
        if(result.hasErrors())
        {
            result.getAllErrors().forEach(System.out::println);
            return Templates.ORDER_VIEW;
        }
        Cart savedCart = cartService.updateCart(user, cartCommand);
        model.addAttribute("orderCommand", new OrderCommand(savedCart));
        model.addAttribute("user", user);
        return Templates.ORDER_VIEW;
    }

    @PostMapping("/addOrder")
    public String addOrder(Model model,
                            @ModelAttribute OrderCommand orderCommand,
                            BindingResult result,
                            @AuthenticationPrincipal User user)
    {
        System.out.println("@ addOrder(), user: " + user.getUsername());
        if(result.hasErrors())
        {
            result.getAllErrors().forEach(System.out::println);
            return Templates.ORDER_VIEW;
        }
        Cart oldCart = cartService.getCartByUser(user);
        UserOrder savedUserOrder = orderService.saveOrderByCommand(orderCommand, user);
        model.addAttribute("orderId", savedUserOrder.getId());
        model.addAttribute("user", user);
        return Templates.ORDER_CONFIRMATION;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
