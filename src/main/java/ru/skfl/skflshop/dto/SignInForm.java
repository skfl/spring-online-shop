package ru.skfl.skflshop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class SignInForm {
    private String email;
    private String password;
}
