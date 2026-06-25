import java.util.concurrent.*;
public class ExecutorServiceSum{
     static class Sum implements Callable<Long>{
        private long start;
        private long end;
        Sum(long start, long end){
            this.start=start;
            this.end=end;
        }

        @Override
        public Long call() throws Exception {
            long sum=0;
            for(long i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }

    } 
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long N = 1_000_000_000L;
        long chunk = N / 4;
        int workers=4;
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(workers);
        Future<Long> f1=executor.submit(new Sum(1,chunk));
        Future<Long> f2=executor.submit(new Sum(chunk+1,2*chunk));
        Future<Long> f3=executor.submit(new Sum(2*chunk+1,3*chunk));
        Future<Long> f4=executor.submit(new Sum(3*chunk+1,N));
        long totalSum = f1.get() + f2.get() + f3.get() + f4.get();
        long endTime = System.nanoTime();
        System.out.println("Sum = " + totalSum);
        System.out.println("Time taken = " + (endTime - startTime) / 1_000_000 + " ns");
        executor.shutdown();
    }
}