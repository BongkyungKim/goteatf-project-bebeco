package com.goteatfproject.appgot.web;

import com.fasterxml.jackson.core.JsonToken;
import com.goteatfproject.appgot.service.FeedLikeService;
import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.FollowerService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.vo.*;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/feed")
public class FeedController {

  FeedService feedService;

  ServletContext sc;

  FollowerService followerService;

  MemberService memberService;

  @Autowired
  FeedLikeService feedLikeService;

  public FeedController(FeedService feedService, ServletContext sc, FollowerService followerService,
      MemberService memberService, FeedLikeService feedLikeService) {
    System.out.println("FeedController() 호출됨!");
    this.feedService = feedService;
    this.sc = sc;
    this.followerService = followerService;
    this.memberService = memberService;
    this.feedLikeService = feedLikeService;
  }

  @ResponseBody
  @PostMapping("/heart")
  public Map<String, Integer> heart(@RequestParam("feedNo") int feedNo, @RequestParam("heart") int heart, HttpSession session, Model model) throws Exception {

    Member member = (Member) session.getAttribute("loginMember");
    Feed feed = feedService.get(feedNo);

    FeedLike feedLike = new FeedLike();

    feedLike.setFeed(feedService.get(feedNo));
    feedLike.setMember(memberService.get(member.getNo()));

    System.out.println(heart);

    if(heart >= 1) {
      feedLikeService.deleteLike(member, feed);
      heart=0;
    } else {
      feedLikeService.insertLike(member, feed);
      heart=1;
    }

    int count = feedLikeService.getLike(member, feed);

    Map<String, Integer> map = new HashMap<>();
    map.put("feedNo", feedNo);
    map.put("count", count);
    map.put("heart", heart);
    return map;

  }

  @GetMapping("/personal")
  public String personalList(String nick, Model model, HttpSession session) throws Exception {
    Object object = session.getAttribute("loginMember");
    Member loginMember = (Member)object;


    // 한 유저의 게시물 출력 홈페이지
    System.out.println("dd=====");
    // 아이디로 회원 정보 조회
    Member member = memberService.profileByNick(nick);
    System.out.println("member1 =======> " + member.getNick());
    if(nick == null) {
      member = memberService.profileByNick(loginMember.getNick());
      System.out.println("member2=====>" + member);
    }

    System.out.println(member);
    // 로그인한 회원 정보 담기

    // 개인페이지의 유저 번호 가져오기
    int memberNo = member.getNo();
    // 로그인 회원 유저 번호 가져오기
    int loginMemberNo = loginMember.getNo();

    // 팔로우 객체 생성
    Follower follower = new Follower();
    follower.setFollow(loginMemberNo);
    follower.setFollowing(memberNo);

    // 팔로우 유무 체크
    int followCheck = followerService.isFollow(follower);

    // 나를 팔로우하는 유저들의 목록
    List<Follower> followerList = followerService.selectFollowingList(memberNo);
    // 내가 팔로우하는 사람들의 목록
    List<Follower> followeringList = followerService.selectFollowList(memberNo);

    // 좋아요 표시
    FeedLike feedLike = new FeedLike();
    feedLike.setMember(loginMember);

    int likeCheck = feedLikeService.isLike(feedLike);

    List<Feed> feedList = feedService.selectListByNick(nick);
    if (nick == null) {
      feedList = feedService.selectListByNick(loginMember.getNick());
    }



    List<Feed> feedList2 = new ArrayList<>();

    for (Feed feed : feedList) {
      feed.setLikeCnt(feedLikeService.getLike(member, feed));
      for (FeedLike feedLikeList : feed.getFeedLikeList()) {
        if (feedLikeList.getFlmno() == loginMember.getNo()) {
          feed.setCheckLike("is");
          break;
        } else {
          feed.setCheckLike("not");
        }

      }
      feedList2.add(feed);
    }

    // 사용자 아이디로 사용자 번호를 조회해서 그 번호로 게시물 땡겨옴
    model.addAttribute("list", feedList2);
    // 사용자 아이디로 회원의 모든 정보 조회하기
    model.addAttribute("member", member);
    // 팔로우 체크 유무
    model.addAttribute("followCheck", followCheck);

    // 좋아요 체크 유무
    model.addAttribute("likeCheck", likeCheck);

    model.addAttribute("followerList", followerList);
    model.addAttribute("followeringList", followeringList);

    return "feed/feedPersonal";

  }

