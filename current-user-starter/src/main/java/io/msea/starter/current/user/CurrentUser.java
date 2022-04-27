package io.msea.starter.current.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrentUser {

    private Long id;
    private String username;
    private String name;
    private String phone;
    private String email;
    private Integer sex;
    private Integer status;
    private String description;
    private Integer isAdmin;
}