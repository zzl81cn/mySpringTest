package com.baobaotao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.User;
/**
 * Created by zhouzilong on 2016/7/15.
 */
// 通过Spring注解定义一个DAO
@Repository
public class UserDao {
    // Inject jdbcTemplate's Bean
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password){
        String sqlStr = "SELECT count(*) FROM t_user "
                + " WHERE user_name = ? and password = ?";
        //代替的方法为queryForObject(String sql, Object[] args, Class<T> requiredType)。需要返回的是什么类型，就在第三个参数写什么类型。比如int类型就写Integer.class. long类型就写Long.class
        // sqlStr, Object[]{userName, password}
        return jdbcTemplate.queryForObject(sqlStr, Integer.class, new Object[]{userName, password});
    }

    public User findUserByUserName(final String userName){
        String sqlStr = "SELECT user_id.user_name.credits "
                + " FROM t_user WHERE user_name = ?";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName},
            new RowCallbackHandler(){
                public void processRow(ResultSet rs) throws SQLException {
                    user.setUserId(rs.getInt("user_id"));
                    user.setUserName(userName);
                    user.setCredits(rs.getInt("credits"));
                }
            });
        return user;
    }

    public void updateLoginInfo(User user){
        String sqlStr = "UPDATE t_user SET last_visit=?, last_ip=?, credits=? "
                + " WHERE user_id=?";
        jdbcTemplate.update(sqlStr, new Object[]{user.getLastVist(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
