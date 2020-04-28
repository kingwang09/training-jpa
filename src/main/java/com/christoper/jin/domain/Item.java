package com.christoper.jin.domain;

import com.christoper.jin.exception.ValidateException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Table(name="Item")
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


  public void buy(int buyCount){
    if(this.stockQuantity == 0){
      throw new ValidateException("현재 잔여 수량이 존재하지 않습니다.");
    }

    if(buyCount > stockQuantity){
      throw new ValidateException("준비된 수량을 초과하였습니다.");
    }
    this.stockQuantity = this.stockQuantity - buyCount;
  }

  @Override
  public String toString() {
    return "Item{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", stockQuantity=" + stockQuantity +
            '}';
  }
}
