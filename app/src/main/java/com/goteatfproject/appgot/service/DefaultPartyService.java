package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.BoardDao;
import com.goteatfproject.appgot.dao.PartyDao;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Board;
import com.goteatfproject.appgot.vo.Comment;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultPartyService implements PartyService {

  @Autowired
  PartyDao partyDao;

  @Autowired
  BoardDao boardDao;

  @Transactional
  public void add(Party party) throws Exception {

    if (partyDao.insert(party) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    if (party.getAttachedFiles().size() > 0) {
      partyDao.insertFiles(party);
    }
  }

  @Override
  public List<Party> list() throws Exception {
    return partyDao.findAll();
  }

  // 파티게시판 - 카테고리
  @Override
  public List<Party> list2(String meal, String food) throws Exception {
    return partyDao.findAll2(meal, food);
  }

  //페이징
  public List<Map<String, Object>> selectPartyList(Criteria cri) {
    return partyDao.selectPartyList(cri);
  }

  @Override
  public Party get(int no) throws Exception {
    partyDao.updatePartyCount(no);
    return partyDao.findByNo(no);
  }
  @Override
  public Party get2(int no) throws Exception {
    return partyDao.findByNo2(no);
  }

  // 마이페이지 파티게시글 수정
  @Transactional
  @Override
  public boolean update(Party party) throws Exception {

    if (partyDao.update(party) == 0) {
      return false;
    }

    if (party.getAttachedFiles().size() > 0) {
      partyDao.insertFiles(party);
    }
    return true;
  }

  // 파티게시판 파티게시글 수정
  @Transactional
  @Override
  public boolean update2(Party party) throws Exception {

    if (partyDao.update2(party) == 0) {
      return false;
    }

    if (party.getAttachedFiles().size() > 0) {
      partyDao.insertFiles(party);
    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    partyDao.deleteFiles(no);
    return partyDao.delete(no) > 0;
  }

  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return partyDao.findFileByNo(fileNo);
  }

  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return partyDao.deleteFile(fileNo) > 0;
  }

  // 관리자페이지 이벤트+피드+파티 게시글 조회
  @Override
  public List<Board> listAll() throws Exception {
    System.out.println("newBoardDao = " + boardDao.findAll());
    return boardDao.findAll();
  }

  // 관리자 페이지 이벤트+피드+파티 오늘 등록된 게시글 개수
  @Override
  public List<Board> newBoardCount() throws Exception {
    return boardDao.newBoardCount();
  }

  // 마이페이지 파티게시글 본인 글 리스트
  @Override
  public List<Map<String, Object>> selectPartyListByNo(Map<String, Object> map) {
    return partyDao.selectPartyListByNo(map);
  }

  // 마이페이지 파티게시글 본인 작성 글 상세보기
  // 관리자페이지 파티게시글 회원 작성 글 상세보기
  @Override
  public Party getMyPartyListDetail(int no) throws Exception {
    return partyDao.findByMyPartyListDetail(no);
  }

  // 관리자페이지 파티게시글 비활성화
  @Override
  public boolean partyBlock(int no) throws Exception {
    return partyDao.partyBlock(no) > 0;
  }

  // 댓글 등록
  @Override
  public void insertComment(Comment comment) throws Exception {
    partyDao.insertComment(comment);
  }

  // 댓글 리스트 출력
  @Override
  public List<Comment> getCommentList(Comment comment) throws Exception {
    return partyDao.selectCommentList(comment);
  }

  // 댓글 수정
  @Override
  public boolean updateComment(Comment comment) throws Exception {
    return partyDao.updateComment(comment) != 0; // 넘어오는 값이 0이 아니면 true, 0이면 false
  }

  // 댓글 삭제
  @Override
  public boolean deleteComment(int no) throws Exception {
    return partyDao.deleteComment(no) > 0;
  }

  //메인페이지에서 뽑는 파티리스트
  @Override
  public List<Party> mainList() throws Exception {
    return partyDao.findAllMain();
  }

  //메인페이지에서 뽑는 파티리스트(String 받기)
  @Override
  public List<Party> mainList(String meal, String food) throws Exception {
    return partyDao.findAllMain(meal, food);
  }

  //마이페이지 파티게시글 연쇄삭제
  @Override
  public boolean allDelete(int no) {
    return partyDao.allDelete(no) > 0;
  }

  public List<Party> searchList(String keywordAll) throws Exception {
    return partyDao.findAllSearch(keywordAll);
  }
}
