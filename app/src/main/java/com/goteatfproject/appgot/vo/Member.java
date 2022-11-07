package com.goteatfproject.appgot.vo;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


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
  private String profile;
  private String intro;

}
