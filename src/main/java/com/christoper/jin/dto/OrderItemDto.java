package com.christoper.jin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Class OrderItemDto
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
public class OrderItemDto {

  private Long itemId;

  private int count;

  @Builder
  public OrderItemDto(long itemId, int count) {
    this.itemId = itemId;
    this.count = count;
  }
}
