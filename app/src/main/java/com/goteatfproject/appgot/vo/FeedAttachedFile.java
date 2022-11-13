package com.goteatfproject.appgot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class FeedAttachedFile {

  private int no;
  private String fileName;
  private String filepath;
  private String saveName;
  private int feedNo;

  public FeedAttachedFile() {}

  // FeedController - feedAttachedFiles.add(new FeedAttachedFile(filename));
  public FeedAttachedFile(String filepath) {
    this.filepath = filepath;
  }

}
