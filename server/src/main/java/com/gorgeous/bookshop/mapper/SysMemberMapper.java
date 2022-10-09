package com.gorgeous.bookshop.mapper;

import com.gorgeous.bookshop.bean.SysMember;
import org.springframework.stereotype.Repository;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
@Repository
public interface SysMemberMapper {

    SysMember validateUserByUsername (String username);

    Integer getCountByUsername(String username);

    void insertUser(SysMember sysMember);

}
