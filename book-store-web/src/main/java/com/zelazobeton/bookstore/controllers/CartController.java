package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.CartObject;
import com.zelazobeton.bookstore.model.CartObjectCommand;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.ItemService;
import com.zelazobeton.bookstore.services.interfaces.ICartService;
import com.zelazobeton.bookstore.services.interfaces.IUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    private final IUserService userService;
    private final ItemService itemService;
    private final ICartService cartService;

    public CartController(IUserService userService, ItemService itemService, ICartService cartService) {
        this.userService = userService;
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String getCartView(Model model,
                                @AuthenticationPrincipal User user){
        System.out.println("@ getCartView(), user: " + user.getUsername());
        Cart cart = cartService.getCartByUser(user);
        model.addAttribute("cart", cart);
        return Templates.CART_VIEW;
    }

    @PostMapping("/addToCart")
    public String addItemToCart(Model model,
                                @ModelAttribute CartObjectCommand cartObjectCommand,
                                @AuthenticationPrincipal User user)
    {
        System.out.println("@ addItemToCart(), user: " + user.getUsername());
        Cart updatedCart = cartService.addToCartByUser(user, cartObjectCommand);
        model.addAttribute("cart", updatedCart);
        return Templates.CART_VIEW;
    }

    @PostMapping("/cart")
    public String updateCart(Model model,
                             @ModelAttribute Cart cart,
                             @AuthenticationPrincipal User user)
    {
        System.out.println("@ updateCart id: " + cart.getId());
        Cart savedCart = cartService.updateCart(user, cart);
        model.addAttribute("cart", savedCart);
        return Templates.CART_VIEW;
    }
}
