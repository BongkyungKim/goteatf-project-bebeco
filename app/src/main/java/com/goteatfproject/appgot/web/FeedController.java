package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feed")
public class FeedController {

  FeedService feedService;

  public FeedController(FeedService feedService) {
    System.out.println("FeedController() 호출됨!");
    this.feedService = feedService;
  }

  @GetMapping("/list")
  public String list(Model model, HttpSession session) throws Exception {

    // 피드 팔롱 기능
//    Follower follower = new Follower();
//    follower.setFollow((int) session.getAttribute("loginMember"));
//
//
//    List<Follower> followList = followerService.selectFollowList(follower.getFollow());
//    model.addAttribute("follows", followList);

    // 피드 리스트 출력
    model.addAttribute("feeds", feedService.list());

    return "feed/Feed";

  }
  @GetMapping("/form")
  public void form() throws Exception {
  }

  @PostMapping("/form")
  public String add(Feed feed, HttpSession session) throws Exception {

//    feed.setWriter((Member) session.getAttribute("loginMember"));
//    System.out.println("1111111111 : " + feed.getWriter());

    feedService.add(feed);
    return "feed/Feed";
  }









}
