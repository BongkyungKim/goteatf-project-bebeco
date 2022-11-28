package com.goteatfproject.appgot.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter @Setter @ToString
public class Notice {

  private int no;
  private String title;
  private String content;
  private String tname;
  private Date createDate;
  private Member writer;

}