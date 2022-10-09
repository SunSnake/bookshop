package com.gorgeous.bookshop.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * author  haohaoxiansheng
 * date  2020/1/27 13:49
 */
@Component
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = -4293070646174744636L;

    private String uid;
    private String ownerName;
    private String name;
    private String phone;
    private String address;
    private String sum;
    private String creatAt;
    private String orderListUid;
    private String orderNumber;
    private String orderState;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }

    public String getOrderListUid() {
        return orderListUid;
    }

    public void setOrderListUid(String orderListUid) {
        this.orderListUid = orderListUid;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
