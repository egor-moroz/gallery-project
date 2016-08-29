package com.gallery.repository;


import com.gallery.model.User;

import java.util.List;

public interface UserRepository {

    void saveUser(User user);

    User updateUser(User user);

    User findByEmail(String email);

    List<User> showUsers();
}
