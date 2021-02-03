package com.alz2019.linkshortener.service;

import com.alz2019.linkshortener.model.Link;
import com.alz2019.linkshortener.repository.LinkRepository;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class LinkService {
    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link shorten(String longLink) {
        Link link = linkRepository.findByLongLink(longLink);
        if (link == null) {
            String shortLink = Hashing.murmur3_32().hashString(longLink, StandardCharsets.UTF_8).toString();
            link = new Link(shortLink, longLink);
            linkRepository.save(link);
        }
        return link;
    }

    public Link enlarge(String shortLink) {
        return linkRepository.findByShortLink(shortLink);
    }
}
