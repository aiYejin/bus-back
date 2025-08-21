package com.kt.backendapp.domain;

public enum ReportType {
    SKIP_STOP("무정차"),
    BROKEN_STATION("정류장 고장"),
    DANGEROUS_DRIVING("난폭운전"),
    LATE_BUS("지연버스"),
    CROWDED_BUS("과다혼잡"),
    OTHER("기타");

    private final String description;

    ReportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ReportType fromDescription(String description) {
        for (ReportType type : values()) {
            if (type.description.equals(description)) {
                return type;
            }
        }
        return OTHER;
    }
}
