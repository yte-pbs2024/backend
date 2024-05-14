package yte.pbs2024.user.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yte.pbs2024.common.response.MessageResponse;
import yte.pbs2024.common.response.MessageType;
import yte.pbs2024.exception.UserExistException;
import yte.pbs2024.exception.UserNotFoundException;
import yte.pbs2024.user.controller.request.UserAddRequest;
import yte.pbs2024.user.controller.request.UserUpdateRequest;
import yte.pbs2024.user.controller.response.UserResponse;
import yte.pbs2024.user.entity.Authority;
import yte.pbs2024.user.entity.Users;
import yte.pbs2024.user.repository.AuthorityRepository;
import yte.pbs2024.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    @Transactional
    public MessageResponse addUser(UserAddRequest userAddRequest){
        Users users = userAddRequest.toEntity();

        if (users.getName().isEmpty()) {
            return new MessageResponse("All fields should be filled", MessageType.ERROR);
        }
        boolean emailExists = userRepository.existsByEmail(users.getEmail());
        if (emailExists) {
            throw new UserExistException("User already exists: " + users.getEmail());
        }

        List<Authority> newAuthorities = authorityRepository.findByIdIn(userAddRequest.authorities());
        if (newAuthorities.size() != userAddRequest.authorities().size()) {
            throw new UserNotFoundException("Some authorities not found");
        }
        users.setAuthorities(newAuthorities);
        userRepository.save(users);
        return new MessageResponse("User has been added successfully", MessageType.SUCCESS);
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("Kullanıcı bulunumadı"));
    }
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public MessageResponse deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("Kullanıcı bulunumadı");
        }
        userRepository.deleteById(id);
        return new MessageResponse("Kişi başarılı bir şekilde silindi", MessageType.SUCCESS);
    }

    @Transactional
    public MessageResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        Users user = userRepository.findById(id).orElseThrow();
        List<Authority> newAuthorities = authorityRepository.findByIdIn(userUpdateRequest.authorities());
        user.update(userUpdateRequest, newAuthorities);
        userRepository.save(user);
        return new MessageResponse("Kişi bilgileri güncellendi.", MessageType.SUCCESS);
    }


    public UserResponse getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = (Users) authentication.getPrincipal();
        return new UserResponse(user);
    }
}

