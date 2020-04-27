package com.christoper.jin.execute;

import com.christoper.jin.domain.User;
import com.christoper.jin.service.BasicService;

import javax.persistence.Persistence;
import java.util.List;

/**
 * @Class JPAMainStep2
 *
 * @Description
 * - ManyToOne 관계에서 Lazy로 변경했을 때 N+1 쿼리 이슈 확인
 * - 대안 방법으로 join fetch 확인
 *
 * @Author hyungeun.jin
 * @Since 2020. 4. 27.
 * @Version 1.0
 * @COPYRIGHT © WADIZ ALL RIGHTS RESERVED.
 * ------------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 수정일 || 수정자 || 수정내용
 * ------------------------------------------------------------------------
 * 2020. 4. 27. || 진형은 || 최초생성
 */
public class JPAMainStep2 {



  public static void main(String[] args) {
    BasicService service = new BasicService(Persistence.createEntityManagerFactory("christoper"));
    service.execute((em)->{
      List<User> findUserList = em.createQuery("select u from User u").getResultList();//User 조회 쿼리 실행
      for(User findUser : findUserList){//User의 ManyToOne이 Lazy이기 때문에 N+1쿼리가 발생함.
        System.out.println("User: "+findUser+", Team: "+findUser.getTeam().getName());//N+1쿼리 예상.
      }

      //join fetch로 User조회시 team도 한번에 가져오도록 처리
      List<User> findUserList2 = em.createQuery("select u from User u join fetch u.team team").getResultList();
      for(User findUser : findUserList2){//User의 ManyToOne이 Lazy이기 때문에 N+1쿼리가 발생함.
        System.out.println("User: "+findUser+", Team: "+findUser.getTeam().getName());//N+1쿼리 예상.
      }
    });
  }
}
