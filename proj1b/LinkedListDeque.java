public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /**
     * Creates an empty LinkedListDeque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    private LinkedListDeque(T x) {
        sentinel = new Node(x, null, null);
        sentinel.prev = sentinel;
        sentinel.next = new Node(x, sentinel, sentinel);
        size = 1;
    }

    /**
     * Creates a deep copy of other.
     * @param other
     */
    private LinkedListDeque(LinkedListDeque other) {
        LinkedListDeque llq = new LinkedListDeque();
        for (int i = 0; i < other.size; i++) {
            llq.addLast(other.get(i));
        }
    }

    @Override
    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        size = size + 1;
        if (size == 1) {
            sentinel.prev = sentinel.next;
        }
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    /**
     * Adds an item of type T to the back of the deque.
     * @param item
     */
    public  void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        size = size + 1;
        if (size == 1) {
            sentinel.next = sentinel.prev;
        }
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been
     * printed, print out a new line.
     */
    public void printDeque() {
        Node p = sentinel;
        for (int i = 0; i < size; i++) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    @Override
    /**
     * Removes and returns the item at the front of
     * the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            sentinel.prev = sentinel;
        }
        T fitem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return fitem;
    }

    @Override
    /**
     * Removes and returns the item at the back of
     * the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            sentinel.next = sentinel;
        }
        T litem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return litem;
    }

    @Override
    /**
     *  Gets the item at the given index,
     *  where 0 is the front, 1 is the next item,
     *  and so forth. If no such item exists,
     *  returns null. Must not alter the deque!
     * @param index
     */
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /**
     * Same as get, but uses recursion.
     * @param index
     */
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getR(sentinel.next, index);
    }

    /**
     * Helps to accomplish getRecursive
     */
    private T getR(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getR(p.next, index - 1);
    }

    private static void main(String[] args) {
        LinkedListDeque n1 = new LinkedListDeque();
        n1.addLast(1);
        n1.addFirst(2);
        n1.get(1);

    }
}

