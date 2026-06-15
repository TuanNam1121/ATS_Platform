package org.ats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
@ToString(exclude = {"department", "skills"})
@Builder
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Job extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String location;

    @Column(name = "min_salary", columnDefinition = "NUMERIC(15, 2)") // 999 999 999 999 999.99
    private Double minSalary;

    @Column(name = "max_salary", columnDefinition = "NUMERIC(15, 2)") // 999 999 999 999 999.99
    private Double maxSalary;

    @Column(columnDefinition = "VARCHAR(50)")
    private String status;

    @Column(name = "utm_source", columnDefinition = "VARCHAR(150)")
    private String utmSource;

    @Column(name = "utm_medium", columnDefinition = "VARCHAR(150)")
    private String utmMedium;

    private OffsetDateTime deadline;

    private OffsetDateTime publishedAt;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "dept_id")
    private Department department;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private Set<JobSkill> skills;
}
