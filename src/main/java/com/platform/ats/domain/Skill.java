package com.platform.ats.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "skills")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", nullable = false, columnDefinition = "VARCHAR(150)")
    private String skillName;

    @Column(columnDefinition = "VARCHAR(150)")
    private String category;

    @ManyToMany(mappedBy = "skills")
    private Set<Job> jobs;
}

