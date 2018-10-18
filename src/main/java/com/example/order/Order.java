package com.example.order;


import com.example.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(nullable = false, name = "uid")
    private User user;

    private BigDecimal payment;

    private String address;

    /* 订单状态 0-出库中，1-配送中，2-已签收,3-已拒收,4-申请退款中，5-申请退货中,6-完成 */
    private short status;

    public Order() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public String getAddress() {
        return address;
    }

    public short getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStutas(short stutas) {
        this.status = stutas;
    }

}

