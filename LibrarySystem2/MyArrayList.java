import java.util.Arrays;
import java.util.Iterator;

// نسخة مبسطة من ArrayList مع دعم Iterator
public class MyArrayList<E> implements Iterable<E> {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[10]; // حجم ابتدائي
        size = 0;
    }

    public void add(E data) {
        if (size == elements.length) {
            // مضاعفة الحجم عند الامتلاء
            elements = Arrays.copyOf(elements, size * 2);
        }
        elements[size++] = data;
    }

    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) elements[index];
    }

    public int size() { 
        return size; 
    }

    // ✅ إضافة Iterator لدعم for-each loop
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }
            
            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) elements[currentIndex++];
            }
        };
    }
}