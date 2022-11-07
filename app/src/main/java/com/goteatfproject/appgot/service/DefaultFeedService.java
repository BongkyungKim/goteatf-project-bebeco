package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.FeedDao;
import com.goteatfproject.appgot.dao.PartyDao;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.Party;
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
    // if(feed.getAttachedFiles().size() > 0) {
    // feedDao.insertFiles(feed);
    // }
  }
}
