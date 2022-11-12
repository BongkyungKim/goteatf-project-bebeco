package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;
import java.util.List;
import java.util.Map;

public interface PartyService {

  void add(Party party) throws Exception;
  List<Party> list() throws Exception;
  //페이징
  List<Map<String, Object>> selectPartyList(Criteria criteria);
  Party get(int no) throws Exception;
  boolean update(Party party) throws Exception;
  boolean delete(int no) throws Exception;
  AttachedFile getAttachedFile(int fileNo) throws Exception;
  boolean deleteAttachedFile(int fileNo) throws Exception;

  // 테스트
//  public void insertComment(Comment comment) throws Exception;


}
