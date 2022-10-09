package com.gorgeous.bookshop.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ItemInfo implements Serializable {

    private static final long serialVersionUID = -1780672091085637452L;

    private String uid;
    private String ownerName;
    private String name;
    private String description;
    private String price;
    private String image;
    private String creatAt;
    private int isPicked;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }

    public int getIsPicked() {
        return isPicked;
    }

    public void setIsPicked(int isPicked) {
        this.isPicked = isPicked;
    }
}
