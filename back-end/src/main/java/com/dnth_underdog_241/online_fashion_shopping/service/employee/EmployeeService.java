package com.dnth_underdog_241.online_fashion_shopping.service.employee;


import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpResponseDto;
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
public class EmployeeService
{
    private final SignUpMapper signUpMapper;


    private final WebUserRepository webUserRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;


    @Transactional
    public SignUpResponseDto createEmployee(SignUpRequestDto signUpRequestDto)
    {
        if (webUserRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber()))
        {
            throw new UserAlreadyExistsException("User already exists");
        }

        WebUser webUser = signUpMapper.toEmployeeEntity(signUpRequestDto);
        webUser.setPassword(passwordEncoder.encode(webUser.getPassword()));

        Role roleEmployee = roleRepository
                .findByName(RoleEnum.ROLE_EMPLOYEE)
                .get();
        Role roleCustomer = roleRepository
                .findByName(RoleEnum.ROLE_CUSTOMER)
                .get();

        webUser.getRoles().add(roleEmployee);
        webUser.getRoles().add(roleCustomer);

        WebUser savedWebUser = webUserRepository.save(webUser);
        return signUpMapper.toDto(savedWebUser);
    }
}
