package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.AddressCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Address extends BaseEntity {
    private String name;
    private String surname;
    private String street;
    private String number;
    private String postalCode;
    private String city;
    private boolean defaultAddress;
    @ManyToOne
    private User user;
    public Address() {}
    public Address(AddressCommand command){
        updateByAddressCommand(command);
    }

    public void updateByAddressCommand(AddressCommand command){
        this.name = command.getName();
        this.surname = command.getSurname();
        this.street = command.getStreet();
        this.number = command.getNumber();
        this.postalCode = command.getPostalCode();
        this.city = command.getCity();
        this.user = command.getUser();
        this.defaultAddress = command.isDefaultAddress();
    }
}
