package com.rajpandith.Ewallet.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rajpandith.Ewallet.enitity.UserEntity;
import com.rajpandith.Ewallet.repository.imple.UserRepository;
import com.rajpandith.Ewallet.repository.jpa.UserJPARepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserJPARepository userJPARepository;

    public UserEntity createUser(UserEntity userEntity) throws Exception {
        try {

            Optional<UserEntity> foundUser = userJPARepository.findByEmail(userEntity.getEmail());
            if (foundUser.isPresent())
                throw new Exception("user already exists");

            UserEntity user = userRepository.save(userEntity);
            return user;
        } catch (Exception e) {
            throw new Exception("user not created/already registered");
        }
    }

}
