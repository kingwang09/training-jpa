package com.christoper.jin.service;

import com.christoper.jin.domain.*;
import com.christoper.jin.dto.OrderDto;
import com.christoper.jin.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Class OrderService
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
@RequiredArgsConstructor
public class OrderService {
  private final EntityManagerFactory emf;

  public void execute(Consumer<EntityManager> consumer){
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try{
      tx.begin();
      consumer.accept(em);

      tx.commit();
    }catch(Exception ex){
      System.out.println("exception : "+ ex.getMessage());
      ex.printStackTrace();
      tx.rollback();
    }finally {
      em.close();
    }
  }

  public void init(){
    execute((em)->{
      //상점 개장
      Item item1 = Item.builder()
              .name("토마토")
              .stockQuantity(100)
              .build();
      em.persist(item1);

      Item item2 = Item.builder()
              .name("쌀")
              .stockQuantity(10)
              .build();
      em.persist(item2);

      Item item3 = Item.builder()
              .name("생수")
              .stockQuantity(5)
              .build();
      em.persist(item3);

      //사용자 미리등록
//      User user = User.builder()
//              .name("진형은-A")
//              .build();
//      em.persist(user);
//
//      Team team = Team.builder()
//              .name("나중에 팀지정")
//              .build();
//      user.setTeam(team);//update 쿼리가 발생하겠지?
    });
  }


  public void newOrder(OrderDto orderDto){
    Long userId = orderDto.getUserId();
    List<OrderItemDto> orderItemList = orderDto.getOrderItemList();

    execute((em) ->{
      System.out.println("1. findUser : "+userId);
      User user = em.find(User.class, userId);
      System.out.println("  finded : "+user);

      Order newOrder = Order.builder()
              .user(user)
              .orderDateTime(LocalDateTime.now())
              .build();

      for(OrderItemDto itemDto : orderItemList){
        Item buyItem = em.find(Item.class, itemDto.getItemId());
        System.out.println("2. find Item : "+buyItem);

        buyItem.buy(itemDto.getCount());
        OrderItem newOrderItem = OrderItem.builder()
                .item(buyItem)
                .orderCount(itemDto.getCount())
                .build();
        em.persist(newOrderItem);//newOrder만 저장하면 다 반영되면 안되나?
        newOrder.addOrderItem(newOrderItem);

        System.out.println("3. new OrderItem: "+newOrderItem);
      }
      em.persist(newOrder);
      System.out.println("4. final Order : "+newOrder);
    });
  }
}
