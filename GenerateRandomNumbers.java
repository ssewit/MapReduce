import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GenerateRandomNumbers {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Prompt for number of random points
    System.out.println("How many random numbers to generate? (e.g., 1000000): ");
    int numberOfPoints = scanner.nextInt();

    // Prompt for radius
    System.out.println("What's the radius? (e.g., 200): ");
    int radius = scanner.nextInt();

    scanner.close(); // Close the scanner after reading input

    int diameter = radius * 2;

    try {
      // Create the output file (improve error handling)
      File outputFile = new File("./PiCalculationInput");
      if (!outputFile.createNewFile()) {
        System.out.println("File already exists: " + outputFile.getAbsolutePath());
      }

      // Generate and write random points to the file
      FileWriter writer = new FileWriter(outputFile);
      for (int i = 0; i < numberOfPoints; i++) {
        int xValue = (int) (Math.random() * diameter);
        int yValue = (int) (Math.random() * diameter);
        writer.write("(" + xValue + "," + yValue + ")\n"); // Use newline for clarity
      }

      writer.flush();
      writer.close();

    } catch (IOException e) {
      System.err.println("Error generating random numbers: " + e.getMessage());
    }
  }
}
