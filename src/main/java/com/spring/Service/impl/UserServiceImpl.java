package com.spring.Service.impl;

import com.google.gson.Gson;
import com.spring.Service.UserService;
import com.spring.dao.UserDao;
import com.spring.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    //通过这两redistemplate可以操作redis
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate redisTemplate;

    @Autowired
    public UserServiceImpl(UserDao userDao, StringRedisTemplate stringRedisTemplate, RedisTemplate redisTemplate) {
        this.userDao = userDao;
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public Map<String, Object> findById( Integer id ) {
        Map<String, Object> map = new HashMap<>();
        //从redis中找到key是user:id 的对象
        Users user = new Gson().fromJson(stringRedisTemplate.opsForValue().get("user:" + id), Users.class);
        if (user != null) {
            // 如果缓存中有，直接从缓存取
            map.put("name", user.getName());
            map.put("gender", user.getGander());
            map.put("phone", user.getPhone());
        } else {
            // 如果没有则直接从数据库取
            Users user1 = userDao.findById(id);
            map.put("name", user1.getName());
            map.put("gender", user1.getGander());
            map.put("phone", user1.getPhone());
            stringRedisTemplate.opsForValue().set("user:" + id, new Gson().toJson(user1));
        }
        return map;
    }
}
