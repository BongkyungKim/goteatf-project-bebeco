package com.goteatfproject.appgot.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.goteatfproject.appgot.dao.PartyDao;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Party;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultPartyService implements PartyService {

  @Autowired
  PartyDao partyDao;

  @Transactional
  public void add(Party party) throws Exception {

    if (partyDao.insert(party) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    if (party.getAttachedFiles().size() > 0) {
      partyDao.insertFiles(party);
    }
  }

  @Override
  public List<Party> list() throws Exception {
    return partyDao.findAll();
  }

  @Override
  public Party get(int no) throws Exception {
    return partyDao.findByNo(no);
  }

  @Transactional
  @Override
  public boolean update(Party party) throws Exception {

    if (partyDao.update(party) == 0) {
      return false;
    }

    if (party.getAttachedFiles().size() > 0) {
      partyDao.insertFiles(party);
    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    partyDao.deleteFiles(no);
    return partyDao.delete(no) > 0;
  }

  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return partyDao.findFileByNo(fileNo);
  }

  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return partyDao.deleteFile(fileNo) > 0;
  }
}
