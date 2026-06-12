package com.platform.ats.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"department", "recruiter", "skills", "applications"})
@Builder
public class Job extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String title;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String location;

    @Column(name = "salary_min", columnDefinition = "NUMERIC(15, 2)") // 999 999 999 999 999.99
    private Double salaryMin;

    @Column(name = "salary_max", columnDefinition = "NUMERIC(15, 2)") // 999 999 999 999 999.99
    private Double salaryMax;

    @Column(columnDefinition = "VARCHAR(50)")
    private String status;

    @Column(name = "utm_source", columnDefinition = "VARCHAR(150)")
    private String utmSource;

    @Column(name = "utm_medium", columnDefinition = "VARCHAR(150)")
    private String utmMedium;

    private OffsetDateTime deadline;

    @Column(name = "published_at")
    private OffsetDateTime publishedAt;

    @OneToMany(mappedBy = "job")
    private Set<Application> applications;

    @ManyToMany
    @JoinTable(name = "job_skills", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", referencedColumnName = "id")
    private User recruiter;
}
