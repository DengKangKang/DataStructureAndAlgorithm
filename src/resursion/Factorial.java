package resursion;

public class Factorial {


    public static void main(String[] args) {
        Factorial f= new Factorial();
        System.out.println(f.recall(10 ,1));
    }
    //f(n) = f(n-1)*f(n-2)
    private int recall(int n,int sum) {
        if(n==1) return sum;
        System.out.println(n+" "+sum);

        return recall(n-1,sum *= n);
    }
}
