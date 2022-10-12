package com.gorgeous.bookshop.controller;

import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author  haohaoxiansheng
 * date  2020/1/27 13:54
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;

    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public RespData submitOrder(@RequestBody Map<String,Object> map){
        return orderInfoService.submitOrder(map);
    }

    @RequestMapping(value = "/loadOrders/{ownerName}", method = RequestMethod.GET)
    public List<Map<String, Object>> loadOrders(@PathVariable String ownerName){
        return orderInfoService.loadOrders(ownerName);
    }

    @RequestMapping(value = "/deleteOrder/{uid}", method = RequestMethod.DELETE)
    public RespData deleteOrder(@PathVariable String uid) {
        return orderInfoService.deleteOrder(uid);
    }

    @RequestMapping(value = "/getOrderUid/{itemUid}", method = RequestMethod.GET)
    public Map<String, Object> getOrderUid(@PathVariable String itemUid){
        return orderInfoService.getOrderUid(itemUid);
    }

    @RequestMapping(value = "/confirmOrder", method = RequestMethod.PUT)
    public RespData confirmOrder(@RequestBody Map<String,Object> uidMap){
        return orderInfoService.confirmOrder(uidMap);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.PUT)
    public RespData cancelOrder(@RequestBody Map<String,Object> uidMap){
        return orderInfoService.cancelOrder(uidMap);
    }
}
