package org.tema13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyPlanner {
    private List<DaySchedule> daySchedules;

    public DailyPlanner() {
        this.daySchedules = new ArrayList<>();
    }

    public void addActivity(Day day, String activitiy) {
        if(activitiy == null) {
            throw new NoActivityException("Activity cannot be null");
        }

        DaySchedule daySchedule = getDaySchedule(day);
        daySchedule.addActivity(activitiy);
    }

    private DaySchedule getDaySchedule(Day day) {
        for(DaySchedule daySchedule : daySchedules) {
            if(daySchedule.getDay() == day) {
                return daySchedule;
            }
        }
        DaySchedule newDaySchedule = new DaySchedule(day);
        daySchedules.add(newDaySchedule);
        return newDaySchedule;
    }

    public void removeActivity(Day day, String activity) {
        DaySchedule daySchedule = getDaySchedule(day);

        if (!daySchedule.getActivities().contains(activity)) {
            throw new NoActivityException("Activity not found for the specified day.");
        }

        daySchedule.removeActivity(activity);
    }

    public List<String> getActivities(Day day) {
        DaySchedule daySchedule = getDaySchedule(day);
        return daySchedule.getActivities();
    }

    public Map<Day, List<String>> endPlanning() throws NoActivitiesForDayException {
        Map<Day, List<String>> result = new HashMap<>();

        for (DaySchedule daySchedule : daySchedules) {
            List<String> activities = daySchedule.getActivities();

            if (activities.isEmpty()) {
                throw new NoActivitiesForDayException("Day " + daySchedule.getDay() + " has no activities.");
            }

            result.put(daySchedule.getDay(), activities);
        }

        return result;
    }
}
