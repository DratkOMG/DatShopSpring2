package com.example.datshopspring2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;
}
