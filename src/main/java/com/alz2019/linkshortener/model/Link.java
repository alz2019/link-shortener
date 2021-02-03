package com.alz2019.linkshortener.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    @Id
    @JsonProperty("short_link")
    private String shortLink;

    @JsonProperty("long_link")
    private String longLink;
}
