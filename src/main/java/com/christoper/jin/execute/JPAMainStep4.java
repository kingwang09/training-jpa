package com.christoper.jin.execute;

import com.christoper.jin.domain.Category;
import com.christoper.jin.domain.CategoryItem;
import com.christoper.jin.service.BasicService;
import com.christoper.jin.service.CategoryItemService;
import com.christoper.jin.service.UserService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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


    //카테고리별 제품 조회
    basicService.execute((em) ->{
      List<Category> categories = em.createQuery("select c from Category c").getResultList();
      for(Category category : categories){
        System.out.println("==============================");
        System.out.println("Category: "+ category);
        System.out.println("==============================");

        if(category.getParent() != null) {//Parent Category가 있는 경우에만 노출
          System.out.println("==============================");
          System.out.println("\tParent Category: " + category.getParent());
          System.out.println("==============================");
        }

        if(!category.getChildList().isEmpty()) {//Child Categories가 있는 경우에만
          System.out.println("==============================");
          System.out.println("\tChild Categories: ");
          System.out.println("==============================");

          for(Category childCategory : category.getChildList()){
            System.out.println("==============================");
            System.out.println("\t\tChild Category: "+childCategory);
            System.out.println("==============================");
          }
        }

        if(!category.getMappingItemList().isEmpty()) {//매핑 아이템이 있는 경우만
          System.out.println("==============================");
          System.out.println("\tMapping Items:");
          System.out.println("==============================");
          for (CategoryItem categoryItem : category.getMappingItemList()) {
            System.out.println("==============================");
            System.out.println("\t\tItem: " + categoryItem.getItem());//lazy select query
            System.out.println("==============================");
          }
        }
      }
    });

    emf.close();
  }
}
