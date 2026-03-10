public class Request {
    // أنواع الطلبات المتاحة
    public enum Type { BORROW, RETURN }
    
    private Type type;
    private String bookId;
    private String userName;

    public Request(Type type, String bookId, String userName) {
        this.type = type;
        this.bookId = bookId;
        this.userName = userName;
    }

    public Type getType() { return type; }
    public String getBookId() { return bookId; }
    public String getUserName() { return userName; }

    @Override
    public String toString() {
        return "[" + type + "] User: " + userName + ", BookID: " + bookId;
    }
}