package com.example.blogapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Size(min=5, max=20)
    private String about;
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
            , message = "Password Must Contains At least one upper case English letter, (?=.*?[A-Z])\n" +
            "At least one lower case English letter, (?=.*?[a-z])\n" +
            "At least one digit, (?=.*?[0-9])\n" +
            "At least one special character, (?=.*?[#?!@$%^&*-])\n" +
            "Minimum eight in length .{8,} (with the anchors)")
    private String password;
}
