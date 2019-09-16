package com.vainglory.dao.daoImpl;


import com.vainglory.dao.IUserDao;
import com.vainglory.domain.User;
import com.vainglory.utils.DataSourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UserDaoImpl implements IUserDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public int add(User user) {
        Object[] params = {null,user.getUsername(), user.getPassword(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getCode()};
        String sql = "insert into tb_user values(?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            log.error("用户添加失败："+e.getMessage());
        }
        return 0;
    }

    @Override
    public User findById(Integer id) {
        String sql = "select * from tb_user where id ="+id;
        try {
            return queryRunner.query(sql, new BeanHandler<>(User.class));
        } catch (SQLException e) {
            log.error("根据ID查询用户失败："+e.getMessage());
        }
        return  null;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "update tb_user set flag=2 where id=? ";
        try {
            int result = queryRunner.update(sql,id);
            if (result==1){
                return true;
            }
        } catch (SQLException e) {
            log.error("用户删除失败："+e.getMessage());
        }
        return false;
    }

    @Override
    public int update(User user) {
        Object[] params = {user.getUsername(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getId()};
        String sql = "update tb_user set username=?,email=?,gender=?,flag=?,role=? where id=?";
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            log.error("用户修改失败："+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<User> findAll(Integer flag) {
        String sql = "select * from tb_user where flag=?";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(User.class),flag);
        } catch (SQLException e) {
            log.error("查询所有用户失败："+e.getMessage());
        }
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        String sql = "select * from tb_user where username =?";
        try {
            return queryRunner.query(sql, new BeanHandler<>(User.class),userName);
        } catch (SQLException e) {
            log.error("根据用户名查询失败："+e.getMessage());
        }
        return  null;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "select * from tb_user where email=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(User.class),email);
        } catch (SQLException e) {
            log.error("根据Email查询失败："+e.getMessage());
        }
        return null;
    }

    @Override
    public void activate(Integer uid) {
        String sql = "update tb_user set flag=1 where id = "+uid;
        try {
            queryRunner.update(sql);
        } catch (SQLException e) {
            log.error("用户激活失败："+e.getMessage());
        }
    }
}
