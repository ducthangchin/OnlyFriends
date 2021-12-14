package com.ducthangchin.model;

import com.ducthangchin.model.validation.PasswordMatch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
@PasswordMatch(message = "{register.repeatpassword.mismatch}")
public class WebUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank(message="Username cannot be blank.")
    private String username;

    @Column(unique = true)
    @Email(message="{register.email.invalid}")
    @NotBlank(message="{register.email.invalid}")
    private String email;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Transient
    @Size(min=5, max=15, message="{register.password.invalid}")
    private String plainPassword;

    @Transient
    private String repeatPassword;


    private String password;

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.password = new BCryptPasswordEncoder().encode(plainPassword);
        this.plainPassword = plainPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
