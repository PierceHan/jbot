package com.wllfengshu.model.service;

import com.wllfengshu.model.dao.UserDao;
import com.wllfengshu.model.entity.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, Object> insertUser(TUser user){
        Map<String, Object> result = new HashMap<>();
        user.setCreateTime(new Date());
        userDao.insertUser(user);
        logger.info("UserServiceImpl,insertUser-------->user:{}",user);
        return result;
    }

    @Override
    public Map<String, Object> deleteUser(Integer id){
        Map<String, Object> result = new HashMap<>();
        userDao.deleteUser(id);
        logger.info("UserServiceImpl,deleteUser-------->id:{}",id);
        return result;
    }

    @Override
    public Map<String, Object> updateUser(TUser user){
        Map<String, Object> result = new HashMap<>();
        user.setUpdateTime(new Date());
        userDao.updateUser(user);
        logger.info("UserServiceImpl,updateUser-------->user:{}",user);
        return result;
    }

    @Override
    public Map<String, Object> selectUsers(Map<String, Object> params){
        Map<String, Object> result = new HashMap<>();
        logger.info("UserServiceImpl,selectUsers-------->params:{}",params);
        result.put("data",userDao.selectUsers(params));
        return result;
    }

    @Override
    public Map<String, Object> selectUser(Integer id){
        Map<String, Object> result = new HashMap<>();
        logger.info("UserServiceImpl,selectUser-------->id:{}",id);
        result.put("data",userDao.selectUser(id));
        return result;
    }

}
