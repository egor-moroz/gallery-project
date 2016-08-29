package com.gallery.service;


import com.gallery.Util.EmailAddressNotAvailableException;
import com.gallery.model.Picture;
import com.gallery.model.User;

import java.util.List;


public interface GalleryService {

    void saveUser(User user) throws EmailAddressNotAvailableException;

    List<User> showUsers();

    boolean isUserEmailUnique(String userEmail);

    User findByEmail(String email);

    void savePicture(Picture picture);

    Picture findPictureById(Long id);

    User updateUser(User user);
}
