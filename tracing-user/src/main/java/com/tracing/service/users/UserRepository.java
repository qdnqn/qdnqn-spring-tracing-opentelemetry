package com.tracing.service.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracing.user.User;

interface UserRepository extends JpaRepository<User, Long> {

}