package com.example.botscrewtesttask.entity.dto;

import lombok.Data;

@Data
public class DepartmentStatisticsDto {
    private long assistantsCount;
    private long associateProfessorsCount;
    private long professorsCount;

    public DepartmentStatisticsDto(long assistantsCount, long associateProfessorsCount, long professorsCount) {
        this.assistantsCount = assistantsCount;
        this.associateProfessorsCount = associateProfessorsCount;
        this.professorsCount = professorsCount;
    }

    @Override
    public String toString() {
        return "assistants - " + assistantsCount + "." + "\n" +
                "associate professors - " + associateProfessorsCount + "." + "\n" +
                "professors - " + professorsCount + ".";
    }
}
