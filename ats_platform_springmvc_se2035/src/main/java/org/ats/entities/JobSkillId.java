package org.ats.entities;

import java.io.Serializable;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSkillId implements Serializable {
    private Long skill;
    private Long job;
}
