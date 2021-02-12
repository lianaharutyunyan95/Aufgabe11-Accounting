package HW12;

public class Philosopher extends Thread{
    public final Object chopstick;
    final Boolean meFirst;
    private MyState state = MyState.THINKING;
    Philosopher next;

    public Philosopher(Object chopstick, Boolean whofirst) {
        this.chopstick = chopstick;
        meFirst = whofirst;
    }


    public static void main(String[] args) {
        final int count = 10;
        Philosopher[] philosophers = new Philosopher[count];

        philosophers[count-1].next = philosophers[0];
        for(int i=0; i<count-1; i++) {
            philosophers[i].next = philosophers[i+1];
        }
        for(int i=0; i< count; i++){
            philosophers[i] = new Philosopher(i,true);
        }
        for(int i=0; i< count; i++){
            philosophers[i].start();
        }
    }
}
