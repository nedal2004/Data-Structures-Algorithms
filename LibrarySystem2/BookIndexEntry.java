// مدخل الفهرس: يخزن في الـ BST للبحث والترتيب السريع
public class BookIndexEntry {
    
    private String bookId;
    private String title;
    private Book bookRef; // مرجع للكتاب الأصلي في الـ inventory
    
    public BookIndexEntry(String bookId, String title, Book bookRef) {
        this.bookId = bookId;
        this.title = title;
        this.bookRef = bookRef;
    }
    
    public String getBookId() { 
        return bookId; 
    }
    
    public String getTitle() { 
        return title; 
    }
    
    public Book getBookRef() { 
        return bookRef; 
    }
    
    @Override
    public String toString() {
        String status = bookRef.isAvailable() ? "Available" : "Borrowed";
        String wl = bookRef.hasWaitlist() ? " [Waitlist: " + bookRef.getWaitlistSize() + "]" : "";
        return bookId + ": " + title + " (" + status + ")" + wl;
    }
}