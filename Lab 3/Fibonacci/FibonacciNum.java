public class FibonacciNum {
    public static void main(String[] args) {
        FibonacciIterator fib = new FibonacciIterator(1000000);
        
        while (fib.hasNext()) {
            System.out.println(fib.next());
            
        }
    }
}
