package com.gallery.service;

import com.gallery.Util.EmailAddressNotAvailableException;
import com.gallery.model.Picture;
import com.gallery.model.User;
import com.gallery.repository.PictureRepository;
import com.gallery.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@Service("galleryService")
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    @Transactional(rollbackFor = EmailAddressNotAvailableException.class)
    public void saveUser(User user) throws EmailAddressNotAvailableException {
        try {
            userRepository.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                throw new EmailAddressNotAvailableException();
            } else {
                throw e;
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> showUsers() {
        return userRepository.showUsers();
    }

    @Override
    public boolean isUserEmailUnique(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        System.out.println(user == null);
        return user == null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Transactional
    public void savePicture(Picture picture) {
        pictureRepository.savePicture(picture);
    }

    @Override
    @Transactional(readOnly = true)
    public Picture findPictureById(Long id) {
        return pictureRepository.findPictureById(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
       return userRepository.updateUser(user);
    }
}
