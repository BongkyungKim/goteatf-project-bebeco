package com.goteatfproject.appgot.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 관리자페이지 파티, 피드, 이벤트 게시글을 담을 필드 설정
public class Board {
  int no;
  String tableName;
  String title;
  String cont;
  Date date;
}
