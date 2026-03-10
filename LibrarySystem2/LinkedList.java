public class LinkedList<E> {

    // Node class: يمثل العقدة الواحدة
    class Node<T> {
        T data;
        Node<T> next;
        
        public Node(T data) { 
            this.data = data; 
        }
        
        // ✅ إضافة getters للوصول الآمن
        public T getData() { 
            return data; 
        }
        
        public Node<T> getNext() { 
            return next; 
        }
    }

    private int size;
    private Node<E> head;
    private Node<E> tail; // نستخدم الـ tail للإضافة السريعة في النهاية

    public LinkedList() {
        this.size = 0;
    }

    public boolean isEmpty() { 
        return head == null; 
    }
    
    public int size() { 
        return size; 
    }
    
    // نحتاجها للوصول للبيانات في الكلاسات الأخرى
    public Node<E> getHead() { 
        return head; 
    }

    // الإضافة في البداية (للمكدس)
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // الإضافة في النهاية (للطابور)
    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    // دالة عامة للإضافة
    public void add(E data) { 
        addLast(data); 
    }

    // الحذف من البداية
    public void deleteFirst() {
        if (isEmpty()) return;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
    }

    // بحث عن عنصر
    public boolean contains(E data) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    // حذف عنصر معين (مهم للـ Undo في الـ Waitlist)
    public boolean remove(E data) {
        if (isEmpty()) return false;
        
        if (head.data.equals(data)) {
            deleteFirst();
            return true;
        }
        
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                if (current.next == null) tail = current; // تحديث الـ tail لو حذفنا الأخير
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}