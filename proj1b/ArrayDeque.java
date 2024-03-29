public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    /**
     * Creates a deep copy of other.
     */
    private ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        size = other.size;
        System.arraycopy(items, 0, other, 0, size);
    }

    private void changeADQ(int length) {
        T[] newitems = (T[]) new Object[length];
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, newitems, 1, nextLast - nextFirst - 1);
            nextFirst = 0;
            nextLast = size + 1;
            items = newitems;
            newitems = null;
        } else {
            System.arraycopy(items, nextFirst + 1, newitems, 1, items.length - 1 - nextFirst);
            System.arraycopy(items, 0, newitems, items.length - nextFirst, nextFirst);
            nextFirst = 0;
            nextLast = size + 1;
            items = newitems;
            newitems = null;
        }
    }

    @Override
    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        if (size == items.length - 2) {
            changeADQ(size * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
        size = size + 1;
    }

    @Override
    /**
     * Adds an item of type T to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        if (size == items.length - 2) {
            changeADQ(size * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast = nextLast + 1;
        }
        size = size + 1;
    }

    @Override
    /**
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * Prints the items in the deque from first to last,
     * separated by a space. Once all the items
     * have been printed, print out a new line.
     */
    public void printDeque() {
        changeADQ(items.length);
        for (int i = 1; i < nextLast; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    /**
     * Removes and returns the item at the front
     * of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size * 3 <= items.length && items.length >= 16) {
            changeADQ(items.length / 2);
        }
        size = size - 1;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst = nextFirst + 1;
        }
        T item = items[nextFirst];
        items[nextFirst] = null;
        return item;
    }

    @Override
    /**
     * Removes and returns the item at the back
     * of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size * 3 <= items.length && items.length >= 16) {
            changeADQ(items.length / 2);
        }
        size = size - 1;
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast = nextLast - 1;
        }
        T item = items[nextLast];
        items[nextLast] = null;
        return item;
    }

    @Override
    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index
     */
    public T get(int index) {
        changeADQ(items.length);
        return items[index + 1];
    }

    private static void main(String[] args) {
        ArrayDeque a = new ArrayDeque();
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(1);
        a.addFirst(0);
        System.out.println(a.get(0));
    }
}

