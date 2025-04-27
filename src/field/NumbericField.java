package field;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NumbericField {
    int[][] numbericField;

    public NumbericField(int n, int m, String path) {
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        numbericField = new int[n][m];

        int c = 0;
        while (scanner.hasNextInt()) {
            numbericField[c / m][c % m] = scanner.nextInt();
            c++;
        }
        scanner.close();
    }

    public int[][] getNumbericField() {
        return numbericField;
    }

    public void setNumbericField(int[][] numbericField) {
        this.numbericField = numbericField;
    }
}
