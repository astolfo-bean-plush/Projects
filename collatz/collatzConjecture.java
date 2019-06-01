import java.util.Scanner;
public class collatzConjecture {
    public static void main(String[] args) {
    int step = 1;
    int n = getCollatz();
    int num = n;
    while (!(n == 1)) {
        if (n % 2 == 0) {
            int orig_n = n;
            n /= 2;
            System.out.printf("Step %,d: %,d / 2 = %,d %n", step, orig_n, n);
        }
        else {
            int orig_n = n;
            n = (n * 3) + 1;
            System.out.printf("Step %,d: (%,d * 3) + 2 = %,d %n", step, orig_n, n);
        }
        step++;
    }
    System.out.printf("The number %,d took %,d steps to reach 1. %n", num, step - 1);
    }

    private static int getCollatz() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an integer N where N > 1: ");
        return input.nextInt();
    }
}
