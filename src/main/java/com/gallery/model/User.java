package com.gallery.model;

import com.gallery.validator.ValidPassword;
import com.gallery.validator.ValidUniqueEmail;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "USER")
@ValidPassword(message = "{validation.user.ValidPassword.message}")
public class User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
    private String confirmPassword;
    private String information;
    private Set<Picture> pictures;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME", nullable = false)
    @NotEmpty(message = "{validation.user.firstName.NotEmpty.message}")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false)
    @NotEmpty(message = "{validation.user.lastName.NotEmpty.message}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL", nullable = false, unique = true)
    @ValidUniqueEmail(message = "validation.user.email.ValidUniqueEmail.message")
    @NotEmpty(message = "{validation.user.email.NotEmpty.message}")
    @Pattern(message = "{validation.user.email.Pattern.message}", regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD", nullable = false)
    @NotEmpty(message = "{validation.user.password.NotEmpty.message}")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    @NotEmpty(message = "{validation.user.confirmPassword.NotEmpty.message}")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Column(name = "BIRTH_DATE")
    @Past(message = "{validation.user.birthDate.Past.message}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "INFORMATION")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
