package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;


import com.dnth_underdog_241.online_fashion_shopping.dto.GetWebUserResponseDTO;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserUpdateWebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.service.WebUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class WebUserController
{
    private final WebUserService webUserService;


    @PutMapping("/{id}/info")
    @PreAuthorize("authentication.principal.getId() == #id")
    public ResponseEntity<WebUserUpdateWebUserRequestDto> updateWebUser(@RequestBody WebUserUpdateWebUserRequestDto webUserUpdateWebUserRequestDto, @PathVariable Long id)
    {
        WebUserUpdateWebUserRequestDto responseWebUserUpdateWebUserRequestDto = webUserService.updateWebUser(webUserUpdateWebUserRequestDto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseWebUserUpdateWebUserRequestDto);
    }


    @GetMapping("/{id}/info")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.getId() == #id ")
    public ResponseEntity<GetWebUserResponseDTO> getWebUser(@PathVariable Long id)
    {
        GetWebUserResponseDTO getWebUserResponseDTO = webUserService.getWebUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getWebUserResponseDTO);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWebUser(@PathVariable Long id)
    {
        webUserService.deleteWebUser(id);
    }
}
