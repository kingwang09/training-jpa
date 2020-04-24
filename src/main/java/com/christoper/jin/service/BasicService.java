package com.christoper.jin.service;

import com.christoper.jin.domain.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;

/**
 * @Class BasicService
 * @Description
 * @Author hyungeun.jin
 * @Since 2020. 4. 24.
 * @Version 1.0
 * @COPYRIGHT © WADIZ ALL RIGHTS RESERVED.
 * ------------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 수정일 || 수정자 || 수정내용
 * ------------------------------------------------------------------------
 * 2020. 4. 24. || 진형은 || 최초생성
 */
@RequiredArgsConstructor
public class BasicService {
  private final EntityManagerFactory emf;


  public void saveUser(Consumer<EntityManager> consumer){
    System.out.println("save User start.");

    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    try{
      tx.begin();

      consumer.accept(em);

      tx.commit();
    }catch(Exception ex){
      System.out.println("exception : "+ ex.getMessage());
      tx.rollback();
    }finally {
      em.close();
    }
  }
}
