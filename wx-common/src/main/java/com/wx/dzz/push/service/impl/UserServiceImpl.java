package com.wx.dzz.push.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx.dzz.push.dao.UserMapper;
import com.wx.dzz.push.service.UserService;
import com.wx.dzz.push.utlis.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
