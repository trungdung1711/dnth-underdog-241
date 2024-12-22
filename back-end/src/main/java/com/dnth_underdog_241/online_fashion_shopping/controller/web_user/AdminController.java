package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminService adminService;


    @PostMapping
    ResponseEntity<SignUpResponseDto> createAdmin(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        SignUpResponseDto signUpResponseDto = adminService.createAdmin(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signUpResponseDto);
    }
}
