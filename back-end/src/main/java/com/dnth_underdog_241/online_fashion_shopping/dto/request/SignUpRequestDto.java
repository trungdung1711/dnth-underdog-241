package com.dnth_underdog_241.online_fashion_shopping.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


@Value
@Builder
@Getter
@Setter
@AllArgsConstructor
public class SignUpRequestDto implements Serializable
{
    @NotNull(message = "Phone number required")
    @Size(min = 10, max = 12, message = "Unsupported phone number")
    String phoneNumber;

    @NotNull(message = "Password required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    String password;

    @NotNull(message = "Last name required")
    String lastName;

    @NotNull(message = "First name required")
    String firstName;
}