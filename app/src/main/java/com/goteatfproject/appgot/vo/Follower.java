package com.goteatfproject.appgot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Follower {

  private int follow;

  private int following;

  private String nick;

  private String intro;

  private String thumbnail;

}
