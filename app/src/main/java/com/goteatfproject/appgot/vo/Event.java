package com.goteatfproject.appgot.vo;

import java.util.Date;
import java.util.List;
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
  private Date createDate;
  private String thumbnail;

//  private String calrander; 아래로 수정
  private String calendar;

  private Member writer;

  private List<AttachedFile> attachedFiles;

  public List<AttachedFile> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<AttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }
}
