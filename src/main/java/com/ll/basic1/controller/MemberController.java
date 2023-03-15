package com.ll.basic1.controller;

import com.ll.basic1.model.Member;
import com.ll.basic1.model.User;
import com.ll.basic1.model.userDto;
import com.ll.basic1.service.MemberService;
import com.ll.basic1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class MemberController {

    private final MemberService memberService;
    private final UserService userService;
    private final List<User> cookie;

    @GetMapping("/addPerson")
    public String add(@RequestParam("name") String name, @RequestParam("age") Long age) {
        return memberService.add(new Member(name, age));
    }

    @GetMapping("/removePerson")
    public String delete(@RequestParam("id") Long id) {
        return memberService.delete(id);
    }

    @GetMapping("/modifyPerson")
    public String modify(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("age") Long age) {
        return memberService.modify(id, name, age);
    }

    @GetMapping("/people")
    public List<Member> list() {
        return memberService.list();
    }

    @GetMapping("/reqAndResp")
    public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int age = Integer.parseInt(req.getParameter("age").replaceAll(" ", ""));
        resp.getWriter().append(String.format("Hello, you are %d years old.",age));
    }

    @GetMapping("/reqAndRespV2")
    public String showReqAndRespV2(int age) {
        return String.format("Hello, you are %d years old.",age);
    }

    @GetMapping("/cookie/increase")
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException { // 리턴되는 int 값은 String 화 되어서 고객(브라우저)에게 전달된다.
        int countInCookie = 0;

        if (req.getCookies() != null) {
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }
        resp.addCookie(new Cookie("count", (countInCookie + 1) + ""));

        return countInCookie + 1;
    }

    @PostMapping("/member/login")
    public userDto login(@RequestParam("user") String username, @RequestParam("pwd") Long pwd) {
        User user = new User(username, pwd);
        if (cookie.size() > 1) {
            return new userDto("F-1", "로그아웃 하십시오.");
        }
        cookie.add(user);
        return userService.add(user);
    }

    @GetMapping("/member/me")
    public userDto search() {
        if (cookie.size() > 0){
            return new userDto("S-1", String.format("당신의 username(은)는 %s 입니다.", cookie.get(0).getUsername()));
        }
        return new userDto("F-1", "로그인 후 이용해주세요.");
    }

    @GetMapping("/member/loginlist")
    public List<User> userList() {
        return userService.list();
    }
}
