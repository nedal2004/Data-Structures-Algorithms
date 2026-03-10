import java.util.Scanner;

public class LibrarySystem {

    private static MyArrayList<Book> inventory = new MyArrayList<>();
    private static MyArrayList<Book> borrowedList = new MyArrayList<>();

    // Stack لتتبع الحركات للتراجع
    private static LinkedStack<Action> historyStack = new LinkedStack<>();

    // Queue لطلبات الخدمة
    private static LinkedQueue<Request> serviceQueue = new LinkedQueue<>();

    //  BST  للبحث السريع
    private static BinaryTree<BookIndexEntry> bookIndex = new BinaryTree<>();
    private static Comparators idComparator = new Comparators();

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("\n--- Library System (Phase 3) ---");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Inventory");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. Join Waitlist");
            System.out.println("7. Undo Last Action");
            System.out.println("8. Add Service Request");
            System.out.println("9. Process Service Request");
            System.out.println("10. View Pending Requests");
            System.out.println("--- Phase 3 Features (BST) ---");
            System.out.println("11. View Inventory (Sorted by ID)");
            System.out.println("12. Search Book by ID (BST)");
            System.out.println("13. Find Books by ID Range");
            System.out.println("14. Tree Explorer");
            System.out.println("15. Exit");
            System.out.print("Choose: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // clear buffer
                handleChoice(choice);
            } else {
                input.next();
                System.out.println("Please enter a number.");
            }

        } while (choice != 15);
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                viewBooks(inventory, "Inventory");
                break;
            case 5:
                viewBooks(borrowedList, "Borrowed Books");
                break;
            case 6:
                handleWaitlist();
                break;
            case 7:
                undoLastAction();
                break;
            case 8:
                addServiceRequest();
                break;
            case 9:
                processRequest();
                break;
            case 10:
                viewRequests();
                break;
            case 11: 
                viewInventorySorted();
                break;
            case 12:
                searchBookByIdBST();
                break;
            case 13: 
                findBooksByRange();
                break;
            case 14: 
                treeExplorer();
                break;
            case 15:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }


    // 1. إضافة كتاب
    private static void addNewBook() {
        System.out.print("Enter Book ID: ");
        String id = input.nextLine();

        // فحص إذا كان الـ ID موجود مسبقاً
        if (findBook(id) != null) {
            System.out.println("Error: A book with ID '" + id + "' already exists in the system!");
            return;
        }

        System.out.print("Enter Book Title: ");
        String title = input.nextLine();

        Book b = new Book(id, title);
        inventory.add(b);
        
       
        BookIndexEntry entry = new BookIndexEntry(id, title, b);
        bookIndex.add(entry, idComparator);
        
        historyStack.push(new Action(Action.Type.ADD_BOOK, b));
        System.out.println("Book added successfully.");
    }

    // 2. استعارة كتاب
    private static void borrowBook() {
        System.out.print("Book ID: ");
        String id = input.nextLine();
        Book b = findBook(id);

        if (b != null) {
            if (!b.isAvailable()) {
                System.out.println("Book is currently borrowed.");
                return;
            }
            b.setAvailable(false);
            borrowedList.add(b);
            historyStack.push(new Action(Action.Type.BORROW_BOOK, b));
            System.out.println("You borrowed: " + b.getTitle());
        } else {
            System.out.println("Book not found.");
        }
    }

    // 3. إرجاع كتاب
    private static void returnBook() {
        System.out.print("Book ID to return: ");
        String id = input.nextLine();

        // البحث في قائمة الكتب المستعارة
        for (int i = 0; i < borrowedList.size(); i++) {
            Book b = borrowedList.get(i);
            if (b.getBookId().equalsIgnoreCase(id)) {

                borrowedList.remove(i);

                // فحص قائمة الانتظار
                if (b.hasWaitlist()) {
                    String nextUser = b.getNextFromWaitlist();
                    borrowedList.add(b);
                    historyStack.push(new Action(Action.Type.BORROW_BOOK, b, nextUser, true));
                    System.out.println("Returned and auto-assigned to: " + nextUser);
                } else {
                    b.setAvailable(true);
                    historyStack.push(new Action(Action.Type.RETURN_BOOK, b));
                    System.out.println("Returned successfully.");
                }
                return;
            }
        }
        System.out.println("This book is not in the borrowed list.");
    }

    // 6. الانضمام لقائمة الانتظار
    private static void handleWaitlist() {
        System.out.print("Book ID: ");
        String id = input.nextLine();
        Book b = findBook(id);
        if (b != null) {
            if (b.isAvailable()) {
                System.out.println("Book is available. You can borrow it directly!");
                return;
            }
            System.out.print("Your Name: ");
            String name = input.nextLine();
            if (b.joinWaitlist(name)) {
                historyStack.push(new Action(Action.Type.JOIN_WAITLIST, b, name));
                System.out.println("Added to waitlist. Position: " + b.getWaitlistSize());
            } else {
                System.out.println("You are already in the waitlist.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // 7. التراجع (Undo Logic)
    private static void undoLastAction() {
        if (historyStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        
        Action last = historyStack.pop();
        Book b = last.getBook();
        System.out.println("Undoing: " + last);

        switch (last.getType()) {
            case ADD_BOOK:
                // الحذف من الـ Inventory
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i) == b) {
                        inventory.remove(i);
                        
                        //  حذف من BST
                        BookIndexEntry entryToDelete = new BookIndexEntry(b.getBookId(), b.getTitle(), b);
                        bookIndex.delete(entryToDelete, idComparator);
                        
                        System.out.println(" Book removed from library.");
                        break;
                    }
                }
                break;

            case BORROW_BOOK:
                if (last.wasAutoAssigned()) {
                    b.joinWaitlist(last.getUserName());
                    System.out.println(" Auto-assignment cancelled. User re-added to waitlist.");
                } else {
                    b.setAvailable(true);
                    for (int i = 0; i < borrowedList.size(); i++) {
                        if (borrowedList.get(i) == b) {
                            borrowedList.remove(i);
                            System.out.println("✓ Borrow cancelled. Book is now available.");
                            break;
                        }
                    }
                }
                break;

            case RETURN_BOOK:
                b.setAvailable(false);
                borrowedList.add(b);
                System.out.println(" Return cancelled. Book marked as borrowed again.");
                break;

            case JOIN_WAITLIST:
                if (b.removeFromWaitlist(last.getUserName())) {
                    System.out.println(" Waitlist join cancelled.");
                } else {
                    System.out.println(" Could not remove from waitlist.");
                }
                break;
        }
    }

    // 8. إضافة طلب (Queue)
    private static void addServiceRequest() {
        System.out.println("\nRequest Type:");
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.print("Choose: ");

        int typeChoice = input.nextInt();
        input.nextLine();

        Request.Type reqType;
        if (typeChoice == 1) {
            reqType = Request.Type.BORROW;
        } else if (typeChoice == 2) {
            reqType = Request.Type.RETURN;
        } else {
            System.out.println("Invalid request type.");
            return;
        }

        System.out.print("Enter User Name: ");
        String userName = input.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = input.nextLine();

        // التحقق من التكرار
        for (Request r : serviceQueue) {
            if (r.getUserName().equalsIgnoreCase(userName) &&
                    r.getBookId().equalsIgnoreCase(bookId)) {
                System.out.println(" Error: A request for this book by the same user is already in the queue.");
                return;
            }
        }

        serviceQueue.enqueue(new Request(reqType, bookId, userName));
        System.out.println(" Request added to the Service Desk queue.");
    }

    // 9. معالجة الطلب
    private static void processRequest() {
        if (serviceQueue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        Request r = serviceQueue.dequeue();
        System.out.println("\nProcessing: " + r);

        String bookId = r.getBookId();

        if (r.getType() == Request.Type.BORROW) {
            Book b = findBook(bookId);
            if (b == null) {
                System.out.println(" Request failed: Book ID not found.");
                return;
            }

            if (!b.isAvailable()) {
                System.out.println(" Request failed: Book is already borrowed.");
                return;
            }

            b.setAvailable(false);
            borrowedList.add(b);
            historyStack.push(new Action(Action.Type.BORROW_BOOK, b));
            System.out.println(" Request processed: Book borrowed successfully by " + r.getUserName());

        } else if (r.getType() == Request.Type.RETURN) {
            boolean found = false;

            for (int i = 0; i < borrowedList.size(); i++) {
                Book b = borrowedList.get(i);

                if (b.getBookId().equalsIgnoreCase(bookId)) {
                    found = true;
                    borrowedList.remove(i);

                    if (b.hasWaitlist()) {
                        String nextUser = b.getNextFromWaitlist();
                        borrowedList.add(b);
                        historyStack.push(new Action(Action.Type.BORROW_BOOK, b, nextUser, true));
                        System.out.println(" Request processed: Book returned and auto-assigned to " + nextUser);
                    } else {
                        b.setAvailable(true);
                        historyStack.push(new Action(Action.Type.RETURN_BOOK, b));
                        System.out.println("Request processed: Book returned successfully.");
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println(" Request failed: Book not in borrowed list.");
            }
        }
    }

    //  (BST Features)

    // 11. عرض الكتب مرتبة حسب ID (InOrder Traversal)
    private static void viewInventorySorted() {
        System.out.println("\n--- Inventory (Sorted by ID - BST InOrder) ---");
        if (bookIndex.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        
        bookIndex.inOrder();
        System.out.println("\nTotal books: " + bookIndex.size());
        System.out.println("Tree height: " + bookIndex.height());
    }

    // 12. البحث عن كتاب باستخدام BST
    private static void searchBookByIdBST() {
        System.out.print("\nEnter Book ID to search: ");
        String id = input.nextLine();

        // إنشاء entry مؤقت للبحث
        BookIndexEntry searchEntry = new BookIndexEntry(id, "", null);

        if (bookIndex.contains(searchEntry, idComparator)) {
            System.out.println(" Book found in BST!");
            
            // البحث في inventory للتفاصيل
            Book b = findBook(id);
            if (b != null) {
                System.out.println("\nBook Details:");
                System.out.println("  " + b);
            }
        } else {
            System.out.println(" Book not found.");
        }
    }

    // 13. البحث ضمن نطاق من IDs (Range Search)
    private static void findBooksByRange() {
        System.out.print("\nEnter minimum Book ID: ");
        String minId = input.nextLine();
        
        System.out.print("Enter maximum Book ID: ");
        String maxId = input.nextLine();

        // إنشاء entries للبحث
        BookIndexEntry minEntry = new BookIndexEntry(minId, "", null);
        BookIndexEntry maxEntry = new BookIndexEntry(maxId, "", null);

        MyArrayList<BookIndexEntry> results = bookIndex.rangeSearch(minEntry, maxEntry, idComparator);

        System.out.println("\n--- Books in Range [" + minId + " to " + maxId + "] ---");
        
        if (results.size() == 0) {
            System.out.println("No books found in this range.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println("  " + results.get(i));
            }
            System.out.println("\nTotal found: " + results.size());
        }
    }

    // 14. مستكشف الشجرة (Tree Explorer)
    private static void treeExplorer() {
        if (bookIndex.isEmpty()) {
            System.out.println("Error: No books in library. Add books first!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Tree Explorer ---");
            System.out.println("1. InOrder Traversal (Sorted)");
            System.out.println("2. PreOrder Traversal (Root First)");
            System.out.println("3. PostOrder Traversal (Root Last)");
            System.out.println("4. Breadth-First Traversal (Level Order)");
            System.out.println("5. Show Tree Height & Stats");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\nInOrder (Left → Root → Right) - SORTED:");
                        System.out.println("─────────────────────────────────────");
                        bookIndex.inOrder();
                        break;

                    case 2:
                        System.out.println("\nPreOrder (Root → Left → Right):");
                        System.out.println("─────────────────────────────────────");
                        bookIndex.preOrder();
                        break;

                    case 3:
                        System.out.println("\nPostOrder (Left → Right → Root):");
                        System.out.println("─────────────────────────────────────");
                        bookIndex.postOrder();
                        break;

                    case 4:
                        System.out.println("\nBreadth-First (Level by Level):");
                        System.out.println("─────────────────────────────────────");
                        bookIndex.breadthTraverse();
                        break;

                    case 5:
                        System.out.println("\n Tree Height: " + bookIndex.height());
                        System.out.println("   Total Nodes: " + bookIndex.size());
                        break;

                    case 6:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } else {
                input.next();
                System.out.println("Please enter a number.");
                choice = 0;
            }

        } while (choice != 6);
    }

  // Helper method 

    private static Book findBook(String id) {
        for (int i = 0; i < inventory.size(); i++) {
            Book b = inventory.get(i);
            if (b.getBookId().equalsIgnoreCase(id))
                return b;
        }
        return null;
    }

    private static void viewBooks(MyArrayList<Book> list, String listName) {
        System.out.println("\n--- " + listName + " ---");
        if (list.size() == 0) {
            System.out.println("No books.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private static void viewRequests() {
        System.out.println("\n--- Pending Service Requests ---");
        if (serviceQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        for (Request r : serviceQueue) {
            System.out.println(r);
        }
    }
}