import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Three {


    private static final String FILE_NAME = "Three.txt";
    private static Logger logger = Logger.getLogger(Two.class.getSimpleName());
    private static HashMap<Point, Integer> points = new HashMap<>();

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
            Integer xStart = Integer.valueOf(matcher.group(2));
            Integer yStart = Integer.valueOf(matcher.group(3));
            Integer xSize = Integer.valueOf(matcher.group(4));
            Integer ySize = Integer.valueOf(matcher.group(5));
            for(int i = 0; i < xSize; i++) {
                for(int j = 0; j < ySize; j++) {
                    Point point = new Point(xStart + i, yStart + j);
                    if(points.containsKey(point)) {
                        Integer nrOfOccurences = points.get(point) + 1;
                        points.put(point, nrOfOccurences);
                    }
                    else {
                        points.put(point, 0);
                    }
                }
            }
        }

        Integer nrOfTotalDuplicates = 0;
        for(Integer duplicates : points.values()) {
            if(duplicates > 0) {
                nrOfTotalDuplicates++;
            }
        }

        logger.info(nrOfTotalDuplicates.toString());

    }

    private class myPoint {
        public Point point;
        public Integer occurence;
    }
}
