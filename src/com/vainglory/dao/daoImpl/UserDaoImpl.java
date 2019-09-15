package com.vainglory.dao.daoImpl;


import com.vainglory.dao.IUserDao;
import com.vainglory.domain.Address;
import com.vainglory.domain.User;
import com.vainglory.utils.DataSourceUtils;
import com.vainglory.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UserDaoImpl implements IUserDao {
    QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public int add(User user) {
        System.out.println(4);
        log.info("添加用户...");
        Object[] params = {null,user.getUsername(), user.getPassword(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getCode()};
        
        String sql = "insert into tb_user values(?,?,?,?,?,?,?,?)";
        try {
            int result = queryRunner.update(sql, params);
            log.info("添加完成，结果{}：",result);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User findById(Integer id) {
        log.info("查询用户根据ID...");
        String sql = "select * from tb_user where id ="+id;
        try {
            User user = queryRunner.query(sql, new BeanHandler<>(User.class));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public boolean delete(Integer id) {
        log.info("删除用户...");
        String sql = "update tb_user set flag=2 where id=? ";
        try {
            int result = queryRunner.update(sql,id);
            log.info("删除结果：{}",result);
            if (result==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int update(User user) {
        log.info("修改用户...");
        Object[] params = {user.getUsername(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getId()};
        String sql = "update tb_user set username=?,email=?,gender=?,flag=?,role=? where id=?";
        try {
            int result = queryRunner.update(sql, params);
            log.info("修改完成，结果:{}",result);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> findAll(Integer flag) {
        log.info("查询所用用户。");
        String sql = "select * from tb_user where flag=?";
        try {
            List<User> userList = queryRunner.query(sql, new BeanListHandler<>(User.class),flag);
            log.info("查询完成，结果数:{}",userList.size());
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        log.info("查询用户根据userName...");
        String sql = "select * from tb_user where username =?";
        try {
            User user = queryRunner.query(sql, new BeanHandler<>(User.class),userName);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
