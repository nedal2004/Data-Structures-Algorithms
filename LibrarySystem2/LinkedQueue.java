import java.util.Iterator;

public class LinkedQueue<E> implements Queue<E> {
    
    private LinkedList<E> list = new LinkedList<>();

    @Override
    public void enqueue(E element) {
        list.addLast(element); // الإضافة في النهاية
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        E temp = list.getHead().data;
        list.deleteFirst(); // السحب من البداية
        return temp;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return list.getHead().data;
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    // Iterator للمرور على عناصر الطابور (لعرض الطلبات)
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private LinkedList<E>.Node<E> current = list.getHead();
            
            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public E next() {
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}