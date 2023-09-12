package com.SpringBootBlogApi.entity;

import com.SpringBootBlogApi.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Blogger  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;

    private String surname;

    @Email
    @NotNull(message = "The email cannot be empty")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password; // hassas veri oldugu icin okuma islemlerinde kullanilmasin

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserRole userRole;

}
