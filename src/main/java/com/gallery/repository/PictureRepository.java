package com.gallery.repository;


import com.gallery.model.Picture;
import com.gallery.model.User;

public interface PictureRepository {

    void savePicture(Picture picture);

     Picture findPictureById(Long id);
}
