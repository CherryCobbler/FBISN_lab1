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

    public Algorithm(int[] g, int[] e, int[] l, int k) {
        this.g = new Polinom(g.clone());
        this.k = (k < 0) ? k : 0;
        this.m = new Polinom(l.clone());
        r = e.length - 1;
        this.e = new Polinom(e.clone());
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

    private Vector<Polinom> generationE(int size) {
        Vector<Polinom> errorVector = new Vector<>();
        int[] temp = new int[size];
        errorVector.add(new Polinom(temp));
        for(int countFix = 1; countFix < size; countFix++) {
            for (int positionFix = 0; positionFix <= (size - countFix); positionFix++) {
                for (int posTemp = positionFix; posTemp < (positionFix + countFix); posTemp++ ) {
                    temp[posTemp] = 1;
                }
                if(countFix == 1) errorVector.add(new Polinom(temp));
                for (int i = positionFix + countFix; i < size; i++) {
                    temp[i] = 1;
                    errorVector.add(new Polinom(temp));
                    temp[i] = 0;
                }
                temp = new int[size];
            }
        }
        for (int i = 0; i < size; i++) {
            temp[i] = 1;
        }
        errorVector.add(new Polinom(temp));
        return errorVector;
    }

    public boolean dopTask() {//???clarify the task

        return true;
    }
}
