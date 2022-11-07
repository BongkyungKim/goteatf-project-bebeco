package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDao {

  List<Notice> findAll();

  Notice findByNo(int no);

}
