package com.gorgeous.bookshop.mapper;

import com.gorgeous.bookshop.bean.ItemInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {

    List<ItemInfo> loadItems();

    int submitItem(ItemInfo itemInfo);

    int updateProduct(ItemInfo itemInfo);

    int deleteProductUnit(String uid);

    void updateState(String itemUid);

    Map<String, Object> qryItemState(String itemUid);
}
