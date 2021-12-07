package com.newsletter.cstoday.content.domain.repository;

import com.newsletter.cstoday.content.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
