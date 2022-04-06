package algorithm;
//dop b
import polinom.Polinom;

import java.util.Vector;

public class Algorithm {
    private Polinom g; //generating polynomial
    private int k; //number of information symbols
    private Polinom e; //error vector
    private Polinom m; //information sequence
    private int r; //degree of the polynomial
    private int n;//size m/l

    public Algorithm(int[] g, int[] e, int[] l, int k) {
        this.g = new Polinom(g.clone());
        this.k = (k < 0) ? 0 : k;
        this.m = new Polinom(l.clone());
        this.n = l.length;
        r = e.length - 1;
        this.e = new Polinom(e.clone());
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public int getR() {
        return r;
    }

    public Polinom getE() {
        return e;
    }

    public Polinom getG() {
        return g;
    }

    public Polinom getM() {
        return m;
    }

    public void setG(Polinom g) {
        this.g = new Polinom(g);
        r = g.getSize() - 1;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setM(Polinom l) {
        this.m = new Polinom(l);
    }

    public void setE(Polinom e) {
        this.e = new Polinom(e);
    }

    public Polinom coder() {
        System.out.println("m(x) = " + getM());
        //step2
        Polinom mxrP = new Polinom(getM());
        int[] xr = new int[getR()+1];
        xr[getR()] = 1;
        Polinom xrP = new Polinom(xr);
        mxrP.setPolinom(mxrP.multiplication(xrP));
        Polinom cxP = new Polinom(mxrP.mod(getG()));
        System.out.println("c(x) mod(g(x)) = " + cxP);
        //step3
        Polinom axP = new Polinom(mxrP.plus(cxP));
        System.out.println("a(x) = " + axP);
        return axP;
    }

    public boolean decoder(Polinom axP) { //true - have error
        System.out.println("e(x) = " + getE());
        Polinom bxP = new Polinom(axP.plus(getE()));
        System.out.println("b(x) = " + bxP);
        Polinom sxP = new Polinom(bxP.mod(getG()));
        System.out.println("s(x) = " + sxP);
        if(sxP.isZero()) {
            System.out.println("have not error");
            return false;
        }
        else {
            System.out.println("have error");
            return true;
        }
    }

    private Vector<Polinom> generationE(int size, int d) {
        Vector<Polinom> errorVector = new Vector<>();
        Vector<int[]> errorInteger = new Vector<>();
        int countA = (int) Math.pow(2, size);
        int[] temp = new int[size];
        for (int i = 0; i < countA; i++) {
            errorInteger.add(temp.clone());
        }
        int deg2 = countA;
        for (int column = 0; column < size; column++) {
            deg2 = deg2 / 2;
            int count = 0;
            boolean zero = true;
            for (int row = 0; row < countA; row++) {
                if(!zero) errorInteger.get(row)[column] = 1;
                count++;
                if(count == deg2) {
                    zero = !zero;
                    count = 0;
                }
            }
        }
        for (int i = 1; i < countA; i++) {
            Polinom tempPolinom = new Polinom(errorInteger.get(i));
            if(tempPolinom.weight() - 1 <= d) errorVector.add(tempPolinom);
        }
        return errorVector;
    }

    public void dopTask(int d, int l) {
        Vector<Integer> answer = new Vector<>();
        Polinom newPolinom = new Polinom(new int[2]);
        newPolinom.randomMessage(l+getK());
        setM(newPolinom);
        Polinom word = coder();
        Vector<Polinom> vectorError = generationE(word.getSize(), d);
        for (int i = 0; i < vectorError.size(); i++) {
            setE(vectorError.get(i));
            if(!decoder(word)) {
                answer.add(i);
            }
        }
        System.out.println("\nm(x) = " + newPolinom);
        System.out.println("Vector's errors:");
        System.out.println("d = " + d);
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(vectorError.get(answer.get(i)));
        }
    }
}
