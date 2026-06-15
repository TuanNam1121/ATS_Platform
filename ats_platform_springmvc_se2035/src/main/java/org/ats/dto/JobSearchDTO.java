package org.ats.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class JobSearchDTO {
    private String title;
    private int minSalary;
    private int maxSalary;
}
