package com.melihcan.repository.entity;

import com.melihcan.repository.enums.ERole;
import com.melihcan.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "tblauth")
public class Auth extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    ERole role = ERole.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    EStatus status = EStatus.PENDING;
}
