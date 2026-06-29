package engine;

import java.util.ArrayList;
import java.util.List;

public class QueryHolder<T> {
    private final List<T> elements = new ArrayList<>();

    public void push(T website) {
        // TODO: Add item to the list
        elements.add(website);
    }

    public T pop() {
        return elements.removeLast();
    }

    public void join(QueryHolder<T> queryHolder) {
        for (T element : queryHolder.elements) {
            push(element);
        }
    }

    public boolean isEmpty() {
        // TODO: Return true if the list is empty
        return elements.isEmpty();
    }
}
