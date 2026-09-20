import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder documents = new Folder("documents");
        Folder pictures = new Folder("pictures");
        Folder vacations = new Folder("vacations");

        root.addItem(documents);
        root.addItem(pictures);
        pictures.addItem(vacations);

        documents.addItem(new FileItem("resume.docx", 120));
        documents.addItem(new FileItem("budget.xlsx", 85));
        pictures.addItem(new FileItem("profile.png", 540));
        vacations.addItem(new FileItem("beach.jpg", 2048));
        vacations.addItem(new FileItem("mountain.jpg", 1830));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1. Display File System Structure");
            System.out.println("2. Add File to a Folder");
            System.out.println("3. Add Subfolder");
            System.out.println("4. Run Recursive Audit");
            System.out.println("5. Run Iterative Audit & Verification");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    FileSystemAnalyzer.printHierarchy(root, "");
                    break;

                case 2:
                    System.out.print("File name: ");
                    String fileName = scanner.nextLine().trim();

                    System.out.print("Size in KB: ");
                    int size;
                    try {
                        size = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Size must be a number.");
                        break;
                    }
                    if (size < 0) {
                        System.out.println("Size cannot be negative.");
                        break;
                    }

                    System.out.print("Folder to add it to: ");
                    String targetName = scanner.nextLine().trim();
                    Folder target = FileSystemAnalyzer.findFolder(root, targetName);
                    if (target == null) {
                        System.out.println("Folder not found.");
                    } else {
                        target.addItem(new FileItem(fileName, size));
                        System.out.println("File added.");
                    }
                    break;

                case 3:
                    System.out.print("New folder name: ");
                    String newFolderName = scanner.nextLine().trim();

                    System.out.print("Parent folder name: ");
                    String parentName = scanner.nextLine().trim();
                    Folder parent = FileSystemAnalyzer.findFolder(root, parentName);
                    if (parent == null) {
                        System.out.println("Folder not found.");
                    } else {
                        parent.addItem(new Folder(newFolderName));
                        System.out.println("Folder added.");
                    }
                    break;

                case 4:
                    System.out.println("Total files: " + FileSystemAnalyzer.countFilesRecursive(root));
                    System.out.println("Total size: " + FileSystemAnalyzer.calculateTotalSizeRecursive(root) + " KB");
                    FileItem largest = FileSystemAnalyzer.findLargestFileRecursive(root);
                    if (largest == null) {
                        System.out.println("No files found.");
                    } else {
                        System.out.println("Largest file: " + largest.getName() + " (" + largest.getSizeInKB() + " KB)");
                    }
                    break;

                case 5:
                    int iterativeCount = FileSystemAnalyzer.countFilesIterative(root);
                    int recursiveCount = FileSystemAnalyzer.countFilesRecursive(root);
                    System.out.println("Iterative count: " + iterativeCount);
                    System.out.println("Recursive count: " + recursiveCount);
                    if (iterativeCount == recursiveCount) {
                        System.out.println("The counts match!");
                    } else {
                        System.out.println("The counts do NOT match.");
                    }
                    break;

                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Pick 1-6.");
            }
        }
    }
}


