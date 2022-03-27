package com.gaegul;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final List<User> userList = List.of(
            new User(1L, "홍길동1", 10),
            new User(2L,"홍길동2", 11),
            new User(3L,"홍길동3", 12),
            new User(4L,"홍길동4", 13),
            new User(5L,"홍길동5", 14),
            new User(6L,"홍길동6", 15),
            new User(7L,"홍길동7", 16),
            new User(8L,"홍길동8", 17),
            new User(9L,"홍길동9", 18),
            new User(10L,"홍길동10", 19)
    );

    public List<User> getUsers() {
        return userList;
    }

    public User getUser(Long id) {
        return userList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("not allow user id"));
    }

    public User saveUser(User user) {
        if (userList.stream().anyMatch(item -> item.getId().equals(user.getId()))) {
            throw new IllegalArgumentException("duplicate user id");
        }
        userList.add(user);
        return user;
    }

    public User updateUser(User user) {
        if (!userList.stream().anyMatch(item -> item.getId().equals(user.getId()))) {
            throw new IllegalArgumentException("not exist user id");
        }
        User defaultUser = getUser(user.getId());
        defaultUser.setAge(user.getAge());
        defaultUser.setName(user.getName());
        return defaultUser;
    }

    public void deleteUser(Long id) {
        userList.remove(id);
    }
}
