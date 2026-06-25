import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
class VirtualThread{
    static Long CalculateSum(long start,long end){
        long sum=0;
        for(long i=start;i<=end;i++){
            sum+=i;
        }
        return sum;
    }
    public static void main(String[] args) throws Exception{
        long N = 1_000_000_000L;
        long chunk = N / 4;
        long startTime = System.currentTimeMillis();
        
        try(ExecutorService executor=Executors.newVirtualThreadPerTaskExecutor()){
            Future<Long> task1=executor.submit(()->CalculateSum(1, chunk));
            Future<Long> task2=executor.submit(()->CalculateSum(chunk+1, 2*chunk));
            Future<Long> task3=executor.submit(()->CalculateSum(2*chunk+1, 3*chunk));
            Future<Long> task4=executor.submit(()->CalculateSum(3*chunk+1, N));
            long sum=task1.get()+task2.get()+task3.get()+task4.get();
            long endTime = System.currentTimeMillis();
            System.out.println("Sum: " + sum);
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}