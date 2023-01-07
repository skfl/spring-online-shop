package ru.skfl.skflshop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Builder
public class UserDTO {
    private String username;
    private String email;
    private String passwordHash;
}
