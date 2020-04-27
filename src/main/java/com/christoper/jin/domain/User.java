package com.christoper.jin.domain;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Builder
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

  public User(String name, RoleType roleType, String description, LocalDate enterDate, LocalDateTime created, String secretDescription) {
    this.name = name;
    this.roleType = roleType;
    this.description = description;
    this.enterDate = enterDate;
    this.created = created;
    this.secretDescription = secretDescription;
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
