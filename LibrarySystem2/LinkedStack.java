public class LinkedStack<E> implements Stack<E> {
    
    private LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() { return list.size(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public void push(E element) {
        list.addFirst(element); // الإضافة في البداية (Top)
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E temp = list.getHead().data;
        list.deleteFirst(); // الحذف من البداية
        return temp;
    }

    @Override
    public E top() {
        if (isEmpty()) return null;
        return list.getHead().data;
    }
}