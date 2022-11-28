package com.goteatfproject.appgot.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EventComment {

  private int reviewNo; // 댓글 PK 번호
  private int eventNo; // 게시물 번호
  private String commentCont;
  private boolean pub;
  private Date date;
  private int memberNo; //

  private Member writer;

//  private Party partyPage;
}
