package com.goteatfproject.appgot.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Comment {

  private int partyReplyNo; // 댓글 PK 번호
  private int partyNo; // 게시물 번호
  private String commentCont;
  private boolean pub;
  private Date date;
  private int memberNo; //

  private Member writer;

//  private Party partyPage;
}
