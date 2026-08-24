package com.inventra.inventra.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Report {

    private String reportType;
    private LocalDateTime generatedDate;

    private String dataSummary;
    private String recommendations;
}
