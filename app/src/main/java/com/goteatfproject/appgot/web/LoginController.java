package com.goteatfproject.appgot.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.vo.Member;

@Controller
@RequestMapping("/auth")
public class LoginController {

  MemberService memberService;

  public LoginController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/login")
  public String login(@CookieValue(name="id", defaultValue = "") String id, Model model, HttpSession session) throws Exception {

    model.addAttribute("id", id);
    return "auth/login"; // TODO login 다시 복구
  }

  @PostMapping("/login")
  public String login(
      String id,
      String password,
      String saveEmail,
      String toURL, // TODO url2
      HttpServletResponse response,
      HttpSession session) throws Exception {

    Member member = memberService.get(id, password);

    System.out.println("password = " + password);
    //    System.out.println("userpassword = " + user.getPassword());

    if (member != null) {
      session.setAttribute("loginMember", member);
    }

    Cookie cookie = new Cookie("id", id);
    if (saveEmail == null) {
      cookie.setMaxAge(0);
    } else {
      cookie.setMaxAge(60 * 60 * 24 * 7);
      cookie.setPath("/");
    }

    response.addCookie(cookie);

    if (id.equals("admin@test.com")){
      return "redirect:/admin/main";
    }

    System.out.println("toURL ======> " + toURL);

    toURL = toURL==null || toURL.equals("") ? "/" : toURL;
//
    return "redirect:"+toURL;

//    return "redirect:/";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}
