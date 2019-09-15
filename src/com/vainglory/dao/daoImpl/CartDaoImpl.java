package com.vainglory.dao.daoImpl;

import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.vainglory.dao.ICartDao;
import com.vainglory.domain.Cart;
import com.vainglory.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements ICartDao {

    QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public int add(Cart cart) {
        Object[] params = {cart.getId(),cart.getGid(),cart.getNum(),cart.getMoney()};
        String sql = "insert into tb_cart (id,gid,num,money) values (?,?,?,?)";
        try {
            return queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByGid(Integer gid) {
        return 0;
    }

    @Override
    public int deleteByUid(Integer uid) {
        String sql = "delete from tb_cart where id = ?";
        try {
            return queryRunner.update(sql,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Cart findByUidAndGid(Integer uid, Integer gid) {
        String sql = "select * from tb_cart where id=? and gid=?";
        try {
            return queryRunner.query(sql, new BeanHandler<>(Cart.class),uid,gid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Cart cart) {
        Object[] params = {cart.getNum(),cart.getMoney(),cart.getId(),cart.getGid()};
        String sql = "update tb_cart set num = ?,money=? where id=? and gid=?";
        try {
            return queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Cart> findAll() {
        String sql = "select * from tb_cart";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Cart.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cart> finByUid(Integer uid) {
        String sql = "select * from tb_cart where id=?";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Cart.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteByUidAndGid(Integer uid, Integer gid) {
        String sql = "delete from tb_cart where id = ? and gid = ?";
        try {
            return queryRunner.update(sql,uid,gid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
