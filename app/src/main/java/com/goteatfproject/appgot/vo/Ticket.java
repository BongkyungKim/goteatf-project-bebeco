package com.goteatfproject.appgot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Ticket {

  int ticketNo;
  String pay;
  Date paydate;
  int paycnt;
  boolean cancel;
  int totalcost;
  int mno;
  int eno;
  Event event;
  String title;

  Ticket(){
    this.ticketNo=0;
  }

}