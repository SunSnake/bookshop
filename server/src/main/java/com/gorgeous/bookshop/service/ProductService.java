package com.gorgeous.bookshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gorgeous.bookshop.bean.ItemInfo;
import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.mapper.ProductMapper;
import com.gorgeous.bookshop.utils.DateUtil;
import com.gorgeous.bookshop.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ItemInfo itemInfo;

    public RespData loadItems(String current, String pageSize) {
        Page<PowerJSON> page = new Page<>(Integer.parseInt(current), Integer.parseInt(pageSize));
        Page<PowerJSON> result = productMapper.loadItems(page, null);
        return RespData.success("加载成功", result);
    }

    public RespData submitItem(Map<String,Object> map){
        PowerJSON jsonObject = new PowerJSON(map);

        itemInfo.setUid(UuidUtil.getUUID());
        itemInfo.setOwnerName(jsonObject.getString("ownerName"));
        itemInfo.setName(jsonObject.getString("name"));
        itemInfo.setDescription(jsonObject.getString("description"));
        itemInfo.setPrice(jsonObject.getString("price"));
        itemInfo.setImage(jsonObject.getString("imageBase"));
        itemInfo.setCreatAt(DateUtil.getCurrentTime());

        if (productMapper.submitItem(itemInfo) == 1){
            return RespData.success("发布商品成功！");
        } else {
            return RespData.error("发布失败");
        }
    }

    public RespData updateProduct(Map<String,Object> map){
        PowerJSON jsonObject = new PowerJSON(map);

        itemInfo.setUid(jsonObject.getString("uid"));
        itemInfo.setName(jsonObject.getString("name"));
        itemInfo.setDescription(jsonObject.getString("description"));
        itemInfo.setPrice(jsonObject.getString("price"));
        itemInfo.setImage(jsonObject.getString("image"));

        if (productMapper.updateProduct(itemInfo) == 1){
            return RespData.success("更新信息成功！");
        } else {
            return RespData.error("更新信息失败");
        }
    }

    public RespData deleteProductUnit(String uid){
        if (productMapper.deleteProductUnit(uid) == 1){
            return RespData.success("删除成功！");
        } else {
            return RespData.error("删除失败");
        }
    }

    public void updateState(List<String> stateList){
        for(String itemUid : stateList){
            productMapper.updateState(itemUid);
        }
    }

    public Map<String, Object> qryItemState(String itemUid) {
        return productMapper.qryItemState(itemUid);
    }
}
