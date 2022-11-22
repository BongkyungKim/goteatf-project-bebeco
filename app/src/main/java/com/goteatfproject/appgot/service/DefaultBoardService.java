package com.goteatfproject.appgot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goteatfproject.appgot.dao.BoardDao;
import com.goteatfproject.appgot.vo.Board;

@Service
public class DefaultBoardService implements BoardService {

  @Autowired
  BoardDao boardDao;


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

  // 마이페이지 메인 본인 작성 글 전체 출력
  @Override
  public List<Board> myListAll(int no) {
    return boardDao.findAll2(no);
  }

  @Override
  public List<Board> boardCount() throws Exception {
    return boardDao.BoardCount();
  }
}
