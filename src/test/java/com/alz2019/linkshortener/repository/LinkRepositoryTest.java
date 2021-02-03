package com.alz2019.linkshortener.repository;

import com.alz2019.linkshortener.model.Link;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LinkRepositoryTest {
    private static final String LONG_LINK = "https://yandex.ru";
    private static final String SHORT_LINK = "yan.ru";

    @Autowired
    private LinkRepository linkRepository;

    @AfterEach
    void tearDown() {
        linkRepository.deleteAll();
    }

    @Test
    public void testFindByLongLink() {
        linkRepository.save(new Link(SHORT_LINK, LONG_LINK));
        Link link = linkRepository.findByLongLink(LONG_LINK);
        assertThat(link).isNotNull();
        assertThat(link.getShortLink()).isEqualTo(SHORT_LINK);
    }

    @Test
    public void testFindByShortLink() {
        linkRepository.save(new Link(SHORT_LINK, LONG_LINK));
        Link link = linkRepository.findByShortLink(SHORT_LINK);
        assertThat(link).isNotNull();
        assertThat(link.getLongLink()).isEqualTo(LONG_LINK);
    }
}