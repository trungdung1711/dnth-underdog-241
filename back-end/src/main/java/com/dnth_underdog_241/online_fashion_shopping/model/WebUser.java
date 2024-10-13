package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "web_user")
public class WebUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "u_id", nullable = false)
    private Long u_id;


    @Column(name = "u_phone_number", length = 20)
    private String u_phone_number;


    @Column(name = "u_pass_word", nullable = false, length = 1000)
    private String u_pass_word;
};