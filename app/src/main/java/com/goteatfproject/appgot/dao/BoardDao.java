package com.goteatfproject.appgot.dao;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.goteatfproject.appgot.vo.Board;

@Mapper
public interface BoardDao {
  // 관리자페이지 파티, 피드, 이벤트 게시글의 신규 게시글 조회
  List<Board> findAll();

  List<Board> BoardCount();

  List<Board> newBoardCount();

  List<Board> findAll2(int no);
}
