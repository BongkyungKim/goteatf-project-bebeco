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

  private int partyNo;

  private int memberNo;


  //  private int partyNo; // vno
  private Member writer;
  private int urlNo;

//  /////
}


