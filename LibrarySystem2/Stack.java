// واجهة  تحدد العمليات الأساسية (LIFO)
public interface Stack<E> {
    int size();
    boolean isEmpty();
    E top();         
    void push(E element);
    E pop();          // سحب العنصر العلوي
}