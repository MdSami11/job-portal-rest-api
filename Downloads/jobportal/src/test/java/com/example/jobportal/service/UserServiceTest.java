package com.example.jobportal.service;


import com.example.jobportal.dto.request.UpdateUserRequest;
import com.example.jobportal.dto.response.UserResponse;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.repository.UserRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class UserServiceTest {



    @Mock
    private UserRepository userRepository;



    @Mock
    private UserMapper userMapper;



    @InjectMocks
    private UserService userService;






    @Test
    void getUserById_shouldReturnUser(){


        User user = new User();

        user.setId(1L);
        user.setName("Sam");
        user.setEmail("sam@gmail.com");



        UserResponse response =
                new UserResponse(

                        1L,

                        "Sam",

                        "sam@gmail.com",

                        Role.JOB_SEEKER

                );



        when(userRepository.findById(1L))
                .thenReturn(
                        Optional.of(user)
                );



        when(userMapper.toDTO(user))
                .thenReturn(response);




        UserResponse result =
                userService.getUserById(1L);




        assertEquals(
                "Sam",
                result.getName()
        );



        verify(userRepository)
                .findById(1L);


    }



    @Test
    void getUserById_shouldThrowExceptionWhenNotFound(){



        when(userRepository.findById(99L))
                .thenReturn(
                        Optional.empty()
                );




        assertThrows(

                ResourceNotFoundException.class,

                () -> userService.getUserById(99L)

        );




        verify(userRepository)
                .findById(99L);


    }



    @Test
    void updateUser_shouldReturnUpdatedUser(){

        User user = new User();


        user.setId(1L);
        user.setName("Old Name");
        user.setEmail("old@gmail.com");


        UpdateUserRequest request = new UpdateUserRequest();


        request.setName("New Name");


        UserResponse response = new UserResponse(

                        1L,

                        "New Name",

                        "old@gmail.com",

                         Role.ADMIN
                );



        when(userRepository.findById(1L))
                .thenReturn(
                        Optional.of(user));



        when(userRepository.save(user))
                .thenReturn(user);



        when(userMapper.toDTO(user))
                .thenReturn(response);




        UserResponse result = userService.updateUser(
                        1L,
                        request
                );




        assertEquals(

                "New Name",

                result.getName()

        );



        verify(userRepository)
                .save(user);


    }




    @Test
    void deleteUser_shouldDeleteUser(){



        User user =
                new User();


        user.setId(1L);



        when(userRepository.findById(1L))
                .thenReturn(
                        Optional.of(user)
                );




        userService.deleteUser(1L);



        verify(userRepository)
                .delete(user);


    }


}