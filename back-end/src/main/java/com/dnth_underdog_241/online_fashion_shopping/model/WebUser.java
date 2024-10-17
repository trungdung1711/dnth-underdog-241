package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "web_user")
public class WebUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;


    @Column(length = 20)
    private String phoneNumber;


    @Column(nullable = false, length = 1000)
    private String password;


    @Enumerated
    private Sex sex;


    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false)
    private String firstName;


    @Column(unique = true)
    private String email;


    @Column
    private LocalDate birthDay;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
            (name = "web_user_roles",
            joinColumns = @JoinColumn(name = "webUser_u_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

}