package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Comment;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PartyDao {

  int insert(Party party);

  List<Party> findAll();

  // 메서드의 파라미터가 여러 개 일 때,
  // - SQL에서 참조할 파라미터라고 애노테이션으로 표시해야한다
  // - 이때 SQL에서 참조할 이름도 지정해야 한다
  List<Party> findAll2(@Param("meal") String meal, @Param("food") String food);

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

  // 댓글 등록
  public void insertComment(Comment comment);

  // 댓글 리스트 출력
  public List<Comment> selectCommentList(Comment comment);

  // 댓글 수정
  int updateComment(Comment comment);

  // 댓글 삭제
  int deleteComment(int no);

}