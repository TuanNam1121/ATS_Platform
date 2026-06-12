package com.platform.ats.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "applications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transferred_from", referencedColumnName = "id")
    private Application transferredFrom;


    @Column(columnDefinition = "VARCHAR(50)")
    private String status;

    @Column(name = "applied_at")
    private OffsetDateTime appliedAt;

}
