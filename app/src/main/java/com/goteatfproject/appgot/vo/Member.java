package com.goteatfproject.appgot.vo;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Getter @Setter @ToString
public class Member {

  private int no;
  private String id;
  private String password;
  private String password2;
  private boolean grade;
  private String name;
  private String nick;
  private Date birth;
  private String tel;
  private String gender;
  private Date createdDate;
  private String post;
  private String address;
  private String subAddr;
  private String interest;
  private boolean outState;
  private Date outDate;
  private String thumbnail;
  private String intro;

  // 클라이언트 측에서 넘어온 파일 데이터를 저장하기 위한 파라미터 읽기용
  private MultipartFile file;

  // 프로필 파일을 위한 필드
  private int profileNo;
  private String profileName;
  private long profileSize;
  private String profileContentType;

}
