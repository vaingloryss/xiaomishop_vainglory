package com.vainglory.dao.daoImpl;

import com.vainglory.dao.IGoodsDao;
import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;
import com.vainglory.utils.DataSourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class GoodsDaoImpl implements IGoodsDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public List<GoodsType> getGoodsTypeList() {
        String sql = "select * from tb_goods_type";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(GoodsType.class));
        } catch (SQLException e) {
            log.error("获取商品类型失败："+e.getMessage());
        }
        return null;
    }

    @Override
    public List<GoodsType> getGoodsTypeAjax() {
        String sql = "select * from tb_goods_type where level=1 limit 0,4";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(GoodsType.class));
        } catch (SQLException e) {
            log.error("获取商品类型失败："+e.getMessage());
        }
        return null;
    }

    @Override
    public long getCount(String condition) {
        String  sql="select count(*) from tb_goods";
        if(condition!=null&&condition.trim().length()!=0){
            sql=sql+" where "+condition;
        }
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return (long) qr.query(sql, new ScalarHandler());
        } catch (Exception e) {
            log.error("查询商品个数失败："+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Goods> findPageByWhere(int pageNum,int pageSize,String condition) {
        String  sql="select * from tb_goods";
        if(condition!=null&&condition.trim().length()!=0){
            sql=sql+" where "+condition;
        }
        sql+=" order by id limit ?,?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<>(Goods.class),(pageNum-1)*pageSize,pageSize);
        } catch (SQLException e) {
            log.error("分页查询商品失败："+e.getMessage());
        }
        return null;
    }

    @Override
    public Goods findById(int gid) {
        try {
            return queryRunner.query("select * from tb_goods where id=?", new BeanHandler<>(Goods.class),gid);
        } catch (SQLException e) {
            log.error("根据商品ID查询商品："+e.getMessage());
        }
        return null;
    }

    @Override
    public GoodsType findTypeById(Integer id) {
        String sql = "select * from tb_goods_type where id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(GoodsType.class),id);
        } catch (SQLException e) {
            log.error("根据商品类型ID查询商品类型失败："+e.getMessage());
        }
        return null;
    }
}
