class SingleThread{
    public static void main(String[] args) {
        long N=1000000000L;
        long start=System.nanoTime();
        long sum=0;
        for(long i=1;i<=N;i++){
            sum+=i;
        }
        long end=System.nanoTime();
        System.out.println("Sum: "+sum);
        System.out.println("Time taken: "+(end-start)+" ns");
    }
}