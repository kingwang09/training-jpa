package com.christoper.jin.execute;

import com.christoper.jin.domain.Team;
import com.christoper.jin.domain.User;
import com.christoper.jin.service.BasicService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Class JPAMain
 * @Description 기본 데이터 생성
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
    basicService.execute((em)->{
      User u1 = User.builder()
              //.id(100L)
              .name("진형은-A")
              .enterDate(LocalDate.of(2017, 10, 23))
              .created(LocalDateTime.now())
              .build();
      User u2 = User.builder()
              //.id(100L)
              .name("진형은-B")
              .enterDate(LocalDate.of(2017, 10, 23))
              .created(LocalDateTime.now())
              .build();
      User u3 = User.builder()
              //.id(100L)
              .name("진형은-C")
              .enterDate(LocalDate.of(2017, 10, 23))
              .created(LocalDateTime.now())
              .build();

      //id generationType identity라서 persist할때 inser쿼리가 나갈 것.
      System.out.println("User 객체들 persist start.");
      em.persist(u1);
      em.persist(u2);
      em.persist(u3);
      System.out.println("User 객체들 persist end.");


      Team team = Team.builder()
              .name("서비스 개발 2랩")
              .build();
      Team team2 = Team.builder()
              .name("플랫폼 개발팀")
              .build();

      //id generationType identity라서 persist할때 inser쿼리가 나갈 것.
      System.out.println("Team 객체들 persist start.");
      em.persist(team);
      em.persist(team2);
      System.out.println("Team 객체들 persist end.");

      //update query가 커밋 시점에 나갈 것.
      System.out.println("User 객체들 team 지정 start.");
      u1.setTeam(team); //dirty checking
      u2.setTeam(team); //dirty checking
      u3.setTeam(team2);
      System.out.println("User 객체들 team 지정 end.");


      //JPQL실행시 flush가 발생하므로 이때 지연 쿼리 실행.
      List<User> findUserList = em.createQuery("select u from User u").getResultList();
      for(User findUser : findUserList){
        //OneToMany fetch Type이 Lazy일 경우 N+1 쿼리 발생됨.
          //1차캐시에 있다보니 N+1쿼리는 발생하지 않은듯.
        System.out.println("User: "+ findUser+", team: "+findUser.getTeam().getName());
      }
    });

    emf.close();
  }
}
