package com.rajpandith.Ewallet.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajpandith.Ewallet.enitity.UserEntity;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
