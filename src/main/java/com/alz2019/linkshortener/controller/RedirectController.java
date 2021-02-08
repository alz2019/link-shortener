package com.alz2019.linkshortener.controller;

import com.alz2019.linkshortener.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RedirectController {
    private final LinkService linkService;

    public RedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/s/{str}")
    public void redirectToLink(HttpServletResponse response, @PathVariable("str") String str) throws IOException {
        response.sendRedirect(linkService.enlarge(str).getLongLink());
    }
}
