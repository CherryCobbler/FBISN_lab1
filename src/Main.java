import algorithm.Algorithm;
import polinom.Polinom;

public class Main {
    public static void main(String[] args) {
        int[] g = new int[] {1, 1, 0, 1};
        int[] e = new int[] {1,1, 1, 1, 1, 1,1,1};
        int[] l = new int[] {1,1, 0, 0, 1};
        Polinom gP = new Polinom(g);
        Polinom eP = new Polinom(e);
        Polinom lP = new Polinom(l);
        int k = 4;
        System.out.println("Input data");
        System.out.println("g(x) = " + gP);
        System.out.println("e(x) = " + eP);
        System.out.println("l(x) = " + lP);
        System.out.println("k = " + k);
        System.out.println("Data in program");
        Algorithm algorithm = new Algorithm(g, e, l, k);
        Polinom aP = new Polinom(algorithm.coder());
        algorithm.decoder(aP);
    }
}
