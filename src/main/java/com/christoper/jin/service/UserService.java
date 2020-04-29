package com.christoper.jin.service;

import com.christoper.jin.constant.RoleType;
import com.christoper.jin.domain.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * @Class UserService
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
public class UserService {

  private final BasicService basicService;

  public void makeUsers(){
    System.out.println("초기 사용자 생성");
    basicService.execute((em)->{
      User u1 = User.builder()
              .name("진형은-A")
              .enterDate(LocalDate.of(2017, 10, 23))
              .roleType(RoleType.ADMIN)
              .description("관리자 계정입니다.")
              .build();
      em.persist(u1);


      User u2 = User.builder()
              .name("진형은-B")
              .enterDate(LocalDate.of(2019, 1, 22))
              .roleType(RoleType.USER)
              .description("짬순위 3위")
              .build();
      em.persist(u2);

      User u3 = User.builder()
              .name("진형은-C")
              .enterDate(LocalDate.of(2020, 12, 31))
              .roleType(RoleType.USER)
              .description("막내")
              .build();
      em.persist(u3);

      User u4 = User.builder()
              .name("진형은-D")
//              .enterDate(LocalDate.of(2016, 0, 31))//value ranage 처리됨. 실행시 에러남.
              .enterDate(LocalDate.of(2016, 2, 29))
              .roleType(RoleType.USER)
              .description("짬 1위")
              .build();
      em.persist(u4);
    });
  }
}
