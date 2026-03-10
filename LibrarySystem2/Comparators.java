import java.util.Comparator;

// مقارن الكتب حسب الـ ID BST
public class Comparators implements Comparator<BookIndexEntry> {
    
    @Override
    public int compare(BookIndexEntry e1, BookIndexEntry e2) {
        // مقارنة أبجدية (Lexicographic)
        return e1.getBookId().compareToIgnoreCase(e2.getBookId());
    }
}