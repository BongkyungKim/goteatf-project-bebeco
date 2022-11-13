package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.FollowerService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedAttachedFile;
import com.goteatfproject.appgot.vo.Follower;
import com.goteatfproject.appgot.vo.Member;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/feed")
public class FeedController {

  FeedService feedService;

  ServletContext sc;

  FollowerService followerService;

  MemberService memberService;

  public FeedController(FeedService feedService, ServletContext sc, FollowerService followerService,
      MemberService memberService) {
    System.out.println("FeedController() 호출됨!");
    this.feedService = feedService;
    this.sc = sc;
    this.followerService = followerService;
    this.memberService = memberService;
  }

  @GetMapping("/personal")
  public String personalList(String nick, Model model, HttpSession session) throws Exception {
    // 한 유저의 게시물 출력 홈페이지
    System.out.println(nick);
    // 아이디로 회원 정보 조회
    Member member = memberService.profileByNick(nick);
    System.out.println(member);
    // 로그인한 회원 정보 담기
    Object object = session.getAttribute("loginMember");
    Member loginMember = (Member)object;

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

    // 사용자 아이디로 사용자 번호를 조회해서 그 번호로 게시물 땡겨옴
    model.addAttribute("list", feedService.selectListByNick(nick));
    // 사용자 아이디로 회원의 모든 정보 조회하기
    model.addAttribute("member", member);
    // 팔로우 체크 유무
    model.addAttribute("followCheck", followCheck);

    model.addAttribute("followerList", followerList);
    model.addAttribute("followeringList", followeringList);

    return "feed/feedPersonal";

  }

  @GetMapping("/list")
  public String list(Model model, HttpSession session) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    // 피드 팔로우 기능
    if(loginMember != null) {
      List<Follower> followList = followerService.selectFollowList(loginMember.getNo());
      model.addAttribute("follows", followList);
    } else {
      model.addAttribute("members", memberService.randomList());
    }

    // 피드 리스트 출력
    if(loginMember != null) {
      model.addAttribute("followfeeds", feedService.followFindAll(loginMember.getNo()));
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
  public void form() throws Exception {
  }

  @PostMapping("/add")
  public String feedAdd(Feed feed, HttpSession session,
      @RequestParam("files") MultipartFile[] files) throws Exception {

    feed.setFeedAttachedFiles(saveFeedAttachedFiles(files));
    feed.setWriter((Member) session.getAttribute("loginMember"));

//    List<FeedAttachedFile> feedAttachedFiles = new ArrayList<>();
//    feedAttachedFiles = feed.getFeedAttachedFiles();
//    feed.setImage(feedAttachedFiles.);

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
  public Map detail(int no) throws Exception {

    Feed feed = feedService.get(no);

    if (feed == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    Map map = new HashMap();
    map.put("feed", feed);
    return map;
  }

  // 파티 게시물 수정
  @PostMapping("update")
  public String update(Feed feed, HttpSession session,
      Part[] files) throws Exception {

    feed.setFeedAttachedFiles(saveFeedAttachedFiles(files));

//     detail.html : <input name="no" type="number" value="1" th:value="${party.no}" readonly hidden/>
//     위에 추가해야 party.getNo() 가져오기 가능 System.out.println("partyNo = " + party.getNo());
    checkOwner(feed.getNo(), session);

    if (!feedService.update(feed)) {
      throw new Exception("게시글을 변경할 수 없습니다.");
    }
    return "feed/feedList";
  }

  private void checkOwner(int feedNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");

    if (feedService.get(feedNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {
    checkOwner(no, session);
    if (!feedService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }
    return "feed/feedList";
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
