import java.util.Iterator;

public interface Queue<E> extends Iterable<E> {
    void enqueue(E element);
    E dequeue();
    E first();
    int size();
    boolean isEmpty();
}