  @GetMapping("/personal-ajax")
  public String personalAjax(int followNo, Model model, HttpSession session) throws Exception {

    System.out.println(followNo);
    Member follow = (Member)session.getAttribute("loginMember");
    Member following = memberService.profileByNo(followNo);

    Follower follower = new Follower();
    follower.setFollow(follow.getNo());
    follower.setFollowing(following.getNo());

    if(followerService.isFollow(follower) == 1) {
      followerService.unfollow(follower);
    } else {
      followerService.follow(follower);
    }

    model.addAttribute("member", following);
    model.addAttribute("member", follow);

    return "feed/feedPersonal";
  }

  //  @GetMapping("/list")
//  public String list(Model model, HttpSession session) throws Exception {
//
//    Member loginMember = (Member) session.getAttribute("loginMember");
//
//    // 피드 팔로우 기능
//    if(loginMember != null) {
//      List<Follower> followList = followerService.selectFollowList(loginMember.getNo());
//      model.addAttribute("follows", followList);
//      model.addAttribute("members", memberService.randomList());
//    } else {
//      model.addAttribute("members", memberService.randomList());
//    }
//
//    // 피드 리스트 출력
//    if(loginMember != null) {
//      model.addAttribute("followfeeds", feedService.followFindAll(loginMember.getNo()));
//    } else {
//      model.addAttribute("feeds", feedService.randomlist());
//    }
//
//    // 로그인멤버 간단 프로필 출력
//    if(loginMember != null) {
//      model.addAttribute("simples", feedService.simpleProfile(loginMember.getNo()));
//    } else {} // -> 로그인을 안했으면 css 히든 넣기 -> 근데 방법을 모름
//
//    return "feed/feedList";
//  }


  @GetMapping("/list")
  public String list(Model model, HttpSession session) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    // 피드 팔로우 기능
    if(loginMember != null) {
      List<Follower> followList = followerService.selectFollowList(loginMember.getNo());
      model.addAttribute("follows", followList);
      model.addAttribute("members", memberService.randomList());
    } else {
      model.addAttribute("members", memberService.randomList());
    }

    // 피드 리스트 출력
    if(loginMember != null) {

      // 피드 좋아요 기능
      FeedLike feedLike = new FeedLike();
      feedLike.setMember(loginMember);

      int likeCheck = feedLikeService.isLike(feedLike);

      List<Feed> followfeeds = feedService.followFindAll(loginMember.getNo());
      List<Feed> followfeeds2 = new ArrayList<>();

      for (Feed feed : followfeeds) {
        System.out.println("체크! " + feed);
        feed.setLikeCnt(feedLikeService.getLike(loginMember, feed));
        for (FeedLike feedLikeList : feed.getFeedLikeList()) {
          System.out.println("피드에 넘버" + feedLikeList.getFlmno());
          System.out.println("로그인 넘버" + loginMember.getNo());

          if (feedLikeList.getFlmno() == loginMember.getNo()) {
            feed.setCheckLike("is");
            System.out.println("이즈?" + feed.getCheckLike());
            break;
          } else {
            feed.setCheckLike("not");
            System.out.println("not?" + feed.getCheckLike());
          }

        }
        followfeeds2.add(feed);
      }
      model.addAttribute("likeCheck", likeCheck);
      model.addAttribute("followfeeds", followfeeds2);

    } else {
      model.addAttribute("feeds", feedService.randomlist());
    }

    // 로그인멤버 간단 프로필 출력
    if(loginMember != null) {
      model.addAttribute("simples", feedService.simpleProfile(loginMember.getNo()));
    } else {} // -> 로그인을 안했으면 css 히든 넣기 -> 근데 방법을 모름

