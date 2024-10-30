package com.dnth_underdog_241.online_fashion_shopping.model.user;


import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SexEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "web_user_type",
        discriminatorType = DiscriminatorType.STRING,
        length = 50)
@Table(name = "web_users")
public abstract class WebUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private Long id;


    @Column(name = "phone_number",
            length = 15,
            nullable = false,
            unique = true)
    private String phoneNumber;


    @Column(name = "password",
            nullable = false,
            length = 1000)
    private String password;


    @Enumerated
    @Column(name = "sex",
            nullable = true)
    private SexEnum sex;


    @Column(name = "last_name",
            nullable = false)
    private String lastName;


    @Column(name = "first_name",
            nullable = false)
    private String firstName;


    @Column(name = "email",
            nullable = true,
            unique = true)
    private String email;


    @Column(name = "birth_day")
    private LocalDate birthDay;


    @OneToOne(cascade =
            {CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.PERSIST},
            orphanRemoval = true)
    @JoinColumn(name = "address_id",
                referencedColumnName = "id")
    private Address address;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "web_users_roles",
            joinColumns = @JoinColumn(name = "web_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    private Set<Role> roles = new LinkedHashSet<Role>();
}