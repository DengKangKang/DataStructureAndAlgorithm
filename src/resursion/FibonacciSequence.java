package resursion;

public class FibonacciSequence {

    public static void main(String[] args) {
        FibonacciSequence fs = new FibonacciSequence();
        fs.recall(3);
    }

    //f(n) = f(n-1)+f(n-2)

    private void recall(int n) {
        if (n == 1) {
            System.out.println(1);
            return;
        }
        if (n == 2) {
            System.out.println(2);
            return;
        }

        System.out.println(n);

        recall(n - 1);
        recall(n - 2);

    }


}
