package yte.pbs2024.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.pbs2024.common.BaseEntity;

@Entity
@NoArgsConstructor
@Getter
public class Users extends BaseEntity {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String picture;


}