    return "feed/feedList";
  }

  @GetMapping("/form")
  public String form() throws Exception {
    return "feed/feedForm";
  }

  @PostMapping("/add")
  public String feedAdd(Feed feed, HttpSession session,
      @RequestParam("files") MultipartFile[] files) throws Exception {

    // thumbnail default 파일 설정
    feed.setThumbnail("logo.png");

    feed.setFeedAttachedFiles(saveFeedAttachedFiles(files));
    feed.setWriter((Member) session.getAttribute("loginMember"));

    // 첨부파일 사이즈가 0 보다 크면 첨부파일 첫번째의 Filepath값 가져와서 thumbnail로 설정
    if (feed.getFeedAttachedFiles().size() > 0) {
      List<FeedAttachedFile> feedAttachedFiles = new ArrayList<>();
      feedAttachedFiles = feed.getFeedAttachedFiles();
      feed.setThumbnail(feedAttachedFiles.get(0).getFilepath());
    }

    System.out.println("filename = " + Arrays.toString(files));
    System.out.println("filename2 = " + files);
    feedService.add(feed);
    return "redirect:list";
  }

  private List<FeedAttachedFile> saveFeedAttachedFiles(Part[] files)
      throws IOException, ServletException {
    List<FeedAttachedFile> feedAttachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/feed/files");

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      feedAttachedFiles.add(new FeedAttachedFile(filename));
    }
    return feedAttachedFiles;
  }

  private List<FeedAttachedFile> saveFeedAttachedFiles(MultipartFile[] files)
      throws IOException, ServletException {
    List<FeedAttachedFile> feedAttachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/feed/files");

    for (MultipartFile part : files) {
      if (part.isEmpty()) {
        continue;
      }

      String filename = UUID.randomUUID().toString();
      part.transferTo(new File(dirPath + "/" + filename));
      feedAttachedFiles.add(new FeedAttachedFile(filename));
    }
    return feedAttachedFiles;
  }

  // 파티 게시물 상세보기
  @GetMapping("detail")
  public Map<String, Object> detail(int no) throws Exception {

    Feed feed = feedService.get(no);

    if (feed == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    Map<String, Object> map = new HashMap<>();
    map.put("feed", feed);
    return map;
  }

  @GetMapping("/updateForm")
  public String updateForm(int no, Model model) throws Exception {
    Feed feed = feedService.get(no);
    if(feed == null) {
      throw new Exception("해당 게시글이 없습니다.");
    }
    model.addAttribute("feed", feed);
    return "feed/feedUpdate";
  }

  // 피드 게시물 수정
  @PostMapping("update")
  public String update(Feed feed, Model model, HttpSession session) throws Exception {

    System.out.println("session4 =---> " + session.getAttribute("loginMember"));

    String nick = URLEncoder.encode(feed.getWriter().getNick(), "UTF-8");
    System.out.println("nick = " + nick);

    checkOwner(feed.getNo(), session);

    if (!feedService.update(feed)) {
      throw new Exception("게시글을 변경할 수 없습니다.");
    }
    return "redirect:personal?nick=" + nick;
  }

  private void checkOwner(int feedNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");

    if (feedService.get(feedNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {

//    String nick = feedService.get(no).getWriter().getNick();
    String nick = URLEncoder.encode(feedService.get(no).getWriter().getNick(), "UTF-8");
    System.out.println("deleteNick ====> " + nick);
    checkOwner(no, session);
    feedService.delete(no);

//    return "redirect:personal";
    return "redirect:personal?nick=" + nick;
  }

  @GetMapping("fileDelete")
  public String fileDelete(int no, HttpSession session) throws Exception {

    // 첨부파일 정보 가져오기
    FeedAttachedFile feedAttachedFile = feedService.getFeedAttachedFile(no);

    System.out.println("feedAttachedFile.getNo() = " + feedAttachedFile.getNo());
    System.out.println("feedAttachedFile.getNo() = " + feedAttachedFile.getFilepath());
    System.out.println("feedAttachedFile.getNo() = " + feedAttachedFile.getFeedNo());


    // 게시글 작성자 일치여부
    Member loginMember = (Member) session.getAttribute("loginMember");
    Feed feed = feedService.get(feedAttachedFile.getFeedNo());
    System.out.println("feed = " + feed);

    // feedVO의 getWriter 통해서 member getNo 접근 != 로그인No 와 일치여부
    if (feed.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
    // 첨부파일 삭제
    if (!feedService.deleteFeedAttachedFile(no)) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }
    return "redirect:detail?no=" + feed.getNo();
  }

}





