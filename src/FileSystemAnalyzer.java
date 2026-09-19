import java.util.Stack;

public class FileSystemAnalyzer {

    public static int countFilesRecursive(FileSystemItem item){
        if (item instanceof FileItem) {
            return 1;
        }

        Folder folder = (Folder) item;
        int count = 0;
        for (FileSystemItem child : folder.getItems()) {
            count = count + countFilesRecursive(child);
        }
        return count;
    }

    public static void printHierarchy(FileSystemItem item, String indent) {
        if (item instanceof FileItem) {
            System.out.println(indent + item.getName() + " (" +item.getSizeInKB() + " KB)");
        } else {
            Folder folder = (Folder) item;
            System.out.println(indent + folder.getName() + "/");
            for (FileSystemItem child : folder.getItems()) {
                printHierarchy(child, indent + "  ");
            }
        }
    }
    public static int calculateTotalSizeRecursive(FileSystemItem item) {
        if (item instanceof FileItem) {
            return item.getSizeInKB();
        }

        Folder folder = (Folder) item;
        int total = 0;
        for (FileSystemItem child : folder.getItems()) {
            total = total + calculateTotalSizeRecursive(child);
        }
        return total;
    }

    public static FileItem findLargestFileRecursive(FileSystemItem item) {
        if (item instanceof FileItem) {
            return (FileItem) item;
        }

        Folder folder = (Folder) item;
        FileItem largest = null;
        for (FileSystemItem child : folder.getItems()) {
            FileItem childMax = findLargestFileRecursive(child);
            if (childMax != null) {
                if (largest == null || childMax.getSizeInKB() > largest.getSizeInKB()) {
                    largest = childMax;
                }
            }
        }
        return largest;
    }

    public static int countFilesIterative(Folder rootFolder) {
        Stack<FileSystemItem> stack = new Stack<FileSystemItem>();
        stack.push(rootFolder);
        int fileCount = 0;

        while (!stack.isEmpty()) {
            FileSystemItem current = stack.pop();
            if (current instanceof FileItem) {
                fileCount++;
            } else {
                Folder folder = (Folder) current;
                for (FileSystemItem child : folder.getItems()) {
                    stack.push(child);
                }
            }
        }
        return fileCount;
    }
}
