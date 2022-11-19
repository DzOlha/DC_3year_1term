package lab_7_1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WeekSchedule {
    private String sourceFileName;
    private ArrayList<WeekDay> days;
    public void loadFromFile(){
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = db.parse(new File(sourceFileName));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root = doc.getDocumentElement();
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
    public WeekSchedule(String filename){
        days = new ArrayList<>();
        sourceFileName = filename;
        loadFromFile();
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
