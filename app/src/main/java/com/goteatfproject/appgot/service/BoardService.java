package com.goteatfproject.appgot.service;

import java.util.List;
import com.goteatfproject.appgot.vo.Board;

public interface BoardService {

  // 관리자 페이지 이벤트+피드+파티 게시글 조회
  List<Board> listAll() throws Exception;

  // 관리자 페이지 이벤트+피드+파티 게시글 조회
  List<Board> boardCount() throws Exception;

  // 관리자 페이지 이벤트+피드+파티 오늘 등록된 게시글 개수
  List<Board> newBoardCount() throws Exception;

  // 마이페이지 메인 본인 작성 글 전체 출력
  List<Board> myListAll(int no);
}
