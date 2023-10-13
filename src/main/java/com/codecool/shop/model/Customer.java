package com.codecool.shop.model;

public class Customer {
    private int id;
    private final String eMail;
    private String hashedPassword;

    private byte[] salt;
    private String phoneNumber;
    private Cart cart;
    private String billingAddress;
    private String shippingAddress;


    public Customer(String eMail, String phoneNumber, String billingAddress, String shippingAddress) {
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;

    }


    public Customer(String eMail, String hashedPassword, byte[] salt) {
        this.eMail = eMail;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
