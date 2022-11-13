package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.PageMaker;
import com.goteatfproject.appgot.vo.Party;
import java.util.List;
import java.util.Map;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MyController {

  PartyService partyService;
  FeedService feedService;
  MemberService memberService;

  public MyController(PartyService partyService,FeedService feedService, MemberService memberService) {
    this.partyService = partyService;
    this.feedService = feedService;
    this.memberService = memberService;
  }

  // 마이페이지
  @GetMapping("/main")
  public ModelAndView myPage(Criteria cri) throws Exception {
    // model.addAttribute("parties", myPageService.list());

    // 기존에는 return에서 보냈으면 mv에서는 여기서 보냄
//    ModelAndView mv = new ModelAndView("party/partyList");
    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);

    List<Map<String, Object>> myPartyList = partyService.selectPartyList(cri);
    mv.addObject("myPartyList", myPartyList);
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("mypage/myPartyList");
//    model.addAttribute("parties", partyService.list());
//    return "party/partyList";
    return mv;
//    return "mypage/myPartyList";
  }

  @GetMapping("/myPartyList")
  public ModelAndView myPartyList(Criteria cri) throws Exception {

    // 기존에는 return에서 보냈으면 mv에서는 여기서 보냄
//    ModelAndView mv = new ModelAndView("party/partyList");
    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);

    List<Map<String, Object>> myPartyList = partyService.selectPartyList(cri);
    mv.addObject("myPartyList", myPartyList);
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("mypage/myPartyList");
//    model.addAttribute("parties", partyService.list());
//    return "party/partyList";
    return mv;
  }


    @GetMapping("/myFeedList")
  public ModelAndView myFeedList(Criteria cri) throws Exception {

      ModelAndView mv = new ModelAndView();

      PageMaker pageMaker = new PageMaker();
      pageMaker.setCri(cri);
      pageMaker.setTotalCount(50);

      List<Map<String, Object>> myFeedList = feedService.selectFeedList(cri);
      mv.addObject("myFeedList", myFeedList);
      mv.addObject("pageMaker", pageMaker);

      mv.setViewName("mypage/myFeedList");

      return mv;
  }

  // 마이페이지- 개인 정보 수정
  @GetMapping("/myProfile")
  public String myProfile(Model model) throws Exception {
      model.addAttribute("members", memberService.list());
    return "mypage/myProfile";
  }

}
