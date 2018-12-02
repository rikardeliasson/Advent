import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class Two {

    private static Logger logger = Logger.getLogger(Two.class.getSimpleName());
    private static final String FILE_NAME = "Two.txt";
    private static ArrayList<String> packages = new ArrayList<>();

    public static void main(String args[]) {
        try {
            scanPackages();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("CHECKSUM: " + calculateCheckSum());

        findCommonString();
    }


    private static void scanPackages() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(FILE_NAME));
        while (scan.hasNext()) {
            packages.add(scan.nextLine());
        }
    }

    private static Integer calculateCheckSum() {

        Integer twoOccurences = 0;
        Integer threeOccurences = 0;


        for(String packageId : packages) {
            AtomicBoolean twosAlreadyCounted = new AtomicBoolean();
            AtomicBoolean threesAlreadyCounted = new AtomicBoolean();
            for(int i = 0; i < packageId.length(); i++) {
                Character partOfId = packageId.charAt(i);
                long count = packageId.chars().filter(ch -> ch == partOfId).count();
                if (count == 2 && !twosAlreadyCounted.get()) {
                    twoOccurences++;
                    twosAlreadyCounted.set(true);
                }
                else if (count == 3 && !threesAlreadyCounted.get()) {
                    threeOccurences++;
                    threesAlreadyCounted.set(true);
                }
            }
        }
        return twoOccurences * threeOccurences;

    }

    private static void findCommonString() {
        for(String packageId : packages) {

            for(String otherPackageId : packages) {
                Integer numOfDifferences = 0;
                String result = "";
                for(int i = 0; i < packageId.length(); i++) {
                    if(packageId.charAt(i) != otherPackageId.charAt(i)) {
                        numOfDifferences++;
                    }
                    else {
                        result += packageId.charAt(i);
                    }
                    if(numOfDifferences > 1) {
                        break;
                    }
                }
                if (numOfDifferences == 1) {
                    logger.info("Two strings: " + packageId + " and " + otherPackageId + "are identical-isch");
                    logger.info("The string " + result + " presents what they have in common");
                    break;
                }
            }

        }
    }

}
