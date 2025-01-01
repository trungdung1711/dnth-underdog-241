package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.EmployeeGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<EmployeeGetAllResponseDto>> getAllEmployees(Pageable pageable)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getAllEmployees(pageable));
    }
}
