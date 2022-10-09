package com.gorgeous.bookshop.controller;

import com.gorgeous.bookshop.bean.ItemInfo;
import com.gorgeous.bookshop.service.ProductService;
import com.gorgeous.bookshop.utils.PowerJSON;
import com.gorgeous.bookshop.utils.RespData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unit")
@Api(tags = "商品接口")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ItemInfo itemInfo;

    @ApiOperation("加载所有商品列表")
    @RequestMapping(value = "/loadItems", method = RequestMethod.GET)
    public List<PowerJSON> loadItems(@RequestParam String page, @RequestParam String pageSize) {
        return productService.loadItems(page, pageSize);
    }

    @ApiOperation("新增商品")
    @RequestMapping(value = "/submitItem", method = RequestMethod.POST)
    public RespData submitItem(@RequestBody Map<String,Object> map){
        return productService.submitItem(map);
    }

    @ApiOperation("更新商品信息")
    @RequestMapping(value = "/updateProduct", method = RequestMethod.PUT)
    public RespData updateProduct(@RequestBody Map<String,Object> map) {
        return productService.updateProduct(map);
    }

    @ApiOperation("删除商品")
    @RequestMapping(value = "/deleteProduct/{uid}", method = RequestMethod.DELETE)
    public RespData deleteProductUnit(@PathVariable String uid) {
        return productService.deleteProductUnit(uid);
    }
}
