package com.dnth_underdog_241.online_fashion_shopping.service.admin;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserAlreadyExistsException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.SignUpMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AdminService
{
    private final WebUserRepository webUserRepository;


    private final RoleRepository roleRepository;


    private final SignUpMapper signUpMapper;


    private final PasswordEncoder passwordEncoder;


    @Transactional
    public SignUpResponseDto createAdmin(SignUpRequestDto signUpRequestDto)
    {
        if (webUserRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber()))
        {
            throw new UserAlreadyExistsException("User already exists");
        }

        WebUser webUser = signUpMapper.toAdminEntity(signUpRequestDto);

        webUser.setPassword(passwordEncoder.encode(webUser.getPassword()));

        Role roleAdmin = roleRepository
                .findByName(RoleEnum.ROLE_ADMIN)
                .get();

        webUser.getRoles().add(roleAdmin);

        WebUser savedWebUser = webUserRepository.save(webUser);
        return signUpMapper.toDto(savedWebUser);
    }
}
