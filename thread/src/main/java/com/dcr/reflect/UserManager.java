package com.dcr.reflect;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/28 18:30
 */
public interface UserManager {

    /**
     * 新增用户抽象方法
     * @param userName
     * @param password
     */
    void addUser(String userName,String password);

    /**
     * 删除用户抽象方法
     * @param userName
     */
    void delUser(String userName);

}
