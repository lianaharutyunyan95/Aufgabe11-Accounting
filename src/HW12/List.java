package HW12;

public class List<T> {
    private volatile ListEl<T> head;

    public ListEl<T> getHead() {
        return head;
    }

    public void setHead(ListEl<T> head) {
        this.head = head;
    }

    public List() {
        head = null;
    }

    public static void main(String[] args) {
        List<Integer> integerList = new List<>();
        Thread[] threads = new Thread[40];
        for (Thread thread : threads) {
            ListInserter inserter = new ListInserter(thread, integerList);
            ListBubbler bubbler = new ListBubbler(thread,integerList);
            bubbler.run();
            inserter.run();
        }
    }
}

class ListEl<T> {
    T val;
    ListEl<T> next;

    ListEl(T v, ListEl<T> n) {
        val = v;
        next = n;
    }
}
