public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }

    /**
     * Creates a deep copy of other.
     */
    private ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        size = other.size;
        System.arraycopy(items,0, other, 0, size);
    }

    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        if (size == 0) {
            items = (T[]) new Object[8];
            nextFirst = 0;
            nextLast = 7;
        }
        if (size == items.length && items.length >= 16) {
            T[] newItems = (T[]) new Object[items.length * 2];
            System.arraycopy(newItems, 0, items, 0, nextFirst);
            System.arraycopy(newItems, newItems.length - (items.length - nextLast + 1 ),
                               items, nextLast + 1, items.length - nextLast + 1 );
            items = newItems;
            newItems = null;
            nextLast = newItems.length - (items.length - nextLast + 1 ) - 1;
        }
        items[nextFirst] = item;
        nextFirst = nextFirst + 1;
        size = size + 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        if (size == 0) {
            items = (T[]) new Object[8];
            nextFirst = 0;
            nextLast = 7;
        }
        if (size == items.length && items.length >= 16) {
            T[] newItems = (T[]) new Object[items.length * 2];
            System.arraycopy(newItems, 0, items, 0, nextFirst);
            System.arraycopy(newItems, newItems.length - (items.length - nextLast + 1 ),
                    items, nextLast + 1, items.length - nextLast + 1 );
            items = newItems;
            newItems = null;
            nextLast = newItems.length - (items.length - nextLast + 1 ) - 1;
        }
        items[nextLast] = item;
        nextLast = nextLast - 1;
        size = size + 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last,
     * separated by a space. Once all the items
     * have been printed, print out a new line.
     */
    public void printDeque() {
        for (int i = nextFirst - 1; i > -1; i--) {
            System.out.print(items[i] + " ");
        }
        for (int i = items.length - 1; i > nextLast; i--) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front
     * of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size * 3 <= items.length) {
            T[] newItems = (T[]) new Object[size / 2];
            System.arraycopy(newItems, 0, items, 0, nextFirst);
            System.arraycopy(newItems, newItems.length - (items.length - nextLast + 1 ),
                    items, nextLast + 1, items.length - nextLast + 1 );
            items = newItems;
            newItems = null;
        }
        if (nextFirst == 0) {
            T item = items[items.length - 1];
            items[items.length - 1] = null;
            size = size - 1;
            return item;
        }
        size = size - 1;
        T item = items[nextFirst - 1];
        items[nextFirst - 1] = null;
        nextFirst = nextFirst - 1;
        return item;
    }

    /**
     * Removes and returns the item at the back
     * of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size * 3 <= items.length) {
            T[] newItems = (T[]) new Object[size / 2];
            System.arraycopy(newItems, 0, items, 0, nextFirst);
            System.arraycopy(newItems, newItems.length - (items.length - nextLast + 1 ),
                    items, nextLast + 1, items.length - nextLast + 1 );
            items = newItems;
            newItems = null;
        }
        if (nextLast == items.length - 1) {
            T item = items[nextFirst - 1];
            items[nextFirst - 1] = null;
            size = size - 1;
            nextFirst = nextFirst - 1;
            return item;
        }
        size = size - 1;
        T item = items[nextLast + 1];
        items[nextLast + 1] = null;
        nextLast = nextLast + 1;
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index
     */
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        if (index + 1 < nextFirst) {
            return items[nextFirst - (index + 1)];
        }
        return items[items.length - ((index + 1) - nextFirst)];
    }

    private static void main(String[] args) {
        ArrayDeque a = new ArrayDeque();
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(3);
        a.printDeque();
        System.out.println(a.get(0));
    }
}

