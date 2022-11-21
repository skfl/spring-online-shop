package ru.skfl.skflshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WeaponDTO {
    private Long id;
    private String type;
    private String name;
    private int price;
    private String imgPath;
}
