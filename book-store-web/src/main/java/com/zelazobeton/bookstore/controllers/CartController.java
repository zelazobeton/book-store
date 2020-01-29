package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.commands.CartCommand;
import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.ItemService;
import com.zelazobeton.bookstore.services.interfaces.ICartService;
import com.zelazobeton.bookstore.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping({"/cart", "/addToCart"})
    public String getCartView(Model model,
                                @AuthenticationPrincipal User user){
        System.out.println("@ getCartView(), user: " + user.getUsername());
        Cart cart = cartService.getCartByUser(user);
        model.addAttribute("cart", new CartCommand(cart));
        return Templates.CART_VIEW;
    }

    @PostMapping("/addToCart")
    public String addItemToCart(Model model,
                                @Valid @ModelAttribute CartItemCommand cartItemCommand,
                                final BindingResult result,
                                RedirectAttributes attr,
                                @AuthenticationPrincipal User user)
    {
        System.out.println("@ addItemToCart(), user: " + user.getUsername());
        if(result.hasErrors())
        {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.cartItemCommand", result);
            attr.addFlashAttribute("cartItemCommand", cartItemCommand);
            return "redirect:/item/" + cartItemCommand.getItem().getId();
        }
        Cart updatedCart = cartService.addToCartByUser(user, cartItemCommand);
        model.addAttribute("cart", new CartCommand(updatedCart));
        return Templates.CART_VIEW;
    }

    @PostMapping("/cart")
    public String updateCart(Model model,
                             @Valid @ModelAttribute("cart") CartCommand cartCommand,
                             final BindingResult result,
                             @AuthenticationPrincipal User user)
    {
        System.out.println("@ updateCart id: " + cartCommand.getId());
        if(result.hasErrors())
        {
            result.getAllErrors().forEach(System.out::println);
            model.addAttribute("cart", cartCommand);
            return Templates.CART_VIEW;
        }
        Cart savedCart = cartService.updateCart(user, cartCommand);
        model.addAttribute("cart", new CartCommand(savedCart));
        return Templates.CART_VIEW;
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
