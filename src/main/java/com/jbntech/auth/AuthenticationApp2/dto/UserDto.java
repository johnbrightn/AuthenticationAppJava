package com.jbntech.auth.AuthenticationApp2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    String username;
    String password;
    String fullName;
    String roles;
}
