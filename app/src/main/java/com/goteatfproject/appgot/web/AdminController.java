package com.goteatfproject.appgot.web;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.goteatfproject.appgot.service.BoardService;
import com.goteatfproject.appgot.service.EventService;
import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.PageMaker;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  MemberService memberService;

  @Autowired
  PartyService partyService;

  @Autowired
  FeedService feedService;

  @Autowired
  EventService eventService;

  @Autowired
  BoardService boardService;

  // 관리자페이지 - 메인
  @GetMapping("/main")
  public String admin(Model model) throws Exception {
    model.addAttribute("parties", partyService.list());
    model.addAttribute("feeds", feedService.list());
    model.addAttribute("members", memberService.list());
    model.addAttribute("memberLists", memberService.MemberList());
    model.addAttribute("newMemberLists", memberService.NewMemberList());
    model.addAttribute("boards", boardService.listAll());
    model.addAttribute("boardCount", boardService.boardCount());
    model.addAttribute("newBoardCount", boardService.newBoardCount());
    return "admin/adminMain";
  }


  // 페이징 관리자페이지 파티게시글 관리
  @GetMapping("/adminPartyList")
  public ModelAndView adminPartyList(Criteria cri) throws Exception {

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);

    List<Map<String, Object>> adminPartyList = partyService.selectPartyList(cri);
    mv.addObject("adminPartyLists", adminPartyList);
    mv.addObject("pageMaker", pageMaker);

    System.out.println("adminPartyList = " + adminPartyList);

    mv.setViewName("admin/adminPartyList");
    //    model.addAttribute("parties", partyService.list());
    //    return "party/partyList";
    return mv;
  }

  // 관리자페이지 파티게시글 회원 작성 글 상세보기
  @GetMapping("/adminPartyListDetail")
  public String adminPartyListDetail(Model model, int no) throws Exception {

    model.addAttribute("party", partyService.getMyPartyListDetail(no));

    return "admin/adminPartyListDetail";
  }

  // 관리자페이지 파티게시글 비활성화 선택
  @GetMapping("/partyBlock")
  public String partyBlock(int no) throws Exception {
    partyService.partyBlock(no);
    return "redirect:adminPartyList";
  }

  // 관리자페이지 파티게시글 비활성화 선택
  @PostMapping("/partyBlocks")
  @ResponseBody
  public String partyBlocks(@RequestParam("checkedValue[]") int[] checkedValue) throws Exception {
    int valueLength = checkedValue.length;

    for(int i=0; i < valueLength; i++) {
      System.out.println(checkedValue[i]);
      partyService.partyBlock(checkedValue[i]);
    }
    return "비활성화 성공";
  }

  // 페이징 관리자페이지 피드게시글 관리
  @GetMapping("/adminFeedList")
  public ModelAndView adminFeedList(Criteria cri) throws Exception {

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);

    List<Map<String, Object>> adminFeedList = feedService.selectFeedList(cri);
    mv.addObject("adminFeedLists", adminFeedList);
    mv.addObject("pageMaker", pageMaker);

    System.out.println("adminFeedList = " + adminFeedList);

    mv.setViewName("admin/adminFeedList");

    return mv;
  }

  // 관리자페이지 피드게시글 회원 작성 글 상세보기
  @GetMapping("/adminFeedListDetail")
  public String adminFeedListDetail(Model model, int no) throws Exception {

    model.addAttribute("feed", feedService.getMyFeedListDetail(no));
    System.out.println("model.getAttribute(\"feed\") = " + model.getAttribute("feed"));

    return "admin/adminFeedListDetail";
  }

  // 관리자페이지 피드게시글 비활성화 선택
  @GetMapping("/feedBlock")
  public String feedBlock(int no) throws Exception {
    feedService.feedBlock(no);
    return "redirect:adminFeedList";
  }

  // 관리자페이지 피드게시글 비활성화 체크박스 선택
  @PostMapping("/feedBlocks")
  @ResponseBody
  public String feedBlocks(@RequestParam("checkedValue[]") int[] checkedValue) throws Exception {
    int valueLength = checkedValue.length;

    for(int i=0; i < valueLength; i++) {
      System.out.println(checkedValue[i]);
      feedService.feedBlock(checkedValue[i]);
    }

    return "비활성화 성공";
  }

  // 페이징 관리자페이지 이벤트게시글 관리
  @GetMapping("/adminEventList")
  public ModelAndView adminEventList(Criteria cri) throws Exception {

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);

    List<Map<String, Object>> adminEventList = eventService.selectEventList3(cri);
    mv.addObject("adminEventLists", adminEventList);
    mv.addObject("pageMaker", pageMaker);

    System.out.println("adminEventList = " + adminEventList);

    mv.setViewName("admin/adminEventList");

    return mv;
  }

  // 관리자페이지 이벤트게시글 상세보기
  @GetMapping("/adminEventListDetail")
  public String adminEventListDetail(Model model, int no) throws Exception {

    model.addAttribute("event", eventService.getAdminEventListDetail(no));
    System.out.println("model.getAttribute(\"event\") = " + model.getAttribute("event"));

    return "admin/adminEventListDetail";
  }

  // 관리자페이지 이벤트게시글 비활성화 선택
  @GetMapping("/eventBlock")
  public String eventBlock(int no) throws Exception {
    eventService.eventBlock(no);
    return "redirect:adminEventList";
  }

  // 관리자페이지 이벤트게시글 비활성화 선택
  @PostMapping("/eventBlocks")
  @ResponseBody
  public String eventBlocks(@RequestParam("checkedValue[]") int[] checkedValue) throws Exception {
    int valueLength = checkedValue.length;

    for(int i=0; i < valueLength; i++) {
      System.out.println(checkedValue[i]);
      eventService.eventBlock(checkedValue[i]);
    }

    return "비활성화 성공";
  }

  // 관리자페이지 - 회원 관리 + 검색기능 추가(keyword 파라미터로 받음)
  @GetMapping("/adminMemberList")
  public String adminMemberList(Model model, String keyword) throws Exception {
    // keyword null(빈값이면) keyword에 null을 넣어서 데이터 전체조회
    if (keyword == null) {
      keyword = null;
    }
    model.addAttribute("members", memberService.getSearchMember(keyword));
    model.addAttribute("keyword", keyword);
    return "admin/adminMemberList";
  }

  // 관리자페이지 회원 상세정보
  @GetMapping("/adminMemberDetail")
  public String adminMemberDetail(Model model, int no) throws Exception {
    model.addAttribute("member", memberService.getMemberDetail(no));
    return "admin/adminMemberDetail";
  }

  // 관리자페이지 회원 상세정보 비활성화 선택
  @GetMapping("/memberBlock")
  public String memberBlock(int no) throws Exception {
    memberService.memberBlock(no);
    return "redirect:adminMemberList";
  }

  //관리자페이지 회원 상세정보 활성화 선택
  @GetMapping("/memberActive")
  public String memberActive(int no) throws Exception {
    memberService.memberActive(no);
    return "redirect:adminMemberList";
  }
}
