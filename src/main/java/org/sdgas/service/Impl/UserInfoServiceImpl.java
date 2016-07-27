package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.UserInfo;
import org.sdgas.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-03.
 */

@Service
@Transactional
public class UserInfoServiceImpl extends DaoSupport implements UserInfoService {

    @Override
    public UserInfo findByName(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", name);
        return (UserInfo) this.findSpecialObject(UserInfo.class, params);
    }

    @Override
    public UserInfo findById(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return (UserInfo) this.findSpecialObject(UserInfo.class, params);
    }


    @Override
    public List<UserInfo> findAll() {
        return this.find(UserInfo.class);
    }
}
