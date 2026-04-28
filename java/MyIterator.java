import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<T> implements Iterator<T> {

    private MyArrayList<T> list;
    private int currentIndex;

    public MyIterator(MyArrayList<T> list) {
        this.list = list;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements.");
        }

        T value = list.get(currentIndex);
        currentIndex++;
        return value;
    }
}