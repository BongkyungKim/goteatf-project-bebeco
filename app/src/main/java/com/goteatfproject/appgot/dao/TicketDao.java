package com.goteatfproject.appgot.dao;


import com.goteatfproject.appgot.vo.Board;
import com.goteatfproject.appgot.vo.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketDao {
  // 관리자페이지 파티, 피드, 이벤트 게시글의 신규 게시글 조회
  List<Ticket> findByNo(int no);
}