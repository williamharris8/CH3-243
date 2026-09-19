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
}
