package yte.pbs2024.user.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yte.pbs2024.common.response.MessageResponse;
import yte.pbs2024.common.response.MessageType;
import yte.pbs2024.user.controller.request.UserUpdateRequest;
import yte.pbs2024.user.controller.response.UserResponse;
import yte.pbs2024.user.entity.Users;
import yte.pbs2024.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public MessageResponse addUser(Users users){

        LocalDate birthDate = users.getBirthDate();
        long age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 18) {
            return new MessageResponse("User must be 18 years or older", MessageType.ERROR);
        }

        // Save the user as normal if over 18
        userRepository.save(users);
        return new MessageResponse("User registered successfully", MessageType.SUCCESS);

    }

    public Users getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public MessageResponse deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            return new MessageResponse("Student can't be found", MessageType.ERROR);
        }
        userRepository.deleteById(id);
        return new MessageResponse("User has been deleted successfully", MessageType.SUCCESS);
    }

    @Transactional
    public MessageResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        Users users = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        users.update(userUpdateRequest);
        userRepository.save(users);

        return new MessageResponse("Student has been updated", MessageType.SUCCESS);
    }

    public UserResponse getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = (Users) authentication.getPrincipal();
        return new UserResponse(user);
    }

}

