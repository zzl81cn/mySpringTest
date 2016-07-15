package com.baobaotao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobaotao.dao.LoginLogDao;
import com.baobaotao.dao.UserDao;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;

/**
 * Created by zhouzilong on 2016/7/15.
 */
@Service
public class UserService {

    @Autowired
    private LoginLogDao loginLogDao;
    @Autowired
    private UserDao userDao;

    public boolean hasMatchUser(String userName, String password){
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    // loginSuccess方法根据实参user参数构造出LoginLog对象，并将user.credits递增5（即用户没登录一次赚取5个积分），然后调用userDao更新到t_user中，再调用loginLogDao往t_login_log表中添加一条记录
    public void loginSuccess(User user){
        user.setCredits(5 + user.getCredits());

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVist());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
