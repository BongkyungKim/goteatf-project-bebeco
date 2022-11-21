package com.goteatfproject.appgot.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter @ToString
public class Volunteer {

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date date;
  private Boolean state;
  private String etc;

  private int partyNo; // 컬럼 vno
  private int memberNo; // 컬럼 mno

  private Member writer;
  private int urlNo;

  private int fcfs; // 파티참여순서

}


