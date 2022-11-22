package com.goteatfproject.appgot.service;

import java.util.List;
import com.goteatfproject.appgot.vo.Notice;
import com.goteatfproject.appgot.vo.AttachedFile;
import java.util.List;

public interface NoticeService {

  void add(Notice notice) throws Exception;

  boolean update(Notice notice) throws Exception;

  Notice get(int no) throws Exception;

  boolean delete(int no) throws Exception;

  List<Notice> list(String keyword, String titleSort, int pageNo, int pageSize) throws Exception;

  int size(String keyword, String titleSort) throws Exception;

}

