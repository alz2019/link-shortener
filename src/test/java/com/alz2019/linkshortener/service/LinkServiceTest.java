package com.alz2019.linkshortener.service;

import com.alz2019.linkshortener.model.Link;
import com.alz2019.linkshortener.repository.LinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkServiceTest {
    private static final String LONG_LINK = "https://yandex.ru";
    private static final String SHORT_LINK = "yan.ru";
    private static final Link LINK = new Link(SHORT_LINK, LONG_LINK);
    private LinkService linkService;

    @Mock
    private LinkRepository linkRepository;

    @BeforeEach
    void setUp() {
        linkService = new LinkService(linkRepository);
    }

    @Test
    public void testFindAndReturn() {
        when(linkRepository.findByLongLink(anyString())).thenReturn(LINK);
        Link link = linkService.shorten(LONG_LINK);
        assertThat(link.getShortLink()).isEqualTo(SHORT_LINK);
        verify(linkRepository).findByLongLink(anyString());
    }

    @Test
    public void testFindAndSave() {
        when(linkRepository.findByLongLink(anyString())).thenReturn(null);
        Link link = linkService.shorten(LONG_LINK);
        verify(linkRepository).findByLongLink(anyString());
        verify(linkRepository).save(link);
    }

    @Test
    public void testEnlargeService() {
        when(linkRepository.findByShortLink(SHORT_LINK)).thenReturn(LINK);
        Link link = linkService.enlarge(SHORT_LINK);
        assertThat(link).isEqualTo(LINK);
        verify(linkRepository).findByShortLink(SHORT_LINK);
    }
}