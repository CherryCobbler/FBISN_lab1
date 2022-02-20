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
        this.k = (k < 0) ? k : 0;
        int[] m = new int[k];
        for (int i = 0; i < l.length && i < m.length; i++) {
            m[i] = l[i];
        }
        this.m = new Polinom(m.clone());
        r = g.length - 1;
        int[] error = new int[k + r];
        for (int i = 0; i < e.length && i < error.length; i++) {
            error[i] = e[i];
        }
        this.e = new Polinom(error.clone());
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
        int[] m = new int[k];
        for (int i = 0; i < l.getSize() && i < m.length; i++) {
            m[i] = l.getPolinom()[i];
        }
        this.m = new Polinom(m.clone());
    }

    public void setE(Polinom e) {
        int[] error = new int[k + r];
        for (int i = 0; i < e.getSize() && i < error.length; i++) {
            error[i] = e.getPolinom()[i];
        }
        this.e = new Polinom(error.clone());
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
        System.out.println("c(x) = " + cxP);
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
}
