package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.FeedDao;
import com.goteatfproject.appgot.dao.PartyDao;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedAttachedFile;
import com.goteatfproject.appgot.vo.Party;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultFeedService implements FeedService {

  @Autowired
  FeedDao feedDao;

  @Override
  public List<Feed> list() throws Exception {
    return feedDao.findAll();
  }

  @Transactional
  @Override
  public void add(Feed feed) throws Exception {
    if(feedDao.insert(feed) == 0) {
      throw new Exception("게시글 등록 실패");
    }
    if(feed.getFeedAttachedFiles().size() > 0) {
      feedDao.insertFiles(feed);
    }
  }

  public List<Map<String, Object>> selectFeedList(Criteria cri) {
    return feedDao.selectFeedList(cri);
  }

  // 추가
  @Override
  public Feed get(int no) throws Exception {
    return feedDao.findByNo(no);
  }

  @Override
  public List<Feed> simpleProfile(int no) throws Exception {
    return feedDao.simpleProfile(no);
  }

  @Override
  public List<Feed> randomlist() throws Exception {
    return feedDao.randomfindAll();
  }

  @Override
  public List<Feed> followFindAll(int no) throws Exception {
    return feedDao.followFindAll(no);
  }

  @Override
  public List<Feed> selectListByNick(String nick) throws Exception {
    return feedDao.selectListByNick(nick);
  }

  @Transactional
  @Override
  public boolean update(Feed feed) throws Exception {

    if (feedDao.update(feed) == 0) {
      return false;
    }

//    if (feed.getFeedAttachedFiles().size() > 0) {
//      feedDao.insertFiles(feed);
//    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    feedDao.deleteFiles(no);
    return feedDao.delete(no) > 0;
  }

  public FeedAttachedFile getFeedAttachedFile(int fileNo) throws Exception {
    return feedDao.findFileByNo(fileNo);
  }

  public boolean deleteFeedAttachedFile(int fileNo) throws Exception {
    return feedDao.deleteFile(fileNo) > 0;
  }

  // 마이페이지 피드게시글 본인 작성 글 리스트
  @Override
  public List<Map<String, Object>> selectFeedListByNo(Map<String, Object> map) {
    return feedDao.selectFeedListByNo(map);
  }

  // 마이페이지 피드게시글 본인 작성 글 상세보기
  // 관리자페이지 피드게시글 회원 작성 글 상세보기
  @Override
  public Feed getMyFeedListDetail(int no) throws Exception {
    return feedDao.findByMyFeedListDetail(no);
  }

  // 관리자페이지 피드게시글 비활성화
  @Override
  public boolean feedBlock(int no) {
    return feedDao.feedBlock(no) > 0;
  }

  @Override
  public List<Feed> mainList() throws Exception {
    return feedDao.findAllMain();
  }

  // 마이페이지 피드게시글 강제삭제 — 1120 추가
  @Override
  public boolean allDelete2(int no) {
    return feedDao.allDelete2(no) > 0;
  }

  // 검색페이지 결과
  public List<Party> searchList(String keywordAll) throws Exception {
    return feedDao.findAllSearch(keywordAll);
  }
}
