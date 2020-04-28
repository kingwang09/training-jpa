package com.christoper.jin.domain;

import com.christoper.jin.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Class Order
 * @Description
 * @Author hyungeun.jin
 * @Since 2020. 4. 28.
 * @Version 1.0
 * @COPYRIGHT © WADIZ ALL RIGHTS RESERVED.
 * ------------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 수정일 || 수정자 || 수정내용
 * ------------------------------------------------------------------------
 * 2020. 4. 28. || 진형은 || 최초생성
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name="Order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDateTime orderDateTime;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  //양방향 매핑
  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

  @Builder
  public Order(User user, LocalDateTime orderDateTime, OrderStatus orderStatus) {
    this.user = user;
    this.orderDateTime = orderDateTime;
    this.orderStatus = orderStatus;
  }


  public void addOrderItem(OrderItem orderItem){
    if(!this.orderItemList.contains(orderItem)){
      this.orderItemList.add(orderItem);
    }
    orderItem.setOrder(this);
  }

  @Override
  public String toString() {
    return "Order{" +
            "id=" + id +
            ", orderDateTime=" + orderDateTime +
            ", orderStatus=" + orderStatus +
            '}';
  }
}
