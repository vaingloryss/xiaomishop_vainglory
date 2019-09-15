package com.vainglory.dao.daoImpl;

import com.vainglory.dao.IOrderDao;
import com.vainglory.domain.Order;
import com.vainglory.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderDaoImpl implements IOrderDao {
    @Override
    public void add(Order order) {
        QueryRunner queryRunner = new QueryRunner();
       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(order.getTime());
        Timestamp timestamp = Timestamp.valueOf(format);*/
        Object[] params = {order.getId(),order.getUid(),order.getMoney(),order.getStatus(),order.getTime(),order.getAid()};
        String sql = "insert into tb_order(id,uid,money,status,time,aid) values(?,?,?,?,?,?)";
        try {
            queryRunner.update(DataSourceUtils.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderStatus(String oid, String status) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update tb_order set status=? where id = ?";
        try {
            queryRunner.update(sql,status,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findByUid(Integer id) {
        System.out.println("dao:"+id);
        String sql = "select * from tb_order where uid=?";
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            List<Order> orderList = queryRunner.query(sql, new BeanListHandler<>(Order.class), id);
            if (orderList==null){
                System.out.println("null");
            }
            System.out.println(orderList);
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order findByOid(String oid) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from tb_order where id = ?";
        try {
            return queryRunner.query(sql, new BeanHandler<>(Order.class),oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
