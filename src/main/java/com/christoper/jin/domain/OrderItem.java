package com.christoper.jin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Class OrderItem
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
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="order_id")
  @Setter
  private Order order;

  private Long orderPrice;

  private int orderCount;

  @Builder
  public OrderItem(Item item, Long orderPrice, int orderCount) {
    this.item = item;
    this.orderPrice = orderPrice;
    this.orderCount = orderCount;
  }
}
