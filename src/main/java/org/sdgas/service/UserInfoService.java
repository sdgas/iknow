package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.UserInfo;

import java.util.List;

/**
 * Created by 120378 on 2015-04-03.
 */
public interface UserInfoService extends DAO {

    /**
     * 根据用户名查找用户
     *
     * @param name 用户名
     * @return 用户
     */
    public UserInfo findByName(String name);

    /**
     * @param userId
     * @return
     */
    public UserInfo findById(String userId);


    /**
     * 查找全部员工
     *
     * @return 员工信息
     */
    public List<UserInfo> findAll();

}
