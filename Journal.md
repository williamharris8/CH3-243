PHASE 1 - The base case is the if (item instanceof FileItem) check that returns 1. General case is when the item is a Folder. Always smaller since the method is never called on the same folder again, only on that folders children. It terminates because the tree has a limited number of items and levels.

PHASE 2 - When a folder only has empty subfolders the method checks each. Subfolders have nothing inside so its loop never runs and returns null. This works well because there is no real file to return and its easy to check for. Code avoids NullPointerException by checking childMax != null before using childMax. Also checks largest == null first so it never calls a method ona  null largest.

PHASE 3 - Recursive version has more overhead time because every call makes java set upa new method call. The iterative version pushes and pops so its faster. For overhead space recursion saves a record on the call stack for every level it goes down which could cause a StackOverflowError. Iterative uses its own stack so doesnt have that problem. Recursive version in my opinion is easier to write and because it just says a folders count is the total of its childrens counts.

