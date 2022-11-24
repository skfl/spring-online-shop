package ru.skfl.skflshop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private String name;
    private String address;
    private String email;
    private String phone;
}
