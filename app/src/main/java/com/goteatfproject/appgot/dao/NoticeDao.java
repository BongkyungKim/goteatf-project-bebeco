package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeDao {

  int insert(Notice notice);

  Notice findByNo(int no);

  int update(Notice board);

  int delete(int no);

  List<Map<String, Object>> selectNoticeList(Criteria cri);

  int insertFiles(Notice notice);

  AttachedFile findFileByNo(int fileNo);



}
