import java.util.LinkedList;

public class Menu {
    private LinkedList<MenuItem> order = new LinkedList<>();
    
    public Menu() {
        order = null;
    }
    
    public void add(MenuItem item) {
        order.add(item);
    }
    
    public void remove(MenuItem item) {
        order.remove(item);
    }
    
    public float returnTotalCost(){
        float total = 0;
        for(MenuItem item : order){
            total += item.getCost();
        }
        return total;
    }
}
