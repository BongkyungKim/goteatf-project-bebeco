package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedAttachedFile;
import com.goteatfproject.appgot.vo.Party;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface FeedDao {

  List<Feed> findAll();

  int insert(Feed feed);

  List<Map<String, Object>> selectFeedList(Criteria cri);

  // 추가
  List<Feed> selectListByNick(String nick);
  List<Feed> randomfindAll();

  List<Feed> followFindAll(int no);
  List<Feed> simpleProfile(int no);

  Feed findByNo(int no);

  int update(Feed feed);

  int delete(int no);

  int insertFiles(Feed feed);

  FeedAttachedFile findFileByNo(int fileNo);

  List<FeedAttachedFile> findFilesByParty(int feedNo);

  int deleteFile(int fileNo);

  int deleteFiles(int feedNo);

  int deleteFilesByMemberFeeds(int memberNo);
}