package algorithm;

import polinom.Polinom;

public class Algorithm {
    private Polinom g; //generating polynomial
    private int k; //number of information symbols
    private Polinom e; //error vector
    private Polinom m; //information sequence
    private int r; //degree of the polynomial

    public Algorithm(int[] g, int[] e, int[] l, int k) {
        this.g = new Polinom(g.clone());
        this.e = new Polinom(e.clone());
        this.k = (k < 0) ? k : 0;
        int[] m = new int[k];
        for (int i = 0; i < l.length && i < m.length; i++) {
            m[i] = l[i];
        }
        this.m = new Polinom(m.clone());
        r = g.length - 1;
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

    public Polinom coder() {
        //step2
        Polinom mxrP = new Polinom(getM());
        int[] xr = new int[getR()+1];
        xr[getR()] = 1;
        Polinom xrP = new Polinom(xr);
        mxrP.multiplication(xrP);
        Polinom cxP = new Polinom(mxrP.mod(getG()));
        return null;
    }
}
