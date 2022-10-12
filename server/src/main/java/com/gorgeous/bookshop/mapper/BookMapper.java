package com.gorgeous.bookshop.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gorgeous.bookshop.constant.PowerJSON;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BookMapper {

    @Select("select id, name, subname, author, authorIntro, publishing, published, " +
            "designed, photoUrl, description, subject, size, number from book")
    Page<PowerJSON> loadBooks(Page<PowerJSON> page, QueryWrapper<List<PowerJSON>> wrapper);

    @Select("select id, name, subname, author, authorIntro, publishing, published, " +
            "designed, photoUrl, description, subject, size, number from book where id=#{id}")
    PowerJSON findBookByISBN(@Param("id") BigInteger id);

    @Insert(
            "<script>" +
            "INSERT INTO book " +
            "(id, name, subname, author, authorIntro, publishing, published, designed, " +
                    "photoUrl, description, subject, size, number) " +
            "VALUES " +
            "<foreach collection ='bookList' item='book' separator =','>" +
                    "(#{book.id}, #{book.name}, #{book.subname}, #{book.author}, #{book.authorIntro}, #{book.publishing}, #{book.published}, #{book.designed}, " +
                    "#{book.photoUrl}, #{book.description}, #{book.subject}, #{book.size}, #{book.number})" +
            "</foreach >" +
            "</script>"
    )
    int addBook(List<PowerJSON> bookList);

}
