import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (T[]) new Object[500];
        size = 0;
    }

    public void add(int index, T element) {
        if (size == data.length) {
            throw new IllegalStateException("ArrayList is full.");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

    public void add(T element) {
        add(size, element);
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        return data[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        T removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;

        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        return containsHelper(element, 0);
    }

    private boolean containsHelper(T element, int index) {
        if (index >= size) {
            return false;
        }

        if (element == null) {
            if (data[index] == null) {
                return true;
            }
        } else if (element.equals(data[index])) {
            return true;
        }

        return containsHelper(element, index + 1);
    }

    public Iterator<T> iterator() {
        return new MyIterator<T>(this);
    }
}