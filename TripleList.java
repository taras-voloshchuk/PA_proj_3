import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TripleList <T> implements Iterable <T>, Iterator <T> {

    public TripleList<T> middleElement, nextElement, previousElement;
    private T value;
    private int count = 0;
    public int Count() {        return count;    }
    public T Value() {        return value;    }

    private boolean isMiddleElement = false;

    public void add(T value) {
        count++;

        TripleList<T> current = this;
        if (count == 1) {
            current.value = value;
            return;
        }

        while (current.middleElement != null && current.nextElement != null)
            current = current.nextElement;

        if (current.middleElement == null) {
            current.middleElement = new TripleList<T>(current, true, value);
        } else {
            current.nextElement = new TripleList<T>(current, false, value);
        }

    }

    private TripleList(TripleList parent, boolean isMiddle, T value) {
        this.isMiddleElement = isMiddle;

        if (isMiddle)
            this.middleElement = parent;
        else
            this.previousElement = parent;

        this.value = value;
    }

    public TripleList() {}
    public TripleList(T []array) {
        count = array.length;
        TripleList<T> current = null;
        for (int i = 0; i < array.length; i++) {
            // a[0] a[2] a[4] .. a[2k] a[2k + 2]...
            if (i % 2 == 0) {
                if (current != null) {
                    current.nextElement = new TripleList<T>(current, false, array[i]);
                    current = current.nextElement;
                }
                else {
                    current = this;
                    current.value = array[i];
                }
            }
            // a[1] a[3] a[5]...
            else {
                current.middleElement = new TripleList<T>(current, true, array[i]);
            }
        }
    }
    private TripleList<T> iter;
    @Override
    public Iterator<T> iterator() {
        iter = this;
        return iter;
    }

    @Override
    public boolean hasNext() {
        if (iter.isMiddleElement)
            return iter.middleElement.nextElement != null;
        else
            return iter.middleElement != null;
    }

    @Override
    public T next() {
        T result = iter.value;
        if (iter.isMiddleElement) iter = iter.middleElement.nextElement;
        else
            iter = iter.middleElement;
        return result;
    }
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (T x : this)
            list.add(x);
        return list;
    }
}
