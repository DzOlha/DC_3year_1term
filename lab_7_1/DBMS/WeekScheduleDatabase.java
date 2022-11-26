package lab_7_1.DBMS;
import java.sql. *;
/**
 * DESCRIPTION OF THE DATABASE
 * ------------------------------------------------------
 * create database week_schedule;
 * use week_schedule;
 *-------------------------------------------------------
 * create table days
 *      -> (id integer not null,
 *      -> name char(32),
 *      -> primary key (id));
 *-------------------------------------------------------
 * create table lessons
 *       -> (id integer not null,
 *       -> name char(32),
 *       -> isLec boolean,
 *       -> primary key (id));
 *-------------------------------------------------------
 * create table lessons_of_day
 *     -> (id_day integer not null,
 *     -> id_lesson integer not null,
 *     -> foreign key (id_day) references days(id) on delete cascade,
 *     -> foreign key (id_lesson) references lessons(id) on delete cascade);
 * */
public class WeekScheduleDatabase {
    private final Connection con;
    private final Statement stmt;
    public WeekScheduleDatabase(String DBName, String ip, int port) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + DBName;
        con = DriverManager.getConnection(url, "root", "justSOW18!!!");
        stmt = con.createStatement();
    }
    public void showDays(){
        String sql = "SELECT ID, NAME FROM DAYS";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("ПЕРЕЛІК ДНІВ:");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                System.out.println(">>" + id + "-" + name);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(
                    "ПОМИЛКА при отриманні списку днів ");
            System.out.println(" >> " + e.getMessage());
        }
    }
    public void stop() throws SQLException {
        con.close();
    }
    public boolean addDay(int id, String name) {
        String sql = "INSERT INTO DAYS (ID, NAME) " +
                "VALUES (" + id + ", '" + name + "')";
        try {
            stmt.executeUpdate(sql);
            System.out.println("День " + name + " Успішно додано!");
            return true;
        } catch (SQLException e) {
            System.out.println("ПОМИЛКА! День " + name + " не додано !");
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }
    public boolean deleteDay(int id) {
        String sql = "DELETE FROM DAYS WHERE ID =" + id;
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                System.out.println("День з ідентифікатором "
                        + id + " успішно видалено!");
                return true;
            } else {
                System.out.println("День з ідентифікатором "
                        + id + " не знайдено!");

                return false;
            }
        } catch (SQLException e) {
            System.out.println("ПОМИЛКА при видаленні дня тижня з ідентифікатором " + id);
                    System.out.println(" >> " + e.getMessage());
            return false;
        }
    }
    public boolean addLesson(int id, String name, boolean isLec){
        String sql = "INSERT INTO LESSONS (ID, NAME, IS_LEC) " +
                "VALUES (" + id + ", '" + name + "', " + isLec + ")";
        try {
            stmt.executeUpdate(sql);
            System.out.println("Заняття " + name + " Успішно додано!");
            return true;
        } catch (SQLException e) {
            System.out.println("ПОМИЛКА! Заняття " + name + " не додано !");
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }
    public boolean deleteLesson(int lesson_id){
        String sql = "DELETE FROM LESSONS WHERE ID =" + lesson_id;
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                System.out.println("Заняття з ідентифікатором "
                        + lesson_id + " успішно видалено!");
                return true;
            } else {
                System.out.println("Заняття з ідентифікатором "
                        + lesson_id + " не знайдено!");

                return false;
            }
        } catch (SQLException e) {
            System.out.println("ПОМИЛКА при видаленні заняття з ідентифікатором " + lesson_id);
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }
    public boolean addLessonToTheDay(int lesson_id, String lessonName, boolean isLec, int day_id, String dayName) {
        boolean addlessonSuccessfully = addLesson(lesson_id, lessonName, isLec);
        if(addlessonSuccessfully == false){
            System.out.println("ПОМИЛКА при додаванні зяняття " + lessonName + " у відповідну таблицю -> неможливе додавання до розкладу!");
            return false;
        }
        String sql = "INSERT INTO LESSONS_OF_DAY (ID_DAY, ID_LESSON) " +
                "VALUES (" + day_id + ", " + lesson_id + ")";
        try {
            stmt.executeUpdate(sql);
            System.out.println("Заняття " + lessonName + " Успішно додано до розкладу дня " + dayName);
            return true;
        } catch (SQLException e) {
            System.out.println("ПОМИЛКА! Заняття " + lessonName + " не додано до розкладу дня " + dayName);
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }
    public boolean deleteLessonFromTheDay(int lesson_id, String lessonName, int day_id, String dayName){
        String sql = "DELETE FROM LESSONS_OF_DAY WHERE ID_DAY =" + day_id + " AND ID_LESSON =" + lesson_id;
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                System.out.println("Заняття з ідентифікатором "
                        + lesson_id + "(" + lessonName + ") " + " успішно видалено з розкладу дня " + dayName);
                return true;
            } else {
                System.out.println("Заняття з ідентифікатором "
                        + lesson_id + "(" + lessonName + ") " + " не знайдено в розкладі дня " + dayName);

                return false;
            }
        } catch (SQLException e) {
            System.out.println("ПОМИЛКА при видаленні заняття з ідентифікатором " + lesson_id + "(" + lessonName + ")" + " з розкладу дня " + dayName);
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }
}
