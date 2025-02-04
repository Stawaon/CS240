public class DisplayPrimeNum {
    
    public static void main(String[] args) {
        GenericStack<Integer> primeStack = new GenericStack<Integer>();
        int count = 0;
        int number = 2;

        // Populate stack with first 50 prime numbers
        while (count < 50) {
            if (isPrime(number)) {
                primeStack.push(number);
                count++;
            }

            number++;
        }

        // Display the first 50 prime numbers in descending order
        System.out.println("The first 50 prime numbers in descending order: ");
        while (!primeStack.isEmpty()) {
            System.out.print(primeStack.pop() + " ");
        }
    }

    // Method checks if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
