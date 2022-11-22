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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.goteatfproject.appgot.vo.Member;
import com.goteatfproject.appgot.vo.Notice;
import com.goteatfproject.appgot.service.NoticeService;

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


  @GetMapping("list")
  public String list(
      String keyword,
      String titleSort,
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "3") int pageSize,
      Model model) throws Exception {
    if (keyword != null && keyword.length() == 0) {
      keyword = null;
    }
    if (titleSort != null && titleSort.length() == 0) {
      titleSort = null;
    }

    if (pageSize < 3) {
      pageSize = 3;
    } else if (pageSize > 7) {
      pageSize = 7;
    }

    int count = noticeService.size(keyword, titleSort);

    int maxPageNo = count / pageSize;
    if ((count % pageSize) > 0) {
      maxPageNo++;
    }

    if (pageNo < 1) {
      pageNo = 1;
    } else if (pageNo > maxPageNo) {
      pageNo = maxPageNo;
    }

    model.addAttribute("notices", noticeService.list(keyword, titleSort, pageNo, pageSize));
    model.addAttribute("keyword", keyword);
    model.addAttribute("titleSort", titleSort);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("maxPageNo", maxPageNo);
    model.addAttribute("pageSize", pageSize);

    return "notice/noticeList";
  }

  @RequestMapping("list-ajax")
  public void listAjax(
      String keyword,
      String titleSort,
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "3") int pageSize,
      Model model) throws Exception {
    if (keyword != null && keyword.length() == 0) {
      keyword = null;
    }
    if (titleSort != null && titleSort.length() == 0) {
      titleSort = null;
    }

    if (pageSize < 3) {
      pageSize = 3;
    } else if (pageSize > 7) {
      pageSize = 7;
    }

    int count = noticeService.size(keyword, titleSort);

    int maxPageNo = count / pageSize;
    if ((count % pageSize) > 0) {
      maxPageNo++;
    }

    if (pageNo < 1) {
      pageNo = 1;
    } else if (pageNo > maxPageNo) {
      pageNo = maxPageNo;
    }
    if (pageNo == 0) {
      pageNo = 1;
    }


    System.out.println("=====> " + pageNo);
    model.addAttribute("notices", noticeService.list(keyword, titleSort, pageNo, pageSize));
    model.addAttribute("keyword", keyword);
    model.addAttribute("titleSort", titleSort);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("maxPageNo", maxPageNo);
    model.addAttribute("pageSize", pageSize);
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



