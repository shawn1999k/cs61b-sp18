public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int n = 0; 
        while (x < 10) {
            n += x;
            System.out.print(n + " ");
            x = x + 1;
        }
    }
}