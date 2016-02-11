import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static HashMap<Character, ArrayList<Country>> countries = new HashMap<>();
    public static Scanner consoleScanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        readFile("countries.txt");
        System.out.print("Type a letter: ");
        char firstLetter = (consoleScanner.nextLine()).charAt(0);
        int i = 0;
        for (Country country : countries.get(firstLetter)) {
            writeFile(String.format("%s_countries", firstLetter), countries.get(firstLetter).get(i).countryName);
            i++
        }

    }


    static void readFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lines = line.split("\\|");       //takes to backslashes to escape a functionality of | character in a String
            Country country = new Country(lines[1], lines[0]);
            if (!countries.containsKey(country.countryName.charAt(0))) {
                countries.put(country.countryName.charAt(0), new ArrayList<>());
            }
            countries.get(country.countryName.charAt(0)).add(country);
        }
    }

    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.append(fileContent);
        fw.close();
    }
}
