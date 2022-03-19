package polinom;

public class Polinom {
    private int size;
    private int[] polinom;
    private int weight;

    public Polinom(int[] polinom) {
        size = polinom.length;
        if(size != 0) {
            this.polinom = new int[this.size];
            for (int i = 0; i < getSize(); i++) {
                this.polinom[i] = polinom[i];
            }
        }
        this.weight = 0;
        for(int i = 0; i < getSize(); i++) {
            if(polinom[i] == 1) this.weight++;
        }
    }

    public int weight () {
        int w = 0;
        for(int i = 0; i < getSize(); i++) {
            if(getPolinom()[i] == 1) w++;
        }
        return w;
    }

    public Polinom(Polinom polinom) {
        this.polinom = polinom.getPolinom().clone();
        this.size = polinom.getSize();
    }

    public int[] getPolinom() {
        return polinom;
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }

    public boolean setPolinom(Polinom polinom) {
        if(polinom.getSize() != 0) {
            this.polinom = polinom.getPolinom().clone();
            this.size = polinom.getSize();
            return true;
        }
        return false;
    }

    public boolean isZero() {
        for(int i = 0; i < getSize(); i++) {
            if(getPolinom()[i] == 1) return false;
        }
        return true;
    }

    public Polinom plus(Polinom polinom2) {
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
        Polinom result = new Polinom(resultPolinom.clone());
        return result;
    }

    public Polinom multiplication(Polinom polinom2) {
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
        Polinom result = new Polinom(resultPolinom.clone());
        return result;
    }

    public int deg() {
        for (int i = getSize() - 1; i >= 0; i--) {
            if(getPolinom()[i] == 1) return i;
        }
        return 0;
    }

    public Polinom mod(Polinom gP) {
        Polinom dividendP = new Polinom(this);
        while (dividendP.deg() >= gP.deg()) {
            int degree = dividendP.deg() - gP.deg();
            int[] quotient = new int[degree + 1];
            quotient[degree] = 1;
            Polinom quotientP = new Polinom(quotient);
            Polinom auxiliaryP = gP.multiplication(quotientP);
            dividendP.setPolinom(dividendP.plus(auxiliaryP));
        }
        return dividendP;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(isZero()) stringBuilder.append(0);
        else {
            for (int i = getPolinom().length-1; i >= 0 ; i--) {
                if(getPolinom()[i] == 1) {
                    if(i == 0) stringBuilder.append("1");
                    else stringBuilder.append("x^" + i + "+");
                }
            }
        }
        return stringBuilder.toString();
    }
}

