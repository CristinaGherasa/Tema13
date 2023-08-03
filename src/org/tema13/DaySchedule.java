package org.tema13;

import java.util.ArrayList;
import java.util.List;

public class DaySchedule {
    private Day day;
    private List<String> activities;

    public DaySchedule(Day day) {
        this.day = day;
        this.activities = new ArrayList<>();
    }

    public Day getDay() {
        return day;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void addActivity(String activity) {
        activities.add(activity);
    }

    public void removeActivity(String activity) {
        activities.remove(activity);
    }
}
