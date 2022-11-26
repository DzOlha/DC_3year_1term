package lab_7.xml;

public class Main {
    public static void main(String[] args)
    {
        String filenamePathXML = "lab_7/xml/WeekSchedule.xml";
        String filenamePathXSD = "lab_7/xml/WeekSchedule.xsd";

        String testXML = "lab_7/xml/OutputXML.xml";
        WorkWithXML ws = new WorkWithXML(testXML);
        WorkWithXML xml = new WorkWithXML(filenamePathXML, filenamePathXSD);

        WeekSchedule schedule = new WeekSchedule();

        schedule.loadFromFile(xml);
        try {
            schedule.uploadToDatabase();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        schedule.uploadToNewFile(ws);
        //schedule.uploadToNewFile(ws);
        //schedule.printSchedule();
    }
}
