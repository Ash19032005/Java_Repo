class SumTask extends Thread {

    private long start;
    private long end;
    private long sum = 0;

    public SumTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getSum() {
        return sum;
    }

    @Override
    public void run() {
        for (long i = start; i <= end; i++) {
            sum += i;
        }
    }
}

public class ExtendThread {

    public static void main(String[] args)
            throws InterruptedException {

        long N = 1_000_000_000L;
        long chunk = N / 4;

        long startTime = System.nanoTime();

        SumTask t1 = new SumTask(1, chunk);
        SumTask t2 = new SumTask(chunk + 1, 2 * chunk);
        SumTask t3 = new SumTask(2 * chunk + 1, 3 * chunk);
        SumTask t4 = new SumTask(3 * chunk + 1, N);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        long total =
                t1.getSum()
                        + t2.getSum()
                        + t3.getSum()
                        + t4.getSum();

        long endTime = System.nanoTime();

        System.out.println("Sum = " + total);
        System.out.println("Time = "
                + (endTime - startTime) / 1_000_000
                + " ns");
    }
}