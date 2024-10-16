package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

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
    @Column(name = "u_id", nullable = false)
    private Long id;


    @Column(name = "u_phone_number", length = 20)
    private String phoneNumber;


    @Column(name = "u_pass_word", nullable = false, length = 1000)
    private String passWord;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "web_user_roles",
            joinColumns = @JoinColumn(name = "webUser_u_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_role_id"))
    private Set<Role> roles = new LinkedHashSet<>();
};