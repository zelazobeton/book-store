package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.Address;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.model.UserOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AddressCommand{
    private Long id;
    @NotBlank(message = "Please provide recipient's name")
    private String name;
    @NotBlank(message = "Please provide recipient's surname")
    private String surname;
    @NotBlank(message = "Please provide name of the street")
    private String street;
    @Pattern(regexp = "^[0-9]*[\\/]?[0-9]*$", message = "Please provide number in format XX or X/Y")
    private String number;
    @Pattern(regexp = "^[0-9]{2}[-][0-9]{3}$", message = "Please provide postal code in format XX-XXX")
    private String postalCode;
    @NotBlank(message = "Please provide city name")
    private String city;
    private UserOrder userOrder;
    private User user;
    private boolean savedByUser;

    public AddressCommand() {}
    public AddressCommand(User user) {this.user = user;}
    public AddressCommand(Address address){
        this.id = address.getId();
        this.name = address.getName();
        this.surname = address.getSurname();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.postalCode = address.getPostalCode();
        this.city = address.getCity();
        this.user = address.getUser();
        this.savedByUser = false;
    }
}
