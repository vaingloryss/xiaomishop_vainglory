package com.vainglory.domain;

import java.math.BigDecimal;

public class Cart {
    private Integer id;
    private Integer gid;
    private Integer num;
    private Goods goods;
    private BigDecimal money;

    public Cart() {
    }

    public Cart(Integer id, Integer gid, Integer num, BigDecimal money) {
        this.id = id;
        this.gid = gid;
        this.num = num;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", gid=" + gid +
                ", num=" + num +
                ", goods=" + goods +
                ", money=" + money +
                '}';
    }
}
