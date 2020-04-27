package com.christoper.jin.domain;

import com.christoper.jin.exception.NotExistException;
import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Class User
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
@Getter
@NoArgsConstructor
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private RoleType roleType = RoleType.USER;

  @Lob
  private String description;

  private LocalDate enterDate;

  private LocalDateTime created;

  @Transient
  private String secretDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  private Team team;

  @Builder
  public User(String name, RoleType roleType, String description, LocalDate enterDate, LocalDateTime created, String secretDescription) {
    this.name = name;
    this.roleType = roleType;
    this.description = description;
    this.enterDate = enterDate;
    this.created = created;
    this.secretDescription = secretDescription;
  }

  /**
   * 팀 지정 메소드
   * - 연관관계의 주인은 User이므로
   * - 팀지정은 User를 통해서만 가능하도록
   * @param team
   */
  public void setTeam(Team team){
    if(team == null){
      throw new NotExistException("존재하지 않는 팀을 설정할 수 없습니다.");
    }

    this.team = team;

    if(!this.team.getUserList().contains(this)){
      this.team.getUserList().add(this);
    }
  }

  /**
   * 팀 해제 메소드
   */
  public void removeTeam(){
    List<User> userList = this.team.getUserList();
    if(userList != null && userList.contains(this)){
      userList.remove(this);
    }
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", roleType=" + roleType +
            ", description='" + description + '\'' +
            ", enterDate=" + enterDate +
            ", created=" + created +
            ", secretDescription='" + secretDescription + '\'' +
            '}';
  }
}
