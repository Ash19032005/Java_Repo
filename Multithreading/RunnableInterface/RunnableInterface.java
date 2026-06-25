class CalculateSum implements Runnable{
    private long N;
    private long sum=0;
    public CalculateSum(long N){
        this.N=N;
    }
    public long getSum(){
        return sum;
    }
    @Override
    public void run() {
        long sum=0;
        for(long i=1;i<=N;i++){
            sum+=i;
        }
    }
}
class RunnableInterface{
    public static void main(String[] args){
        long N=1000000000L;
        long start=System.nanoTime();
        CalculateSum cs=new CalculateSum(N);
        Thread t=new Thread(cs);
        t.start();
        try{
            t.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        long end=System.nanoTime();
        long sum=cs.getSum();
        System.out.println("Sum: "+sum);
        System.out.println("Time taken: "+(end-start)/1000000+" ms");
    }
}