package com.dnth_underdog_241.online_fashion_shopping.dto.response;

import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SexEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser}
 */
@Value
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeGetAllResponseDto implements Serializable
{
    Long id;
    String phoneNumber;
    SexEnum sex;
    String lastName;
    String firstName;
    String email;
    LocalDate birthDay;
}