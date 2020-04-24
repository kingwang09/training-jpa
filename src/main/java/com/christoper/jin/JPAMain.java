package com.christoper.jin;

import com.christoper.jin.service.BasicService;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Class JPAMain
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
public class JPAMain {

  public static void main(String[] args){
    System.out.println("JPA Hello world Start.");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("christoper");

    BasicService basicService = new BasicService(emf);
    basicService.saveUser();

    emf.close();
  }
}
