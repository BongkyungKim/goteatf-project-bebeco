package com.goteatfproject.appgot.service;

import java.util.List;

import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Notice;
import com.goteatfproject.appgot.vo.AttachedFile;
import java.util.List;
import java.util.Map;

public interface NoticeService {

  void add(Notice notice) throws Exception;

  boolean update(Notice notice) throws Exception;

  Notice get(int no) throws Exception;

  boolean delete(int no) throws Exception;

  List<Map<String, Object>> selectNoticeList(Criteria criteria);

}
