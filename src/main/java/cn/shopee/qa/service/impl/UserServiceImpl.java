package cn.shopee.qa.service.impl;

import cn.shopee.qa.dao.UserMapper;
import cn.shopee.qa.model.User;
import cn.shopee.qa.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userDao;

    @Override
    public User getUserById(int userId) {
        // TODO Auto-generated method stub
        return this.userDao.selectByPrimaryKey(userId);
    }

}