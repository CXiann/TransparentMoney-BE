package com.myproject.transparentmoney.user;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String uuid) {
        try {
            UUID uuidObj = UUID.fromString(uuid);
            Optional<User> optionaluser = userRepository.findById(uuidObj);
            if (optionaluser.isPresent()) {
                return optionaluser;
            }
            log.info("User with id: {} doesn't exist", uuid);
            return null;
        } catch (HttpMessageNotReadableException e) {
            log.error("User with id: {} doesn't exist", uuid, e);
            return null;
        }

    }

    public User saveUser(User user) {
        user.setCreatedAt(OffsetDateTime.now());
        User savedUser = userRepository.save(user);

        log.info("User with id: {} saved successfully", user.getId());
        return savedUser;
    }

    public User updateUser(User user) {
        Optional<User> optionalUser = findById(user.getId().toString());
        if (optionalUser.isPresent()) {
            User updatedUser = userRepository.save(user);
            log.info("User with id: {} updated successfully", user.getId());
            return updatedUser;
        }
        log.info("User with id: {} doesn't exist", user.getId());
        return null;

    }

    public void deleteUserById(String uuid) {
        try {
            UUID uuidObj = UUID.fromString(uuid);
            userRepository.deleteById(uuidObj);
            log.info("User with id: {} removed successfully", uuid);
        } catch (HttpMessageNotReadableException e) {
            log.error("User with id: {} doesn't exist", uuid, e);
        }
    }
}
