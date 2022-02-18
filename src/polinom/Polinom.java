package polinom;

public class Polinom {
    private int size;
    private int[] polinom;

    public Polinom(int[] polinom) {
        size = polinom.length;
        if(size != 0) {
            this.polinom = new int[this.size];
            for (int i = 0; i < getSize(); i++) {
                this.polinom[i] = polinom[i];
            }
        }
    }

    public int[] getPolinom() {
        return polinom;
    }

    public int getSize() {
        return size;
    }

    public boolean setPolinom(int[] polinom) {
        if(polinom.length != 0) {
            this.polinom = new int[polinom.length];
            for (int i = 0; i < polinom.length; i++) {
                this.polinom[i] = polinom[i];
            }
            return true;
        }
        return false;
    }

    public void plus(Polinom polinom2) {
        int sizeNew = (getSize() > polinom2.getSize()) ? getSize() : polinom2.getSize();
        int[] resultPolinom = new int[sizeNew];
        int j = 0;
        int i;
        for (i = 0; i < getSize() && j < polinom2.getSize(); i++, j++) {
            int value = getPolinom()[i] + polinom2.getPolinom()[j];
            if(value == 2) value = 0;
            resultPolinom[i] = value;
        }
        if(getSize() > polinom2.getSize()) {
            for (int k = i; k < getSize(); k++) {
                resultPolinom[k] = getPolinom()[k];
            }
        }
        else if(getSize() < polinom2.getSize()) {
            for (int k = j; k < polinom2.getSize(); k++) {
                resultPolinom[k] = polinom2.getPolinom()[k];
            }
        }
        setPolinom(resultPolinom);
    }

    public void multiplication(Polinom polinom2) {
        int sizeNew = polinom2.getSize() + getSize() - 1;
        int[] resultPolinom = new int[sizeNew];
        for (int i = 0; i < polinom2.getSize(); i++) {
            for (int j = 0; j < getPolinom().length; j++) {
                if(getPolinom()[j] == 1 && polinom2.getPolinom()[i] == 1) {
                    int degree = i+j;
                    resultPolinom[degree]++;
                    if(resultPolinom[degree] == 2) resultPolinom[degree]  = 0;
                }
            }
        }
        setPolinom(resultPolinom);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = getPolinom().length-1; i >= 0 ; i--) {
            if(getPolinom()[i] == 1) {
                if(i == 0) stringBuilder.append("1");
                else stringBuilder.append("x^" + i + "+");
            }
        }
        return stringBuilder.toString();
    }
}

