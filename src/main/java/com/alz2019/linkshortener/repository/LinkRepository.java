package com.alz2019.linkshortener.repository;

import com.alz2019.linkshortener.model.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface LinkRepository extends CrudRepository<Link, String> {
    Link save(Link link);

    Link findByLongLink(String longLink);

    Link findByShortLink(String shortLink);
}
