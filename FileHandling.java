import java.io.*;
import java.util.Scanner;

     public class FileHandling {

    static Scanner input = new Scanner(System.in);

    static int[] ids = new int[100];
    static String[] names = new String[100];
    static int count = 0;

    static String fileName = "records.csv";

    
    static void loadRecords() {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                ids[count] = Integer.parseInt(data[0]);
                names[count] = data[1];
                count++;
            }

            fileReader.close();

        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    
    static void saveRecords() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            for (int i = 0; i < count; i++) {
                writer.println(ids[i] + "," + names[i]);
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    
    static void addRecord() {

        System.out.print("Enter ID: ");
        ids[count] = input.nextInt();
        input.nextLine();

        System.out.print("Enter Name: ");
        names[count] = input.nextLine();

        count++;

        saveRecords();
    }

    
    static void displayRecords() {

        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(i + " | ID: " + ids[i] + " | Name: " + names[i]);
        }
    }

    
    static void updateRecord() {

        displayRecords();

        System.out.print("Enter index to update: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 0 || index >= count) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.print("New ID: ");
        ids[index] = input.nextInt();
        input.nextLine();

        System.out.print("New Name: ");
        names[index] = input.nextLine();

        saveRecords();
    }

    
    static void deleteRecord() {

        displayRecords();

        System.out.print("Enter index to delete: ");
        int index = input.nextInt();

        if (index < 0 || index >= count) {
            System.out.println("Invalid index.");
            return;
        }

        for (int i = index; i < count - 1; i++) {
            ids[i] = ids[i + 1];
            names[i] = names[i + 1];
        }

        count--;

        saveRecords();
    }

    public static void main(String[] args) {

        loadRecords();

        int choice;

        do {

            System.out.println("\n=== RECORD SYSTEM ===");
            System.out.println("1. Add Record");
            System.out.println("2. Display Records");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");

            System.out.print("Choose: ");
            choice = input.nextInt();

            switch (choice) {

                case 1:
                    addRecord();
                    break;

                case 2:
                    displayRecords();
                    break;

                case 3:
                    updateRecord();
                    break;

                case 4:
                    deleteRecord();
                    break;

                case 5:
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}