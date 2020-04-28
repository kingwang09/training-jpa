package com.christoper.jin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Class Item
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
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Long price;

  private int stockQuantity;

  @Builder
  public Item(String name, Long price, int stockQuantity) {
    this.name = name;
    this.price = price;
    this.stockQuantity = stockQuantity;
  }
}
