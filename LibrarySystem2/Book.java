public class Book {
    private String id;
    private String title;
    private boolean isAvailable;

    // waitlist
    private LinkedList<String> waitlist;

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
        this.waitlist = new LinkedList<>();
    }

    public String getBookId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean status) {
        this.isAvailable = status;
    }

    // waitlist managemnent

    public boolean joinWaitlist(String user) {
        if (waitlist.contains(user))
            return false; // منع التكرار
        waitlist.add(user);
        return true;
    }

    public String getNextFromWaitlist() {
        if (waitlist.isEmpty())
            return null;
        // use getter
        String user = waitlist.getHead().getData();
        waitlist.deleteFirst(); // FIFO
        return user;
    }

    // for undoing
    public boolean removeFromWaitlist(String user) {
        return waitlist.remove(user);
    }

    public boolean hasWaitlist() {
        return !waitlist.isEmpty();
    }

    public int getWaitlistSize() {
        return waitlist.size();
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed";
        String wl = waitlist.size() > 0 ? " [Waitlist: " + waitlist.size() + "]" : "";
        return id + ": " + title + " (" + status + ")" + wl;
    }
}