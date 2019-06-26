package com.imckh.gitblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author imckh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private BigDecimal userId;
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Basic
    @Column(name = "role_id", nullable = false)
    private BigDecimal roleId;
    @Basic
    @Column(name = "ban", nullable = false, length = 3)
    private String ban;
    @Basic
    @Column(name = "login", nullable = false, length = 50)
    private String login;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
    @Basic
    @Column(name = "token", nullable = false, length = 100)
    private String token;
    @Basic
    @Column(name = "token_expiry_at", nullable = true)
    private Timestamp tokenExpiryAt;
    @OneToOne
    @JoinColumn(name = "profile_id", nullable = true, foreignKey = @ForeignKey(name = "null"))
    private Profile profile;
}
