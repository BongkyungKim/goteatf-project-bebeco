package com.goteatfproject.appgot.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.goteatfproject.appgot.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.goteatfproject.appgot.service.NoticeService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notice/")
public class NoticeController {

  ServletContext sc;
  NoticeService noticeService;

  public NoticeController(NoticeService noticeService, ServletContext sc) {
    System.out.println("NoticeController() 호출됨!");
    this.noticeService = noticeService;
    this.sc = sc;
  }

  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("add")
  public String add(
      Notice notice,
      HttpSession session) throws Exception {

    notice.setWriter((Member) session.getAttribute("loginMember"));

    noticeService.add(notice);
    return "redirect:list";
  }

  // 파티 게시판 페이징 적용
  @GetMapping("list")
  @ResponseBody
  public ModelAndView selectNoticeList(Notice notice, Criteria cri) throws Exception {

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(10);

    List<Map<String, Object>> list = noticeService.selectNoticeList(cri);
    mv.addObject("list", list);
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("notice/noticeList");
    return mv;
  }
  @GetMapping("detail")
  public Map detail(int no) throws Exception {
    Notice notice = noticeService.get(no);
    if (notice == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    Map map = new HashMap();
    map.put("notice", notice);

    return map;
  }

  @PostMapping("update")
  public String update(
      Notice notice,
      HttpSession session)
      throws Exception {

    checkOwner(notice.getNo(), session);

    if (!noticeService.update(notice)) {
      throw new Exception("게시글을 변경할 수 없습니다!");
    }

    return "redirect:list";
  }

  private void checkOwner(int noticeNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (noticeService.get(noticeNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  @GetMapping("delete")
  public String delete(
      int no,
      HttpSession session)
      throws Exception {

    checkOwner(no, session);
    if (!noticeService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }
    return "redirect:list";
  }
}


