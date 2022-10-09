package com.gorgeous.bookshop.controller;

import com.gorgeous.bookshop.bean.ItemInfo;
import com.gorgeous.bookshop.service.CartService;
import com.gorgeous.bookshop.utils.RespData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author  Gorgeous
 * date  2022/10/08 15:33
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public RespData addToCart(@RequestBody Map<String,Object> map){
        return cartService.addToCart(map);
    }

    @RequestMapping(value = "/loadCart/{buyerName}", method = RequestMethod.GET)
    public List<ItemInfo> loadCart(@PathVariable String buyerName){
        return cartService.loadCart(buyerName);
    }

    @RequestMapping(value = "/deleteCartItem", method = RequestMethod.POST)
    public RespData deleteCartItem(@RequestBody Map<String,Object> map){
        return cartService.deleteCartItem(map);
    }

    @RequestMapping(value = "/qryCartCount/{buyerName}", method = RequestMethod.GET)
    public int qryCartCount(@PathVariable String buyerName){
        return cartService.qryCartCount(buyerName);
    }
}
