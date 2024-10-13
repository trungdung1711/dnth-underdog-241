package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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
};