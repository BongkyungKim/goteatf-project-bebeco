package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Notice;

import java.util.List;

public interface NoticeService {

  List<Notice> list() throws Exception;

  Notice get(int no) throws Exception;

}
