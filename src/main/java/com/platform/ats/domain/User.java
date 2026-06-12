package com.platform.ats.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"department", "jobs", "notifications", "interviewInterviewers"})
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Column(name = "full_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String fullName;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "password_hash", columnDefinition = "VARCHAR(255)")
    private String passwordHash;

    @Column(columnDefinition = "VARCHAR(30)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(50)")
    private String role;

    @Column(name = "sso_provider_id", columnDefinition = "VARCHAR(255)")
    private String ssoProviderId;

    @Column(columnDefinition = "VARCHAR(50)")
    private String status;

    @OneToMany(mappedBy = "recruiter")
    private Set<Job> jobs;
}

