package com.gorgeous.bookshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gorgeous.bookshop.constant.BookConstant;
import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.mapper.BookMapper;
import com.gorgeous.bookshop.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Service("BookService")
public class BookService {

    private LinkedBlockingQueue<PowerJSON> bookQueue;

    @Autowired
    private BookMapper bookMapper;

    public RespData loadBooks(String current, String pageSize) {
        Page<PowerJSON> page = new Page<>(Integer.parseInt(current), Integer.parseInt(pageSize));
        Page<PowerJSON> jsonPage = bookMapper.loadBooks(page, null);

        PowerJSON result = new PowerJSON();
        result.put("total", jsonPage.getTotal());
        result.put("records", jsonPage.getRecords());
        return RespData.success("加载成功", result);
    }

    public RespData findBookByISBN(String isbn) {
        PowerJSON book = bookMapper.findBookByISBN(new BigInteger(isbn));
        if (book != null) {
            return RespData.success("获取书籍信息成功", book);
        }

        String url = BookConstant.isbnUrl.replace("#", isbn);
        String result = HttpUtil.get(url);
        if (result == null) {
            return RespData.error("未获取到书籍信息，请手动填写");
        }

        PowerJSON json = new PowerJSON(result);
        if (json.getInteger("ret") != 0) {
            return RespData.error("未获取到书籍信息，请手动填写");
        }

        PowerJSON data = json.getPowerJSON("data");
        for (String unusedTag : BookConstant.unusedTags) {
            data.remove(unusedTag);
        }
        bookQueue.offer(data);
        return RespData.success("获取书籍信息成功", data);
    }

    public int addBook(List<PowerJSON> book) {
        return bookMapper.addBook(book);
    }

    public void setBookQueue(LinkedBlockingQueue<PowerJSON> bookQueue) {
        this.bookQueue = bookQueue;
    }
}
