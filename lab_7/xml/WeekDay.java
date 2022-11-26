package lab_7.xml;

import java.util.ArrayList;

public class WeekDay {
    public final Integer code;
    public String name;
    ArrayList<Lesson> lessons;
    public WeekDay(Integer code, String name){
        this.code = code;
        this.name = name;
        lessons = new ArrayList<>();
    }
}
