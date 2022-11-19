package lab_7_1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<WeekDay> daysOfWeek = new ArrayList<>();
        WeekDay monday = new WeekDay(1, "Monday");
        daysOfWeek.add(monday);
        WeekSchedule schedule = new WeekSchedule("lab_7_1/WeekSchedule.xml");
        schedule.printSchedule();
    }
}
