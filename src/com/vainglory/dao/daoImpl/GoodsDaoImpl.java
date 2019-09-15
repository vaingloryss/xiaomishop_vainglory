package com.vainglory.dao.daoImpl;

import com.vainglory.dao.IGoodsDao;
import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;
import com.vainglory.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements IGoodsDao {
    QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public List<GoodsType> getGoodsTypeList() {
        String sql = "select * from tb_goods_type";
        try {
            List<GoodsType> goodsTypeList = queryRunner.query(sql, new BeanListHandler<>(GoodsType.class));
            return goodsTypeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoodsType> getGoodsTypeAjax() {
        String sql = "select * from tb_goods_type where level=1 limit 0,4";
        try {
            List<GoodsType> goodsTypeList = queryRunner.query(sql, new BeanListHandler<>(GoodsType.class));
            return goodsTypeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> getGoodsListByTypeId(Integer typeId) {
        String sql = "select tg.*,tgt.name typeName from tb_goods tg,tb_goods_type tgt where tg.typeid = tgt.id";
        if(typeId>0){
            sql += " and tg.typeid=?";
        }
        try {
            List<Goods> goodsList = queryRunner.query(sql, new BeanListHandler<>(Goods.class),typeId);
            return goodsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getCount(String condition) {  //" typeId=1";
        String  sql="select count(*) from tb_goods";
        if(condition!=null&&condition.trim().length()!=0){
            sql=sql+" where "+condition;   // select count(*) from tb_goods  where  typeId=1
        }
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return (long) qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询商品个数失败", e);
        }

    }

    @Override
    public List<Goods> findPageByWhere(int pageNum,int pageSize,String condition) {
        String  sql="select * from tb_goods";
        if(condition!=null&&condition.trim().length()!=0){
            sql=sql+" where "+condition;   // select *  from tb_goods  where  typeId=1
        }
        sql+=" order by id limit ?,?";
        // select *  from  tb_goods  where  typeId=1  order by id limit ?,?
        // select * from tb_goods order by id limit ?,?

        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<Goods>(Goods.class),(pageNum-1)*pageSize,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("分页查询失败", e);
        }


    }

    @Override
    public Goods findById(int gid) {
        try {
            return queryRunner.query("select * from tb_goods where id=?", new BeanHandler<>(Goods.class),gid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询商品失败", e);
        }

    }

    @Override
    public GoodsType findTypeById(Integer id) {
        String sql = "select * from tb_goods_type where id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(GoodsType.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
