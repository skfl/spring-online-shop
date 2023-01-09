package ru.skfl.skflshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    private String passwordHash;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(value = EnumType.STRING)
    private Set<UserRole> roles;


    @ElementCollection(targetClass = UserState.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_state", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(value = EnumType.STRING)
    private Set<UserState> states;

    @OneToMany(mappedBy = "user")
    private Set<UserOrder> orders;

    private String token; //todo: implement many tokens feature (i should create new entity and bind with user)

//    public List<SimpleGrantedAuthority> getAuthorities() {
//
//        List<SimpleGrantedAuthority> listAuthorities = new ArrayList<>();
//        Iterator<UserRole> rolesIterator = roles.iterator();
//        while (rolesIterator.hasNext()) {
//            listAuthorities.add(new SimpleGrantedAuthority(rolesIterator.next().toString()));
//        }
//        return listAuthorities;
//    }
}
