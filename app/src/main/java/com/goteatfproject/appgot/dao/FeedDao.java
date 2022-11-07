package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.Party;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface FeedDao {

  List<Feed> findAll();

  int insert(Feed feed);

}