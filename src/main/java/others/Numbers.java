package others;

public final class Numbers {
    public static final int gcd(int a, int b) {
        if (a == 0) { return b; }
        if (b == 0) { return a; }
        while (b != 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(gcd(101, 103));
    }
}
