package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.WebUserGetDTO;
import com.dnth_underdog_241.online_fashion_shopping.model.Product;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminService adminService;

    private  final WebUserRepository webUserRepository;

    private  final WebUserMapper webUserMapper;


    @PostMapping
    ResponseEntity<SignUpResponseDto> createAdmin(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        SignUpResponseDto signUpResponseDto = adminService.createAdmin(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(signUpResponseDto);
    }


    @GetMapping
    public ResponseEntity<List<WebUserGetDTO>> getAllManager()
    {
        List<WebUser> brands = webUserRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        brands
                                .stream()
                                .map(webUserMapper::toDto)
                                .collect(Collectors.toList())
                );
    }
}
