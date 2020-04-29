package com.christoper.jin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Class CategoryItem
 * @Description
 * @Author hyungeun.jin
 * @Since 2020. 4. 29.
 * @Version 1.0
 * @COPYRIGHT © WADIZ ALL RIGHTS RESERVED.
 * ------------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 수정일 || 수정자 || 수정내용
 * ------------------------------------------------------------------------
 * 2020. 4. 29. || 진형은 || 최초생성
 */
@Getter
@NoArgsConstructor
@Entity
public class CategoryItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="CATEGORY_ID")
  private Category category;

  @ManyToOne
  @JoinColumn(name="ITEM_ID")
  private Item item;

  @Builder
  public CategoryItem(Category category, Item item) {
    this.category = category;

    if(!this.category.getMappingItemList().contains(this)) {
      this.category.getMappingItemList().add(this);
    }

    this.item = item;
  }
}

