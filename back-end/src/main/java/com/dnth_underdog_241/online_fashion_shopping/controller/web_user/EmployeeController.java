package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;


import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.employee.EmployeeService;
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
    private final EmployeeService employeeService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SignUpResponseDto> createEmployee(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        SignUpResponseDto signUpResponseDto = employeeService.createEmployee(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(signUpResponseDto);
    }
}
