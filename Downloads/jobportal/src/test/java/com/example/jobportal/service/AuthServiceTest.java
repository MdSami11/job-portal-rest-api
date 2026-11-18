package com.example.jobportal.service;


import com.example.jobportal.dto.request.LoginRequest;
import com.example.jobportal.dto.request.RegisterRequest;
import com.example.jobportal.dto.response.AuthResponse;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.security.JwtService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



/*This is the most important security unit test.

Tests covered:AuthService

✅ Register user
    - Password gets encrypted
    - User gets saved

✅ Login user
    - AuthenticationManager called
    - JWT token generated*/


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {


    @Mock
    private UserRepository userRepository;



    @Mock
    private PasswordEncoder passwordEncoder;



    @Mock
    private AuthenticationManager authenticationManager;



    @Mock
    private JwtService jwtService;



    @InjectMocks
    private AuthService authService;



    //REGISTER
    @Test
    void register_shouldSaveUserWithEncryptedPassword(){


        RegisterRequest request = new RegisterRequest(

                        "Sam",

                        "sam@gmail.com",

                        "password123"

                );



        when(passwordEncoder.encode("password123"))

                .thenReturn("encryptedPassword");



        authService.register(request);



        verify(passwordEncoder)

                .encode("password123");



        verify(userRepository)

                .save(any(User.class));

    }




    //LOGIN
    @Test
    void login_shouldReturnJwtToken(){



        LoginRequest request = new LoginRequest(

                        "sam@gmail.com",

                        "password123");



        when(jwtService.generateToken("sam@gmail.com"))

                .thenReturn("jwt-token");



        AuthResponse response = authService.login(request);


        assertEquals(

                "jwt-token",

                response.getToken());


        verify(authenticationManager).authenticate(

                        any(UsernamePasswordAuthenticationToken.class));


        verify(jwtService)

                .generateToken("sam@gmail.com");

    }

}