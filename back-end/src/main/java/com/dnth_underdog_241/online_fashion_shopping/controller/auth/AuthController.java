package com.dnth_underdog_241.online_fashion_shopping.controller.auth;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.LogInRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.LogInResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.user.*;
import com.dnth_underdog_241.online_fashion_shopping.security.model.WebUserDetails;
import com.dnth_underdog_241.online_fashion_shopping.security.util.JwtUtil;
import com.dnth_underdog_241.online_fashion_shopping.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthController
{
    private final CustomerService customerService;


    private final AuthenticationManager authenticationManager;


    private final JwtUtil jwtUtil;


    @PostMapping("sign-up")
    public ResponseEntity<SignUpResponseDto> registerNewWebUser(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        SignUpResponseDto signUpResponseDto = customerService.createCustomer(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signUpResponseDto);
    }


    @PostMapping("login")
    public ResponseEntity<LogInResponseDto> logIn(@RequestBody @Valid LogInRequestDto logInRequestDto)
    {
        Authentication authentication = new UsernamePasswordAuthenticationToken(logInRequestDto.getPhoneNumber(), logInRequestDto.getPassword());
        authentication = authenticationManager.authenticate(authentication);

        WebUser webUser = ((WebUserDetails) authentication.getPrincipal()).getWebUser();


        String token = jwtUtil.generateToken
                (
                        webUser.getId(),
                        webUser.getPhoneNumber(),
                        webUser.getRoles()
                );

        LogInResponseDto logInResponseDto = new LogInResponseDto
                (
                        webUser.getId(),
                        token,
                        webUser.getPhoneNumber(),
                        webUser.getRoles()
                );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logInResponseDto);
    }
}