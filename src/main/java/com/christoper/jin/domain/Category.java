package com.christoper.jin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Class Category
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
@Getter
@NoArgsConstructor
@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="PARENT_ID")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> childList = new ArrayList<Category>();


  @OneToMany(mappedBy = "category")
  private List<CategoryItem> mappingItemList = new ArrayList<>();

  public void setParent(Category parentCategory){
    this.parent = parentCategory;

    if(!this.parent.getChildList().contains(this)) {
      this.parent.getChildList().add(this);
    }
  }

  public void removeParent(){
    if(this.parent.getChildList().contains(this)) {
      this.parent.getChildList().remove(this);
    }

    this.parent = null;
  }

  @Builder
  public Category(String name) {
    this.name = name;
  }
}
