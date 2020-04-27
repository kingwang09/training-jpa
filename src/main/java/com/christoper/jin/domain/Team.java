package com.christoper.jin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @Class Team
 * @Description
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
@Getter
@NoArgsConstructor
@Entity
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "team")
  private List<User> userList = new LinkedList<User>();

  @Builder
  public Team(String name) {
    this.name = name;
  }
}
