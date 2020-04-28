package com.christoper.jin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class OrderDto
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
@Setter
public class OrderDto {
  private Long userId;

  private List<OrderItemDto> orderItemList = new ArrayList<>();


  public void addOrderItem(OrderItemDto orderItemDto){
    this.orderItemList.add(orderItemDto);
  }

  @Builder
  public OrderDto(long userId) {
    this.userId = userId;
  }
}
