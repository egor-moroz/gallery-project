package com.gallery.repository.jpa;


import com.gallery.model.Picture;
import com.gallery.repository.PictureRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class jpaPictureRepository implements PictureRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void savePicture(Picture picture) {
        em.persist(picture);
    }

    public Picture findPictureById(Long id) {

        Picture picture = em.find(Picture.class, id);
        return picture;
    }
}
