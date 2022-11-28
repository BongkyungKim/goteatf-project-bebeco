package com.goteatfproject.appgot.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter @ToString
public class Event {

  private int no;
  private String title;
  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;
  private String location;
  private String address;
  private int viewCnt;
  private boolean pub;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createDate;
  private String thumbnail;

//  private String calrander;
  private String calendar;

  private int cost;


  private Member writer;

  private List<AttachedFile> attachedFiles;

  private EventComment commentList;

  public List<AttachedFile> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<AttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }


}