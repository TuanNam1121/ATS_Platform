package com.platform.ats.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter @Getter @ToString
public class JobSearchDTO {
    private String title;
    private int minSalary;
    private int maxSalary;
}
