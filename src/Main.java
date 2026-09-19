public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder documents = new Folder("documents");
        Folder pictures = new Folder("pictures");
        Folder vacations = new Folder("vacations");

        root.addItem(documents);
        root.addItem(pictures);
        root.addItem(vacations);

        documents.addItem(new FileItem("resume.docx", 120));
        documents.addItem(new FileItem("budget.xlsx", 85));
        pictures.addItem(new FileItem("profile.png", 540));
        vacations.addItem(new FileItem("beach.jpg", 2048));
        vacations.addItem(new FileItem("mountain.jpg", 1830));

        FileSystemAnalyzer.printHierarchy(root, "");
        System.out.println("Total files: " + FileSystemAnalyzer.countFilesRecursive(root));

        System.out.println("Total size: " + FileSystemAnalyzer.calculateTotalSizeRecursive(root) + " KB");

        FileItem largest = FileSystemAnalyzer.findLargestFileRecursive(root);
        System.out.println("Largest file: " + largest.getName() + " (" + largest.getSizeInKB() + " KB)");

        int recursiveCount = FileSystemAnalyzer.countFilesRecursive(root);
        int iterativeCount = FileSystemAnalyzer.countFilesIterative(root);
        System.out.println("Iterative count: " + iterativeCount);
        System.out.println("Counts match: " + (recursiveCount == iterativeCount));
    }
}
