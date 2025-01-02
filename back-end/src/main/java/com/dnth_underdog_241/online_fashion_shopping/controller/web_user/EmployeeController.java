package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.EmployeeGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.WebUserGetDTO;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController
{

    private final EmployeeService employeeService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private WebUserMapper webUserMapper;



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SignUpResponseDto> createEmployee(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        SignUpResponseDto signUpResponseDto = employeeService.createEmployee(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(signUpResponseDto);
    }

    @GetMapping("/asdfladsfj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<WebUserGetDTO>> getAllEmployeesdfdsfdfs() {
        Role roleEmployee = roleRepository
                .findByName(RoleEnum.ROLE_EMPLOYEE)
                .get();
        List<WebUser> webUser = webUserRepository.findByRoles(roleEmployee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        webUser
                                .stream()
                                .map(webUserMapper::toDto)
                                .collect(Collectors.toList())
                );
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
