package com.dnth_underdog_241.online_fashion_shopping.controller.employee;


import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.auth.WebUserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController
{
    private final WebUserAuthService webUserAuthService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SignUpResponseDto> signInEmployee(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        SignUpResponseDto signUpResponseDto = webUserAuthService.signUpEmployee(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(signUpResponseDto);
    }
}
