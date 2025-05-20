package org.example.cs322project.model.enums;

import lombok.Getter;

@Getter
public enum Hours {
    FIRST("09:00", "10:00"),
    SECOND("10:00", "11:00"),
    THIRD("11:00", "12:00"),
    FORTH("12:00", "13:00"),
    FIFTH("13:00", "14:00"),
    SIXTH("14:00", "15:00");

    private final String startTime;
    private final String endTime;

    Hours(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return name() + " (" + startTime + " - " + endTime + ")";
    }
}
