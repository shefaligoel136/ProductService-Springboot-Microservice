package com.project.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private String hashedPassword;
    private List<RoleDTO> roles;
    private boolean isEmailVerified;

}
