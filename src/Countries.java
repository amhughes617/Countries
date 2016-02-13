import jodd.json.JsonSerializer;
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

    public static void main(String[] args) throws Exception {
        readFile("countries.txt");

        while (true) {
            System.out.print("Save all countries that begin with the letter: ");
            String entry = consoleScanner.nextLine().toLowerCase();
            if (entry.length() != 1) throw new Exception("Input must be a single letter!!!");
            char firstLetter = entry.charAt(0);
            try {
                writeFile(firstLetter);
                writeJson(firstLetter);
                break;
            }
            catch (Exception e) {
                System.out.printf("No countries beginning with %s exist.\n", firstLetter);
            }
        }
    }

//Read/Write methods below

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
    static void writeFile(Character fileName) throws IOException {
        int i = 0;
        for (Country country : countries.get(fileName)) {
            File f = new File(String.format("%s_countries.txt", fileName.toUpperCase(fileName))); //capitalizes the first letter of the file name
            FileWriter fw = new FileWriter(f, true);
            String countryName = countries.get(fileName).get(i).countryName;    //did these in temp variables to
            String countryAbbr = countries.get(fileName).get(i).abbreviation;   //make fileContent String less ugly
            String fileContent = String.format("%s|%s\n", countryAbbr, countryName);//created this string in method
            fw.append(fileContent);                                                 //so i wouldn't have to pass in
            fw.close();                                                             //an enormous String.format as
            i++;
            //a fileContent argument
        }
    }
    static void writeJson(Character fileName) throws IOException { //writes a separate json file
            File f = new File(String.format("%s_countries.json", fileName.toUpperCase(fileName)));
            JsonSerializer serializer = new JsonSerializer();
            String json = serializer.include("*").serialize(countries.get(fileName));
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
    }
}
