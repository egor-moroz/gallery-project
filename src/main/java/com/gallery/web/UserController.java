package com.gallery.web;

import com.gallery.validator.EmailAddressNotAvailableException;
import com.gallery.model.Picture;
import com.gallery.model.User;
import com.gallery.service.GalleryService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    GalleryService galleryService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String showMain(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/main";
    }

    @RequestMapping(value = "/myCabinet", method = RequestMethod.GET)
    public String showCabinet(Model model) {
        model.addAttribute("user",getUser());
        return "cabinet";
    }

    @RequestMapping(value = "/myCabinet", method = RequestMethod.POST)
    public String uploudFiles(Model model,@RequestParam(value = "file", required = false) Part[] files, Locale locale) {

        User user = getUser();
        List<String> errors = new ArrayList<>();
        Set<Picture> pictures = user.getPictures();

        for (int i = 1; i< files.length;i++) {
                Part file = files[i];
            String fileName = getFileName(file);
            if (file != null && ((fileName.endsWith(".jpg")||(fileName.endsWith(".jpeg"))||(fileName.endsWith(".png"))))) {
                Picture picture = new Picture();
                byte[] fileContent = null;
                try {
                    InputStream inputStream = file.getInputStream();
                    fileContent = IOUtils.toByteArray(inputStream);
                } catch (IOException e) {
                    logger.info("can`t read file");
                }

                picture.setPhoto(fileContent);
                pictures.add(picture);
            }else {
                String errorMessage = messageSource.getMessage("message_image_upload_fail",new Object[]{},locale);
                errors.add(errorMessage+fileName);
            }
        }
        model.addAttribute("errorUploadMessages", errors);
        user.setPictures(pictures);
        user = galleryService.updateUser(user);
        model.addAttribute("user",user);
        return "cabinet";
    }

    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        Picture picture = galleryService.findPictureById(id);
        return picture.getPhoto();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "/main";
        }
        try {
            galleryService.saveUser(user);
        } catch (EmailAddressNotAvailableException e) {
            FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
            bindingResult.addError(emailError);
            model.addAttribute("user", user);
            return "/main";
        }
        return "/main";
    }
    private User getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            user = galleryService.findByEmail(email);
        }
        return user;
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
