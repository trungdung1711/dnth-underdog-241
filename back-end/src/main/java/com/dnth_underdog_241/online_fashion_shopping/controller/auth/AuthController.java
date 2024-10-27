package com.dnth_underdog_241.online_fashion_shopping.controller.auth;


import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.auth.WebUserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class AuthController
{
    private final WebUserAuthService webUserAuthService;


    @PostMapping("sign-up")
    public ResponseEntity<WebUserResponseDto> registerNewWebUser(@RequestBody WebUserRequestDto webUserRequestDto)
    {
        WebUserResponseDto webUserResponseDto = webUserAuthService.signIn(webUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(webUserResponseDto);
    }
}
