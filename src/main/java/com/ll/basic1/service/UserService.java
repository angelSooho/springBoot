package com.ll.basic1.service;

import com.ll.basic1.model.User;
import com.ll.basic1.model.userDto;
import com.ll.basic1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    ArrayList<User> arr = new ArrayList<>();

    public userDto add(User user) {
        userRepository.save(user);
        arr.add(user);
        return new userDto("S-1", user.getUsername() + "님 환영합니다.");
    }

    public userDto search(User user) {
        for (User user1 : arr) {
            if (Objects.equals(user1.getUsername(), user.getUsername())) {
                if (Objects.equals(user1.getPassword(), user.getPassword())) {
                    return new userDto("S-1", user.getUsername() + "님 환영합니다.");
                } else {
                    return new userDto("F-1", "비밀번호가 일치하지 않습니다.");
                }
            }
        }
        return new userDto("F-2", user.getUsername() + "(은)는 존재하지 않는 회원입니다.");
    }

    public List<User> list() {
        return arr;
    }
}
