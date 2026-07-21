package com.hackaton.bad.practices.demo.dtos;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private Long idUser;

    private String orderName; 

    private String item;

    private Integer amount;

    public OrderDTO() {
    }

    public OrderDTO(Long idUser, String orderName, String item, Integer amount) {
        this.idUser = idUser;
        this.orderName = orderName;
        this.item = item;
        this.amount = amount;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    } 



}
