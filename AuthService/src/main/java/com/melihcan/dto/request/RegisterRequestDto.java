package com.melihcan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestDto {
    @NotNull(message = "kullanıcı adı girilmesi zorunludur.")
    @Size(min = 3,max = 20,message = "")
    private String username;
    @NotNull(message = "şifre boş geçilemez.")
    @Size(min = 8,message = "şifre minimum 8 karakter olmalıdır.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "şifre en az 8 Karakter, En az bir büyük bir Küçük harf, Sayı ve özel karakterden oluşmalıdır.")
    private String password;
    private String repassword;
    @Email(message = "lütfen geçerli bir email giriniz.")
    private String email;


}
