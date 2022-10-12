package com.gorgeous.bookshop.service;

import com.gorgeous.bookshop.bean.OrderInfo;
import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.mapper.OrderInfoMapper;
import com.gorgeous.bookshop.utils.DateUtil;
import com.gorgeous.bookshop.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author  haohaoxiansheng
 * date  2020/1/27 13:57
 */
@Service
public class OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;
    
    @Autowired
    OrderInfo orderInfo;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    public RespData submitOrder(Map<String,Object> map){
        PowerJSON jsonObject = new PowerJSON(map);

        orderInfo.setUid(UuidUtil.getUUID());
        orderInfo.setOwnerName(jsonObject.getString("ownerName"));
        orderInfo.setName(jsonObject.getString("name"));
        orderInfo.setPhone(jsonObject.getString("phone"));
        orderInfo.setAddress(jsonObject.getString("address"));
        orderInfo.setSum(jsonObject.getString("sum"));
        orderInfo.setCreatAt(DateUtil.getCurrentTime());
        orderInfo.setOrderState("未受理");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        int randomNum = (int)((Math.random()*9+1)*100000);
        String dateTime = dateFormat.format(new Date());
        orderInfo.setOrderNumber(dateTime + randomNum);

        String orderListUid = UuidUtil.getUUID();
        orderInfo.setOrderListUid(orderListUid);
        List<String> uidList = (List)jsonObject.get("cartItems");
        List<String> stateList = new ArrayList<>();
        List<Map<String,Object>> orderList = new ArrayList<>();

        for (String itemUid : uidList){
            Map<String, Object> itemMap = productService.qryItemState(itemUid);
            if (itemMap.get("isPicked") != null && (int)itemMap.get("isPicked") != 0){
                return RespData.success("下单失败，商品  " + itemMap.get("itemName") + "  已被抢占，请重新下单！", itemMap.get("itemName"));
            }

            Map<String,Object> map1 = new HashMap<>();
            map1.put("uid", orderListUid);
            map1.put("itemUid", itemUid);

            stateList.add(itemUid);
            orderList.add(map1);
        }
        productService.updateState(stateList);
        orderInfoMapper.insertList(orderList);

        if (orderInfoMapper.submitOrder(orderInfo) == 1){
            //删除购物车中对应商品
            for (String uid : uidList){
                Map<String,Object> map1 = new HashMap<>();
                map1.put("itemUid", uid);
                map1.put("buyerName", jsonObject.getString("ownerName"));
                cartService.deleteCartItem(map1);
            }
            return RespData.success("下单成功！");
        } else {
            return RespData.error("下单失败");
        }
    }

    public List<Map<String, Object>> loadOrders(String ownerName) {
        List<Map<String, Object>> order = orderInfoMapper.loadOrders(ownerName);
        for (Map map : order){
            List<Map<String, Object>> orderUids = orderInfoMapper.loadOrderLists((String)map.get("orderListUid"));
            map.put("orderUids", orderUids);
        }
        return order;
    }

    public RespData deleteOrder(String uid){
        orderInfoMapper.deleteOrderList(uid);
        if (orderInfoMapper.deleteOrder(uid) == 1){
            return RespData.success("删除成功！");
        } else {
            return RespData.error("删除失败");
        }
    }

    public Map<String, Object> getOrderUid(String itemUid) {
        return orderInfoMapper.getOrderUid(itemUid);
    }

    public RespData confirmOrder(Map<String, Object> uidMap){
        if (orderInfoMapper.confirmOrder(uidMap) == 1){
            return RespData.success("接受订单成功！");
        } else {
            return RespData.error("确认订单失败");
        }
    }

    public RespData cancelOrder(Map<String, Object> uidMap){
        if (orderInfoMapper.cancelOrder(uidMap) == 1){
            return RespData.success("取消订单成功！");
        } else {
            return RespData.error("取消订单失败");
        }
    }
}
