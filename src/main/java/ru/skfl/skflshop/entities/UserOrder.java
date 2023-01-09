package ru.skfl.skflshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String email;
    private String items;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
