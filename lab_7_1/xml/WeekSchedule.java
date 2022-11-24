package lab_7_1.xml;

import lab_7_1.DBMS.WeekScheduleDatabase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class WeekSchedule {
    private ArrayList<WeekDay> days;
    public void loadFromFile(WorkWithXML xml){
        //days = new ArrayList<>();
        Element root = xml.loadRootFromXML();
        if (root.getTagName().equals("WeekSchedule"))
        {
            NodeList listWeekDays = root.getElementsByTagName("WeekDay");
            int countOfWeekDays = listWeekDays.getLength();
            for (int i = 0; i < countOfWeekDays; i++)
            {
                Element weekDay = (Element) listWeekDays.item(i);
                String weekDayCode = weekDay.getAttribute("id");
                String weekDayName = weekDay.getAttribute("name");
                days.add(new WeekDay(Integer.parseInt(weekDayCode), weekDayName));

                NodeList listLessons = weekDay.getElementsByTagName("Lesson");

                int countOfLessons = listLessons.getLength();
                for (int j = 0; j < countOfLessons; j++)
                {
                    Element lesson = (Element) listLessons.item(j);
                    String lessonCode = lesson.getAttribute("id");
                    String lessonName = lesson.getAttribute("name");
                    String isLec = lesson.getAttribute("isLec");
                    days.get(i).lessons.add(new Lesson(Integer.parseInt(lessonCode), lessonName, Integer.parseInt(isLec)));
                }
            }
        }
    }
    public WeekSchedule uploadToNewFile(WorkWithXML xml_new){
        WeekSchedule newSchedule = new WeekSchedule();
        try{
            xml_new.createXmlDocument(days);
            newSchedule.loadFromFile(xml_new);
            //newSchedule.printSchedule();
        } catch(ParserConfigurationException pce){
            pce.printStackTrace();
        } catch(TransformerException tre){
            tre.printStackTrace();
        }
        return newSchedule;
    }
    public void uploadToDatabase() throws Exception {
        WeekScheduleDatabase db = new WeekScheduleDatabase("week_schedule", "localhost", 3306);
        int countOfWeekDays = days.size();
        int countOfLessons = 0;
        WeekDay tmpDay = null;
        Lesson tmpLesson = null;
        for(int i = 0; i < countOfWeekDays; i++)
        {
            tmpDay = days.get(i);
            db.addDay(tmpDay.code, tmpDay.name);

            countOfLessons = tmpDay.lessons.size();
            for(int j = 0; j < countOfLessons; j++)
            {
                tmpLesson = tmpDay.lessons.get(j);
                db.addLessonToTheDay(tmpLesson.code, tmpLesson.name, tmpLesson.isLec, tmpDay.code, tmpDay.name);
            }
        }
    }
    public WeekSchedule(){
        days = new ArrayList<>();
    }
    public void printSchedule(){
        int countOfWeekDays = days.size();
        int countOfLessons = 0;
        WeekDay tmpDay = null;
        Lesson tmpLesson = null;
        String lessonType = "";
        for(int i = 0; i < countOfWeekDays; i++)
        {
            tmpDay = days.get(i);
            System.out.println("\n" + tmpDay.name + ": ");
            countOfLessons = tmpDay.lessons.size();
            for(int j = 0; j < countOfLessons; j++)
            {
                tmpLesson = tmpDay.lessons.get(j);
                //System.out.println("Code = " + tmpLesson.code);
                if(tmpLesson.isLec == 1)
                    lessonType = "LEC";
                else
                    lessonType = "LAB";
                System.out.println(tmpLesson.name + " " + lessonType);
            }
        }
    }
}
