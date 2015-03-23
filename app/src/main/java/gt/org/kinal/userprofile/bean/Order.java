package gt.org.kinal.userprofile.bean;

/**
 * Created by Alejo on 21/02/2015.
 */
public class Order {

    private final int idOrder;
    private int mainDish;
    private int sideDish1;
    private int sideDish2;
    private boolean soup;
    private int dessert;

    public Order (int idOrder, int mainDish, int sideDish1, int sideDish2) {
        this(idOrder, mainDish, sideDish1, sideDish2, false, 0);
    }
    public Order(int idOrder, int mainDish, int sideDish1, int sideDish2, boolean soup, int dessert) {
        this.idOrder = idOrder;
        setMainDish(mainDish);
        setSideDish1(sideDish1);
        setSideDish2(sideDish2);
        setSoup(soup);
        setDessert(dessert);
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getMainDish() {
        return mainDish;
    }

    public void setMainDish(int mainDish) {
        this.mainDish = mainDish;
    }

    public int getSideDish1() {
        return sideDish1;
    }

    public void setSideDish1(int sideDish1) {
        this.sideDish1 = sideDish1;
    }

    public int getSideDish2() {
        return sideDish2;
    }

    public void setSideDish2(int sideDish2) {
        this.sideDish2 = sideDish2;
    }

    public boolean hasSoup() {
        return soup;
    }

    public void setSoup(boolean soup) {
        this.soup = soup;
    }

    public int getDessert() {
        return dessert;
    }

    public void setDessert(int dessert) {
        this.dessert = dessert;
    }
}
