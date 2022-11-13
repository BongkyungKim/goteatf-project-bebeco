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

    if (feed.getFeedAttachedFiles().size() > 0) {
      feedDao.insertFiles(feed);
    }
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
}
