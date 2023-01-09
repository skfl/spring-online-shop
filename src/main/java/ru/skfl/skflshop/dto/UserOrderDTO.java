package ru.skfl.skflshop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserOrderDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String items;
}
