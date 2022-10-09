package com.gorgeous.bookshop.mapper;

import com.gorgeous.bookshop.bean.ItemCart;
import com.gorgeous.bookshop.bean.ItemInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author  Gorgeous
 * date  2022/10/08 15:33
 */
@Repository
public interface ShoppingCarMapper {

    int addToCart(ItemCart itemCart);

    List<ItemInfo> loadCart(String buyerName);

    int deleteCartItem(ItemCart itemCart);

    int qryCartCount();

}
