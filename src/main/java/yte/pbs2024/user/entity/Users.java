package yte.pbs2024.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import yte.pbs2024.common.BaseEntity;
import yte.pbs2024.user.controller.request.UserUpdateRequest;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Users extends BaseEntity implements UserDetails {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String picture;
    private String tc;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;

    public Users(String name, String surname,   String email, String picture, String tc, String gender, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.picture = picture;
        this.tc = tc;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void update(UserUpdateRequest userUpdateRequest, List<Authority> authorityList) {

        this.name = userUpdateRequest.name();
        this.surname = userUpdateRequest.surname();
        this.email = userUpdateRequest.email();
        this.picture = userUpdateRequest.picture();
        this.gender = userUpdateRequest.gender();
        this.birthDate = userUpdateRequest.birthDate();
        this.phoneNumber = userUpdateRequest.phoneNumber();
        this.authorities = authorityList;

    }
}