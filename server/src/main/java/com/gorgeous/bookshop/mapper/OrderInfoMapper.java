package com.gorgeous.bookshop.mapper;

import com.gorgeous.bookshop.bean.OrderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * author  haohaoxiansheng
 * date  2020/1/27 13:58
 */
@Repository
public interface OrderInfoMapper {
    int submitOrder(OrderInfo orderInfo);

    List<Map<String, Object>> loadOrders(String ownerName);

    List<Map<String, Object>> loadOrderLists(String orderListUid);

    void insertList(List<Map<String, Object>> list);

    int deleteOrder(String uid);

    void deleteOrderList(String uid);

    Map<String, Object> getOrderUid(String itemUid);

    int confirmOrder(Map<String, Object> uidMap);

    int cancelOrder(Map<String, Object> uidMap);
}
