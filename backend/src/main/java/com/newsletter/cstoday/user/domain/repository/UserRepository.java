package com.newsletter.cstoday.user.domain.repository;

import com.newsletter.cstoday.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
