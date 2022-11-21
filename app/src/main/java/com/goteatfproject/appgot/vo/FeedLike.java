package com.goteatfproject.appgot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedLike {

  private int flno;

  private Member member;

  private Feed feed;

  private int flmno;
}
