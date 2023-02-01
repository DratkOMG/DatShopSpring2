package com.example.datshopspring2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProfileDTO {
    private String username;
    private String phoneNumber;
    private String city;
    private String sex;
    private Integer age;
}
