package com.christoper.jin.execute;

import com.christoper.jin.domain.User;
import com.christoper.jin.dto.OrderDto;
import com.christoper.jin.dto.OrderItemDto;
import com.christoper.jin.service.OrderService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Class JPAMainStep3
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
public class JPAMainStep3 {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("christoper");
    try {
      OrderService service = new OrderService(emf);
      service.init();

      User user = User.builder()
              .name("진형은-dummy")
              .build();
      service.execute((em) -> {
        em.persist(user);
      });

      System.out.println("created user: " + user);

      OrderDto newOrderDto = OrderDto.builder()
              .userId(user.getId())
              .build();

      newOrderDto.addOrderItem(OrderItemDto.builder()
              .itemId(1L)
              .count(10)
              .build());
      newOrderDto.addOrderItem(OrderItemDto.builder()
              .itemId(2L)
              .count(10)
              .build());
      newOrderDto.addOrderItem(OrderItemDto.builder()
              .itemId(3L)
              .count(3)
              .build());

      service.newOrder(newOrderDto);
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      emf.close();
    }
  }
}
