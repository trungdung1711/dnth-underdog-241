package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;


import com.dnth_underdog_241.online_fashion_shopping.dto.GetWebUserResponseDTO;
import com.dnth_underdog_241.online_fashion_shopping.dto.UpdateWebUserDto;
import com.dnth_underdog_241.online_fashion_shopping.service.webuser.WebUserService;
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


    @PutMapping("{id}/info")
    @PreAuthorize("authentication.principal.getId() == #id")
    public ResponseEntity<UpdateWebUserDto> updateWebUser(@RequestBody UpdateWebUserDto updateWebUserDto, @PathVariable Long id)
    {
        UpdateWebUserDto responseUpdateWebUserDto = webUserService.updateWebUser(updateWebUserDto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseUpdateWebUserDto);
    }


    @GetMapping("{id}/info")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.getId() == #id ")
    public ResponseEntity<GetWebUserResponseDTO> getWebUser(@PathVariable Long id)
    {
        GetWebUserResponseDTO getWebUserResponseDTO = webUserService.getWebUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getWebUserResponseDTO);
    }
}
