package com.example.jobportal.service;


import com.example.jobportal.dto.request.UpdateUserRequest;
import com.example.jobportal.dto.response.UserResponse;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.DuplicateResourceException;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;


    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);



    public List<UserResponse> getAllUsers(){

        logger.info("Fetching all users");

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();

    }


    // GET USER BY ID

    public UserResponse getUserById(Long id){

        User user = findUserById(id);

        return userMapper.toDTO(user);

    }


    // PUT UPDATE USER

    public UserResponse updateUser(Long id, UpdateUserRequest request){

        User user = findUserById(id);

        if(!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())){

            throw new DuplicateResourceException(
                    "Email already exists"
            );

        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);

        logger.info("User updated with id {}", id);

        return userMapper.toDTO(updatedUser);

    }


    // DELETE USER

    public void deleteUser(Long id){

        User user = findUserById(id);

        userRepository.delete(user);

        logger.info("User deleted with id {}", id);

    }


    // COMMON METHOD

    private User findUserById(Long id){

        return userRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

    }

}