package com.vainglory.dao.daoImpl;

import com.vainglory.dao.IAddressDao;
import com.vainglory.domain.Address;
import com.vainglory.utils.DataSourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class AddressDaoImpl implements IAddressDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public List<Address> getAddressByUid(Integer uid) {
        String sql = "select * from tb_address where uid = ? order by level desc,id desc";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Address.class),uid);
        } catch (SQLException e) {
            log.error("根据用户ID查询地址失败："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(Address address) {
        String sql = "insert into tb_address values(?,?,?,?,?,?)";
        Object[] params = {null,address.getDetail(),address.getName(),address.getPhone(),address.getUid(),address.getLevel()};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            log.error("添加地址失败："+e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from tb_address where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            log.error("根据地址ID删除地址失败："+e.getMessage());
        }
        return 0;
    }

    @Override
    public void removeDefault(int uid) {
        String sql = "update tb_address set level=0 where uid=? and level=1";
        try {
            queryRunner.update(sql, uid);
        } catch (SQLException e) {
            log.error("移除默认地址失败："+e.getMessage());
        }
    }

    @Override
    public int setDefault(int id) {
        String sql = "update tb_address set level=1 where id=?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            log.error("设置默认地址失败："+e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(Address address) {
        Object[] params = {address.getName(),address.getPhone(),address.getDetail(),address.getId()};
        String sql = "update tb_address set name=?,phone=?,detail=? where id=?";
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            log.error("更新地址失败："+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Address> findByUid(Integer uid) {
        String sql = "select * from tb_address where uid = ?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Address.class),uid);
        } catch (SQLException e) {
            log.error("根据用户ID查询地址失败："+e.getMessage());
        }
        return null;
    }

    @Override
    public Address findById(Integer aid) {
        String sql = "select * from tb_address where id = ?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Address.class),aid);
        } catch (SQLException e) {
            log.error("根据地址ID查询地址失败："+e.getMessage());
        }
        return null;
    }
}
