package com.christoper.jin.service;

import com.christoper.jin.domain.Category;
import com.christoper.jin.domain.CategoryItem;
import com.christoper.jin.domain.Item;
import lombok.RequiredArgsConstructor;

/**
 * @Class CategoryItemService
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
@RequiredArgsConstructor
public class CategoryItemService {
  private final BasicService basicService;


  public void makeDefaultCategoryItem(){
    basicService.execute((em)->{
      //기본 카테고리 생성
      Category foodCategory = Category.builder()
              .name("음식 카테고리")
              .build();
      em.persist(foodCategory);

      Category fresh = Category.builder()
              .name("신선 식품")
              .build();
      fresh.setParent(foodCategory);
      em.persist(fresh);

      Category health = Category.builder()
              .name("건강 식품")
              .build();
      health.setParent(foodCategory);
      em.persist(health);

      Category tech = Category.builder()
              .name("테크 카테고리")
              .build();
      em.persist(tech);

      Category digital = Category.builder()
              .name("디지털(전자제품/가전)")
              .build();
      digital.setParent(tech);
      em.persist(digital);

      Category mobile = Category.builder()
              .name("모바일(스마트폰)")
              .build();
      mobile.setParent(tech);
      em.persist(mobile);


      //기본 제품
      Item milk = Item.builder()
              .name("우유")
              .price(1400L)
              .stockQuantity(1000)
              .build();
      em.persist(milk);

      Item tomato = Item.builder()
              .name("토마토 2kg")
              .price(3800L)
              .stockQuantity(100)
              .build();
      em.persist(tomato);

      Item omega = Item.builder()
              .name("오메가3")
              .price(10800L)
              .stockQuantity(10)
              .build();
      em.persist(omega);

      Item noni = Item.builder()
              .name("노니")
              .price(25900L)
              .stockQuantity(5)
              .build();
      em.persist(noni);


      //Item - Category 매핑
      CategoryItem freshCategoryItem1 = CategoryItem.builder()
              .category(fresh)
              .item(milk)
              .build();
      em.persist(freshCategoryItem1);

      CategoryItem freshCategoryItem2 = CategoryItem.builder()
              .category(fresh)
              .item(tomato)
              .build();
      em.persist(freshCategoryItem2);

      CategoryItem healCategoryItem1 = CategoryItem.builder()
              .category(health)
              .item(omega)
              .build();
      em.persist(healCategoryItem1);

      CategoryItem healCategoryItem2 = CategoryItem.builder()
              .category(health)
              .item(noni)
              .build();
      em.persist(healCategoryItem2);
    });
  }
}
