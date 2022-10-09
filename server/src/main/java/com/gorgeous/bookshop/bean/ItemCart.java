package com.gorgeous.bookshop.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * author  Gorgeous
 * date  2022/10/08 15:33
 */
@Component
public class ItemCart implements Serializable {

    private static final long serialVersionUID = -2392598828882253163L;

    private int id;
    private String itemUid;
    private String buyerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemUid() {
        return itemUid;
    }

    public void setItemUid(String itemUid) {
        this.itemUid = itemUid;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
