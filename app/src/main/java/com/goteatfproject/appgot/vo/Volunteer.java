package com.goteatfproject.appgot.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Volunteer {


  private Date date;
  private Boolean state;
  private String etc;

  private int partyNo; // 컬럼 vno
  private int memberNo; // 컬럼 mno

  private Member writer;
  private int urlNo;

}


