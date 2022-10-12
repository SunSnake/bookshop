package com.gorgeous.bookshop.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gorgeous.bookshop.bean.ItemInfo;
import com.gorgeous.bookshop.constant.PowerJSON;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper extends BaseMapper<PowerJSON> {

    Page<PowerJSON> loadItems(Page<PowerJSON> page, QueryWrapper<List<PowerJSON>> wrapper);

    int submitItem(ItemInfo itemInfo);

    int updateProduct(ItemInfo itemInfo);

    int deleteProductUnit(String uid);

    void updateState(String itemUid);

    Map<String, Object> qryItemState(String itemUid);
}
