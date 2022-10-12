package com.gorgeous.bookshop.controller;

import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderSale")
@Api(tags = "卖书接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public RespData submitOrder(@RequestBody @Valid PowerJSON map){
        return orderService.submit(map);
    }

}
