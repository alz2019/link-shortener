package com.alz2019.linkshortener.controller;

import com.alz2019.linkshortener.model.Link;
import com.alz2019.linkshortener.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/short")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Link shorten(@RequestParam("long_link") String longLink) {
        return linkService.shorten(longLink);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public Link enlarge(@RequestParam("short_link") String shortLink) {
        return linkService.enlarge(shortLink);
    }
}
