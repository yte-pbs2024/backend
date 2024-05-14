package yte.pbs2024.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.pbs2024.common.response.MessageResponse;
import yte.pbs2024.user.controller.request.UserAddRequest;
import yte.pbs2024.user.controller.request.UserUpdateRequest;
import yte.pbs2024.user.controller.response.UserResponse;
import yte.pbs2024.user.entity.Users;
import yte.pbs2024.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    @PostMapping
    public MessageResponse addUser(@RequestBody @Valid UserAddRequest userAddRequest)  {
        return userService.addUser(userAddRequest);
    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@NotNull @PathVariable Long id){
        Users users = userService.getUserById(id);
        return new UserResponse(users);
    }
    @GetMapping
    public List<UserResponse> getUsers(){
        List<Users> users = userService.getUsers();
        return users.stream()
                .map(UserResponse::new)
                .toList();
    }
    @DeleteMapping("/{id}")
    public MessageResponse deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public MessageResponse updateUser(@PathVariable  Long id, @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(id, userUpdateRequest);
    }
    @GetMapping("/current-user")
    public UserResponse getCurrentUserDetails() {
        return userService.getCurrentUserDetails();
    }
}
