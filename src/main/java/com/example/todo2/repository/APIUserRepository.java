package com.example.todo2.repository;

import com.example.todo2.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIUserRepository  extends JpaRepository<APIUser, String> {
}
