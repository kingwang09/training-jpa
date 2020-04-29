package com.christoper.jin.domain;

import com.christoper.jin.constant.DeliveryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Class Delivery
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
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String city;

  private String street;

  private String zipCode;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus deliveryStatus = DeliveryStatus.IN_PROGRESS;

  @Setter
  @OneToOne(mappedBy = "delivery")
  private Order order;

  public Delivery(String city, String street, String zipCode, DeliveryStatus deliveryStatus) {
    this.city = city;
    this.street = street;
    this.zipCode = zipCode;
    this.deliveryStatus = deliveryStatus;
  }
}
