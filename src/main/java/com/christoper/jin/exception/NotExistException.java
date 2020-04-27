package com.christoper.jin.exception;

/**
 * @Class NotExistException
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
public class NotExistException extends RuntimeException{
  public NotExistException(String message) {
    super(message);
  }
}
