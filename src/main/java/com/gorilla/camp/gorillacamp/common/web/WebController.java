package com.gorilla.camp.gorillacamp.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    /**
    * @name : 공통 uri 컨트롤러
    * @desc : 페이지 이동에만 쓰이는 컨트롤러 보안부분은 'SpringSecurityConfig.java' 에서 쓰임
    */
    @GetMapping("/gorilla/**")
    public String goPage(HttpServletRequest hsr){
        String pageUrl = hsr.getRequestURI();
        return pageUrl;
    }
}
