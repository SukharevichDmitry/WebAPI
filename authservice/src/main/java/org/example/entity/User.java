package org.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "Имя", example = "John")
    private String username;

    @Schema(description = "Пароль", example = "12a3_4d5fc6cv7b89")
    private String password;

}
