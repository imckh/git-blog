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
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/6/26 22:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @Column(name = "profile_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal profileId;
    @Basic
    @Column(name = "bio", nullable = true, length = 500)
    private String bio;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
}
