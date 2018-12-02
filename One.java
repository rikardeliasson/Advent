import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class One {

    private static Logger logger = Logger.getLogger(One.class.getSimpleName());
    private final static String FILE_NAME = "One.txt";
    private static ArrayList<Integer> integers = new ArrayList<>();
    private static ArrayList<Integer> sums = new ArrayList<>();
    private static Integer sum = 0;
    private static boolean foundDuplicate = false;


    public static void main(String args[]) {

        try {
            readIntegers();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        calculateSum();

    }

    private static void readIntegers() throws FileNotFoundException {
        Integer integer = 0;
        Scanner scan = new Scanner(new File(FILE_NAME));
        while (scan.hasNext()) {
            String number = scan.nextLine();
            if (number.charAt(0) == '+') {
                integer = Integer.parseInt(number.substring(1));
                integers.add(integer);
            }
            else if(number.charAt(0) == '-') {
                integer = Integer.parseInt(number);
                integers.add(integer);
            }
        }
    }

    private static void calculateSum() {
        for(Integer tempSum : integers) {
            sum += tempSum;
            if(sums.contains(sum)) {
                foundDuplicate = true;
                logger.info("FIRST DUPLICATE: " + sum);
            }
            sums.add(sum);
        }

        if(!foundDuplicate) {
            calculateSum();
        }

    }


}
