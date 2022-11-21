package com.goteatfproject.appgot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goteatfproject.appgot.service.NoticeService;
import com.goteatfproject.appgot.service.PartyService;

@Controller
@RequestMapping("/notice")

public class NoticeController {

  NoticeService noticeService;
  PartyService partyService;

  public NoticeController(NoticeService noticeService,PartyService partyService) {
    System.out.println("NoticeController() 호출됨!");
    this.noticeService = noticeService;
    this.partyService = partyService;
  }

  //  public NoticeController(PartyService partyService) {
  //    System.out.println("PartyService() 호출됨!");
  //    this.partyService = partyService;
  //  }

  //  @GetMapping ("/add")
  //  public String add() throws Exception {
  //    return "member/form";
  //  }

  //  @PostMapping("/save")
  //  public String save(User user) throws Exception {
  //    return "memberInfo";
  //  }
  //
  //  @PostMapping("/add")
  //  public String add(User user) throws Exception {
  //    memberService.add(user);
  //    return "redirect:list";
  //  }

  @GetMapping("/list")
  public String list(Model model) throws Exception {
    model.addAttribute("notices", noticeService.list());
    return "notice/noticeList";
  }

  //  @GetMapping("/detail")
  //  public Map detail(int no) throws Exception {
  //    Notice notice = noticeService.get(no);
  //    if (notice == null) {
  //      throw new Exception("해당 번호의 게시글이 없습니다");
  //    }
  //    Map map = new HashMap();
  //    map.put("notice", notice);
  //    return map;
  //  }

  // 1:1 문의 리스트
  @GetMapping("/noticeOne")
  public String oneList(Model model) throws Exception {
    model.addAttribute("noticesOne", noticeService.list());
    return "notice/noticeOne";
  }

  // 1:1 문의 등록
  //  @PostMapping("/noticeAdd")
  //  public String oneAdd(
  //      Notice notice,
  //      @RequestParam("files") MultipartFile[] files,
  //      HttpSession session) throws Exception {
  //
  //    board.setAttachedFiles(saveAttachedFiles(files));
  //    board.setWriter((Member) session.getAttribute("loginMember"));
  //
  //    // 서비스 객체에 업무를 맡긴다.
  //    boardService.add(board);
  //    return "redirect:list";
  //  }


}
