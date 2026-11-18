package com.example.jobportal.service;


import com.example.jobportal.dto.request.LoginRequest;
import com.example.jobportal.dto.request.RegisterRequest;
import com.example.jobportal.dto.response.AuthResponse;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {



    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;






    public void register(RegisterRequest request){

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.JOB_SEEKER);


        userRepository.save(user);

    }



    public AuthResponse login(LoginRequest request){


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()
                ));

        String token = jwtService.generateToken(request.getEmail());

        return new AuthResponse(token);

    }
}