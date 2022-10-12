package com.gorgeous.bookshop.mapper;

import com.gorgeous.bookshop.constant.PowerJSON;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSaleMapper {

    @Insert(
            "INSERT INTO order_sale " +
            "(book_Ids, book_uniqueIds, small_num, big_num, ownerId, price) " +
            "VALUES " +
            "(#{book_Ids}, #{book_uniqueIds}, #{small_num}, #{big_num}, #{ownerId}, #{price})"
    )
    int submit(PowerJSON orderInfo);

}
