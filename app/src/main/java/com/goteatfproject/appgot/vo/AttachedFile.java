package com.goteatfproject.appgot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AttachedFile {

  private int no;
  private String fileName;
  private String filepath;
  private String saveName;
  private int partyNo;
  private int eventNo;

  public AttachedFile() {}

  // PartyController - attachedFiles.add(new AttachedFile(filename));
  public AttachedFile(String filepath) {
    this.filepath = filepath;
  }

}
