package com.gorgeous.bookshop.controller;

import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/book")
@Api(tags = "书籍接口")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("分页加载所有书籍列表")
    @RequestMapping(value = "/loadBooks", method = RequestMethod.GET)
    public RespData loadBooks(@RequestParam String page, @RequestParam String pageSize) {
        return bookService.loadBooks(page, pageSize);
    }

    @ApiOperation("根据ISBN查询书籍信息")
    @RequestMapping(value = "/findBookByISBN", method = RequestMethod.GET)
    public RespData findBookByISBN(@RequestParam String isbn) {
        return bookService.findBookByISBN(isbn);
    }

    @ApiOperation("新增书籍")
    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public RespData addBook() {
        PowerJSON json = new PowerJSON("{\"code\":\"9787121318474\",\"translator\":null,\"description\":\"随着移动互联网的兴起，以Java技术为后台的互联网技术占据了市场的主导地位，而在Java互联网后台开发中，SSM框架（Spring+Spring MVC+MyBatis）成为了主要架构，《Java EE互联网轻量级框架整合开发——SSM框架（Spring MVC+Spring+MyBatis）和Redis实现》以此为焦点从入门到实际工作要求讲述了SSM框架的技术应用；与此同时，为了提高系统性能，NoSQL（尤其是Redis）在互联网系统中已经广泛使用，为了适应这个变化，《Java EE互联网轻量级框架整合开发——SSM框架（Spring MVC+Spring+MyBatis）和Redis实现》通过Spring讲解了有关Redis的技术应用，这样更加贴近实际学习和工作的需要。\\n《Java EE互联网轻量级框架整合开发——SSM框架（Spring MVC+Spring+MyBatis）和Redis实现》主要分为6个部分，第1部分对Java互联网的框架和主要涉及的模式做初步简介；第2部分讲述MyBatis技术；第3部分讲述Spring基础（包括IoC、AOP和数据库应用），重点讲解Spring数据库事务应用，以满足互联网企业的应用要求；第4部分，讲述Spring MVC框架；第5部分，通过Spring技术的应用，讲解Redis技术；第6部分，讲解SSM+Redis实践应用，通过互联网高并发如抢票、抢红包等场景，使用全注解的方式讲解SSM框架的整合，以及高并发与锁的应用和系统性能优化。\\n《Java EE互联网轻量级框架整合开发——SSM框架（Spring MVC+Spring+MyBatis）和Redis实现》结合企业的实际需求，从原理到实践全面讲解SSM+Redis技术应用，无论你是Java程序员、SSM应用和研究人员，还是Redis应用人员、互联网开发人员，都可以从《Java EE互联网轻量级框架整合开发——SSM框架（Spring MVC+Spring+MyBatis）和Redis实现》中收获知识。\",\"photoUrl\":\"https://img9.doubanio.com/view/subject/m/public/s29496784.jpg\",\"id\":9787121318474,\"publishing\":\"电子工业出版社\",\"designed\":\"平装\",\"author\":\"杨开振 / 周吉文 / 梁华辉 / 谭茂华\",\"subname\":\"SSM框架（Spring MVC+Spring+MyBatis）和Redis实现\",\"authorIntro\":\"\",\"published\":\"2017-7\",\"name\":\"Java EE互联网轻量级框架整合开发\"}");

        ArrayList<PowerJSON> objects = new ArrayList<>();
        objects.add(json);
        int i = bookService.addBook(objects);
        return RespData.success(String.valueOf(i));
    }

}
