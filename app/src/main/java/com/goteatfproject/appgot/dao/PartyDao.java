package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartyDao {

  int insert(Party party);

  List<Party> findAll();
  // 페이징
  List<Map<String, Object>> selectPartyList(Criteria cri);

  Party findByNo(int no);

  int update(Party party);
  int delete(int no);

  int insertFiles(Party party);

  AttachedFile findFileByNo(int fileNo);

  List<AttachedFile> findFilesByParty(int partyNo);

  int deleteFile(int fileNo);

  int deleteFiles(int partyNo);

  int deleteFilesByMemberParties(int memberNo);

  // 테스트

}