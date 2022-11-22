package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeDao {

  int insert(Notice notice);

  Notice findByNo(int no);

  int update(Notice board);

  int delete(int no);


  List<Notice> findAll(
      @Param("keyword") String keyword,
      @Param("titleSort") String titleSort,
      @Param("startIndex") int startIndex,
      @Param("size") int size);

  int count(@Param("keyword") String keyword, @Param("titleSort") String titleSort);

  int insertFiles(Notice notice);

  AttachedFile findFileByNo(int fileNo);



}

