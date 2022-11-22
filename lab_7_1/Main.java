package lab_7_1;

public class Main {
    public static void main(String[] args)
    {
        String filenamePathXML = "lab_7_1/WeekSchedule.xml";
        String filenamePathXSD = "lab_7_1/WeekSchedule.xsd";

        String testXML = "lab_7_1/test.xml";
        WorkWithXML ws = new WorkWithXML(testXML);
        WorkWithXML xml = new WorkWithXML(filenamePathXML, filenamePathXSD);

        WeekSchedule schedule = new WeekSchedule();

        schedule.loadFromFile(xml);
        schedule.uploadToNewFile(ws);
        //schedule.printSchedule();
    }
}
