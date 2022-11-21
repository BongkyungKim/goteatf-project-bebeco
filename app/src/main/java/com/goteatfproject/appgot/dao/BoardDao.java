package com.goteatfproject.appgot.dao;


import com.goteatfproject.appgot.vo.Board;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
  // 관리자페이지 파티, 피드, 이벤트 게시글의 신규 게시글 조회
  List<Board> findAll();

  List<Board> newBoardCount();
}
