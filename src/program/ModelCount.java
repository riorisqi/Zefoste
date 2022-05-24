package program;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author RIO RISQI
 */
public class ModelCount 
{
    
    private SimpleIntegerProperty countA, countB, countC, countD, countE,
                                  countF, countG, countH, countI, countJ,
                                  countK;
    public ModelCount()
    {
        
    }
    
    public ModelCount(int a, int b, int c, int d, int e,
                      int f, int g, int h, int i, int j,
                      int k)
    {
        countA = new SimpleIntegerProperty(a);
        countB = new SimpleIntegerProperty(b);
        countC = new SimpleIntegerProperty(c);
        countD = new SimpleIntegerProperty(d);
        countE = new SimpleIntegerProperty(e);
        countF = new SimpleIntegerProperty(f);
        countG = new SimpleIntegerProperty(g);
        countH = new SimpleIntegerProperty(h);
        countI = new SimpleIntegerProperty(i);
        countJ = new SimpleIntegerProperty(j);
        countK = new SimpleIntegerProperty(k);
    }

    public int getCountA() {
        return countA.get();
    }

    public int getCountB() {
        return countB.get();
    }

    public int getCountC() {
        return countC.get();
    }

    public int getCountD() {
        return countD.get();
    }

    public int getCountE() {
        return countE.get();
    }

    public int getCountF() {
        return countF.get();
    }

    public int getCountG() {
        return countG.get();
    }

    public int getCountH() {
        return countH.get();
    }

    public int getCountI() {
        return countI.get();
    }

    public int getCountJ() {
        return countJ.get();
    }

    public int getCountK() {
        return countK.get();
    }

    public void setCountA(int countA) {
        this.countA.set(countA);
    }

    public void setCountB(int countB) {
        this.countB.set(countB);
    }

    public void setCountC(int countC) {
        this.countC.set(countC);
    }

    public void setCountD(int countD) {
        this.countD.set(countD);
    }

    public void setCountE(int countE) {
        this.countE.set(countE);
    }

    public void setCountF(int countF) {
        this.countF.set(countF);
    }

    public void setCountG(int countG) {
        this.countG.set(countG);
    }

    public void setCountH(int countH) {
        this.countH.set(countH);
    }

    public void setCountI(int countI) {
        this.countI.set(countI);
    }

    public void setCountJ(int countJ) {
        this.countJ.set(countJ);
    }

    public void setCountK(int countK) {
        this.countK.set(countK);
    }
    
}
