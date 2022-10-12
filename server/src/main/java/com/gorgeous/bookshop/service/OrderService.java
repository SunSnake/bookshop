package com.gorgeous.bookshop.service;

import com.gorgeous.bookshop.constant.BookConstant;
import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.mapper.BookMapper;
import com.gorgeous.bookshop.mapper.OrderSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private OrderSaleMapper orderSaleMapper;

    @Transactional
    public RespData submit(PowerJSON map) {
        String orderType = map.getString("orderType");
        if (BookConstant.ORDER_SALE.equals(orderType)) {
            int i = orderSaleMapper.submit(map);
        } else {

        }

        return RespData.success(String.valueOf("i"));
    }
}
