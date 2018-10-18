package com.example.order;

import com.example.goods.Goods;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnore
//    @Column(nullable = false, name = "oid", insertable = false, updatable = false)
    private long oid;

    private int num;

    @OneToOne
    @JoinColumn(nullable = false, name = "gid")
    private Goods goods;

    public OrderItem() {

    }

    public OrderItem(long oid, int num, Goods goods) {
        this.oid = oid;
        this.num = num;
        this.goods = goods;
    }

    public Long getId() {
        return id;
    }

    public long getOid() {
        return oid;
    }

    public int getNum() {
        return num;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
