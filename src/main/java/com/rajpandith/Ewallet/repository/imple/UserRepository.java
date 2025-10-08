package com.rajpandith.Ewallet.repository.imple;


import org.springframework.stereotype.Repository;

import com.rajpandith.Ewallet.enitity.UserEntity;
import com.rajpandith.Ewallet.repository.jpa.UserJPARepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJPARepository userJPARepository;

    public UserEntity save(UserEntity userEntity) {
        return userJPARepository.save(userEntity);
    }

}
