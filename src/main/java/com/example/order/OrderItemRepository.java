package com.example.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//    @Query("select o from order as o where o.uid = ?1 and o.deal_date between ?2 and ?3")
//    List<Order> findByUidAndDateBetween(Long uid, Date data1, Date data2);
//
//    @Query("select o from order as o where o.gid = ?1 and o.deal_date between ?2 and ?3")
//    List<Order> findByGidAndDateBetween(Long gid, Date data1, Date data2);

//    List<OrderItem> findOrderItemsByOrderId(long orderId);


//    @Query("select o from orders as o where o.id = ?1")
//    Page<Order> findOrdersByUserId(Long uid, Pageable pageable);
//    @Query("select o from orders as o where o.id = ?1")
//    Page<Order> findOrdersByUser(Long uid, Pageable pageable);
}
