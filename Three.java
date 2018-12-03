import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Three {


    private static final String FILE_NAME = "Three.txt";
    private static Logger logger = Logger.getLogger(Two.class.getSimpleName());
    private static HashMap<Point, Integer> points = new HashMap<>();
    private static HashMap<Point, Integer> uniquePoints = new HashMap<>();

    public static void main(String... args) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNext()) {
            Matcher matcher = Pattern.compile("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$").matcher(scan.nextLine());
            matcher.find();
            Integer id = Integer.valueOf(matcher.group(1));
            Integer xStart = Integer.valueOf(matcher.group(2));
            Integer yStart = Integer.valueOf(matcher.group(3));
            Integer xSize = Integer.valueOf(matcher.group(4));
            Integer ySize = Integer.valueOf(matcher.group(5));
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    Point point = new Point(xStart + i, yStart + j);
                    if (points.containsKey(point)) {
                        Integer nrOfOccurences = points.get(point) + 1;
                        points.put(point, nrOfOccurences);
                        uniquePoints.remove(point);
                    } else {
                        points.put(point, 0);
                        uniquePoints.put(point, id);
                    }
                }
            }
        }

        Integer nrOfTotalDuplicates = 0;
        for (Integer duplicates : points.values()) {
            if (duplicates > 0) {
                nrOfTotalDuplicates++;
            }
        }

        logger.info("Total number of duplicate points: " + nrOfTotalDuplicates.toString());
        Scanner again = null;

        try {
            again = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (again.hasNext()) {
            Matcher matcher = Pattern.compile("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$").matcher(again.nextLine());
            matcher.find();
            Integer id = Integer.valueOf(matcher.group(1));
            Integer xStart = Integer.valueOf(matcher.group(2));
            Integer yStart = Integer.valueOf(matcher.group(3));
            Integer xSize = Integer.valueOf(matcher.group(4));
            Integer ySize = Integer.valueOf(matcher.group(5));
            AtomicBoolean uniqueSheet = new AtomicBoolean(true);
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    Point point = new Point(xStart + i, yStart + j);
                    if (!uniquePoints.containsKey(point)) {
                        uniqueSheet.set(false);
                    }
                }
            }
            if(uniqueSheet.get()) {
                logger.info("UNIQUE SHEET: " + id);
            }
        }
    }
}

