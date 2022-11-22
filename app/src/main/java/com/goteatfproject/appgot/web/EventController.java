package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.EventService;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Event;
import com.goteatfproject.appgot.vo.Member;
import com.goteatfproject.appgot.vo.PageMaker;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/event/")
public class EventController {

  EventService eventService;
  ServletContext sc;

  public EventController(EventService eventService, ServletContext sc) {
    System.out.println("EventController() 호출됨!!");
    System.out.println("ServletContext() 호출됨!!");
    this.eventService = eventService;
    this.sc = sc;
  }

  // 파티 게시판 페이징 적용
  @GetMapping("list")
  @ResponseBody
  public ModelAndView selectEventList(Event event, Criteria cri) throws Exception {

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(10);

    List<Map<String, Object>> list = eventService.selectEventList(cri);
    mv.addObject("list", list);
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("event/eventList");
    return mv;
  }

  // 파티 리스트 게시물 등록
  @GetMapping("form")
  public String eventFrom() throws Exception {
    return "event/eventAdd";
  }

  // 파티 리스트 게시물 등록 post
  @PostMapping("add")
  public String eventAdd(Event event, HttpSession session,
                         @RequestParam("files") MultipartFile[] files) throws Exception {

    // thumbnail default 파일 설정 TODO 추가1
    event.setThumbnail("defaultimage.jpg");

    event.setAttachedFiles(saveAttachedFiles(files));
    event.setWriter((Member) session.getAttribute("loginMember"));

    // 첨부파일 사이즈가 0 보다 크면 첨부파일 첫번째의 Filepath값 가져와서 thumbnail로 설정 TODO 추가2
    if (event.getAttachedFiles().size() > 0) {
      List<AttachedFile> attachedFiles = new ArrayList<>();
      attachedFiles = event.getAttachedFiles();
      event.setThumbnail(attachedFiles.get(0).getFilepath());
    }

    System.out.println("filename = " + Arrays.toString(files));
    System.out.println("filename2 = " + files);

    eventService.add(event);
    return "redirect:list";
  }

  private List<AttachedFile> saveAttachedFiles(Part[] files)
      throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/event/files");

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      attachedFiles.add(new AttachedFile(filename));
    }
    return attachedFiles;
  }

  private List<AttachedFile> saveAttachedFiles(MultipartFile[] files)
      throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/event/files");

    for (MultipartFile part : files) {
      if (part.isEmpty()) {
        continue;
      }

      System.out.println("filename3 = " + Arrays.toString(files));
      System.out.println("filename4 = " + files);
      System.out.println("dirPath = " + dirPath);

      String filename = UUID.randomUUID().toString();
      part.transferTo(new File(dirPath + "/" + filename));
      attachedFiles.add(new AttachedFile(filename));
    }
    return attachedFiles;
  }

  // 파티 게시물 상세보기
  @GetMapping("detail")
  public Map detail(int no) throws Exception {
    Event event = eventService.get(no);

    if (event == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    Map map = new HashMap();
    map.put("event", event);
    return map;
  }

  // 파티 게시물 수정
  @PostMapping("update")
  public String update(Event event, HttpSession session,
                       Part[] files) throws Exception {

    event.setAttachedFiles(saveAttachedFiles(files));

    // detail.html : <input name="no" type="number" value="1" th:value="${party.no}" readonly hidden/>
    // 위에 추가해야 party.getNo() 가져오기 가능 System.out.println("partyNo = " + party.getNo());
    checkOwner(event.getNo(), session);

    if (!eventService.update(event)) {
      throw new Exception("게시글을 변경할 수 없습니다.");
    }
    return "redirect:list";

  }

  private void checkOwner(int eventNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (eventService.get(eventNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {
    checkOwner(no, session);
    if (!eventService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }
    return "redirect:list";
  }

  @GetMapping("fileDelete")
  public String fileDelete(int no, HttpSession session) throws Exception {

    // 첨부파일 정보 가져오기
    AttachedFile attachedFile = eventService.getAttachedFile(no);

    System.out.println("attachedFile.getNo() = " + attachedFile.getNo());
    System.out.println("attachedFile.getNo() = " + attachedFile.getFilepath());
    System.out.println("attachedFile.getNo() = " + attachedFile.getEventNo());

    // 게시글 작성자 일치여부
    Member loginMember = (Member) session.getAttribute("loginMember");
    Event event = eventService.get(attachedFile.getEventNo());
    System.out.println("event = " + event);

    // partyVO의 getWriter 통해서 member getNo 접근 != 로그인No 와 일치여부
    if (event.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
    // 첨부파일 삭제
    if (!eventService.deleteAttachedFile(no)) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }
    return "redirect:detail?no=" + event.getNo();
  }


  //결제정보를 받아오는 컨트롤러
  @ResponseBody
  @GetMapping("ticketing")
  public String ticketing(@RequestParam HashMap<String, Object> map, HttpSession session) {

    Member member = (Member) session.getAttribute("loginMember");

    map.put("mno",member.getNo());
    System.out.println(map);
//    System.out.println(map.get("eno"));
//    System.out.println(map.get("paycnt"));
    boolean result = false;

    result = eventService.ticketing(map);

    if(result == true){
      return "1";
    }else {
      return "0";
    }
  }

  @GetMapping("test")
  public String test() {
    return "event/test";
  }
}

