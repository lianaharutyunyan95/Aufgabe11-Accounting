package HW12;

import java.util.Random;

public class ListInserter implements Runnable {
    List<Integer> integerList;
    Random random = new Random();

    public ListInserter(Thread thread, List<Integer> integerList) {
        this.integerList = integerList;
        run();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            int i = random.nextInt(10);
            System.out.println("inserted: " + i);
            integerList.setHead(new ListEl<>(i, null));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
