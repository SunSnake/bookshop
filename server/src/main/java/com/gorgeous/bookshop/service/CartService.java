package com.gorgeous.bookshop.service;

import com.gorgeous.bookshop.bean.ItemCart;
import com.gorgeous.bookshop.bean.ItemInfo;
import com.gorgeous.bookshop.mapper.ShoppingCarMapper;
import com.gorgeous.bookshop.utils.PowerJSON;
import com.gorgeous.bookshop.utils.RespData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * author  Gorgeous
 * date  2022/10/08 15:33
 */
@Service
public class CartService {

    @Autowired
    ShoppingCarMapper shoppingCarMapper;

    @Autowired
    ItemCart itemCart;

    public RespData addToCart(Map<String,Object> map){
        PowerJSON jsonObject = new PowerJSON(map);

        itemCart.setItemUid(jsonObject.getString("itemUid"));
        itemCart.setBuyerName(jsonObject.getString("buyerName"));

        if (shoppingCarMapper.addToCart(itemCart) == 1){
            return RespData.success("添加购物车成功！");
        } else {
            return RespData.error("添加购物车失败");
        }
    }

    public List<ItemInfo> loadCart(String buyerName) {
        return shoppingCarMapper.loadCart(buyerName);
    }

    public RespData deleteCartItem(Map<String,Object> map){
        PowerJSON jsonObject = new PowerJSON(map);

        itemCart.setItemUid(jsonObject.getString("itemUid"));
        itemCart.setBuyerName(jsonObject.getString("buyerName"));

        if (shoppingCarMapper.deleteCartItem(itemCart) == 1){
            return RespData.success("删除商品成功！");
        } else {
            return RespData.error("删除商品失败");
        }
    }

    public int qryCartCount(String buyerName) {
        return shoppingCarMapper.loadCart(buyerName).size();
    }
}
