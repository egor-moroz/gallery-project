package com.gallery.web;

import com.gallery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class SecurityController {

    private MessageSource messageSource;

    @RequestMapping(value = "/loginfail")
    public String loginFail(Model model,Locale locale){
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("message", messageSource.getMessage("message_login_fail",new Object[]{},locale) );
        return "/main";
    }
    @RequestMapping(value = "/login")
    public String login(Model model,Locale locale){
        User user = new User();
        model.addAttribute("user",user);
        return "/main";
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }


}