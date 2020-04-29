package com.christoper.jin.execute;

import com.christoper.jin.service.BasicService;
import com.christoper.jin.service.CategoryItemService;
import com.christoper.jin.service.UserService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Class JPAMainStep4
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
public class JPAMainStep4 {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("christoper");
    BasicService basicService = new BasicService(emf);

    //사용자 초기 데이터 생성
    UserService userService = new UserService(basicService);
    userService.makeUsers();

    //카테고리 & 제품 초기 데이터 생성
    CategoryItemService categoryItemService = new CategoryItemService(basicService);
    categoryItemService.makeDefaultCategoryItem();


    emf.close();
  }
}
