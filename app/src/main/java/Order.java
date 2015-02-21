/**
 * Created by Alejo on 21/02/2015.
 */
public class Order {

    private int mainDish;
    private int sideDish1;
    private int sideDish2;
    private boolean soup;

    public Order(int mainDish, int sideDish1, int sideDish2, boolean soup) {
        setMainDish(mainDish);
        setSideDish1(sideDish1);
        setSideDish2(sideDish2);
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

    public boolean isSoup() {
        return soup;
    }

    public void setSoup(boolean soup) {
        this.soup = soup;
    }
}
