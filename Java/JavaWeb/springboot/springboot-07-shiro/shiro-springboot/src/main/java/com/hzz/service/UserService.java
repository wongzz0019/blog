package com.hzz.service;

import com.hzz.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author Bosco
 * @date 2022/1/4
 */

public interface UserService {
    User queryUserByName(String name);
}
