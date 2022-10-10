package com.gorgeous.bookshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gorgeous.bookshop.bean.ItemInfo;
import com.gorgeous.bookshop.mapper.ProductMapper;
import com.gorgeous.bookshop.utils.DateUtil;
import com.gorgeous.bookshop.utils.PowerJSON;
import com.gorgeous.bookshop.utils.RespData;
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
        return RespData.buildSuccess(200, result, "加载成功");
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
            return RespData.buildSuccess("发布商品成功！",200);
        } else {
            return RespData.buildError("发布失败",200);
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
            return RespData.buildSuccess("更新信息成功！",200);
        } else {
            return RespData.buildError("更新信息失败",200);
        }
    }

    public RespData deleteProductUnit(String uid){
        if (productMapper.deleteProductUnit(uid) == 1){
            return RespData.buildSuccess("删除成功！",200);
        } else {
            return RespData.buildError("删除失败",200);
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
