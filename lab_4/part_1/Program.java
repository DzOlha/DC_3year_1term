package lab_4.part_1;


import java.util.Random;
import static java.lang.System.*;

public class Program {
    private static final String fileName = "lab_4/part_1/database.txt";

    public static void main(String... args) {
        Locker lock = new Locker();
        Reader reader = new Reader(fileName, lock);
        Writer writer = new Writer(fileName, lock);

        try {
            out.println("Status of adding operation: " +
                    writer.changeFile(Instruction.ADD, "Name" + new Random().nextInt(100),
                            "11" + (new Random().nextInt(8999) + 1000)));
            out.println("Name with number 26352673: " +
                    reader.completeSearch(Instruction.FIND_NAME_BY_NUMBER, "26352673"));
            out.println("Number of Georg: " +
                    reader.completeSearch(Instruction.FIND_NUMBER_BY_NAME, "Georg"));
            out.println("Name with number 50505: " +
                    reader.completeSearch(Instruction.FIND_NAME_BY_NUMBER, "50505"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}