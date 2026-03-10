// تسجيل الحركات
public class Action {

    public enum Type {
        ADD_BOOK, BORROW_BOOK, RETURN_BOOK, JOIN_WAITLIST
    }

    private Type type;
    private Book book;
    private String userName;
    private boolean wasAutoAssigned; // هل تم تحويل الكتاب تلقائياً لمستخدم آخر؟

    //  للحركات البسيطة مثل إضافة كتاب
    public Action(Type type, Book book) {
        this(type, book, null, false);
    }

    //  للحركات مثل استعارة كتاب 
    public Action(Type type, Book book, String userName) {
        this(type, book, userName, false);
    }

    //  للحركات التي فيها تعيين تلقائي Constructor كامل 
    public Action(Type type, Book book, String userName, boolean auto) {
        this.type = type;
        this.book = book;
        this.userName = userName;
        this.wasAutoAssigned = auto;
    }

    public Type getType() {
        return type;
    }

    public Book getBook() {
        return book;
    }

    public String getUserName() {
        return userName;
    }

    public boolean wasAutoAssigned() {
        return wasAutoAssigned;
    }
// to String تعرض نوع الحرمة والكتاب  المتاثر 

    @Override
    public String toString() {
        return type + " on " + book.getTitle();
    }
}