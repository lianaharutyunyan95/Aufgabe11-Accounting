package HW12;

public class ListBubbler implements Runnable {
    List<Integer> integerList;

    public ListBubbler(Thread thread, List<Integer> integerList) {
        this.integerList = integerList;
        run();
    }

    @Override
    public void run() {
        ListEl<Integer> current = integerList.getHead();
        while (current.next != null) {
            int currentVal = current.val;
            int nextVal = current.next.val;
            if (currentVal > nextVal) {
                System.out.println("swapped: " + currentVal + " and" + nextVal);
                swapElements(current, current.next);
            }
            current = current.next;
        }
    }

    void swapElements(ListEl<Integer> el1, ListEl<Integer> el2) {
        ListEl<Integer> el2Next = el2.next;
        el2.next = el1;
        el1.next = el2Next;
    }
}
