import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Node {
    private String word;
    private int cost;

    public Node(String word, int cost) {
        this.word = word;
        this.cost = cost;
    }

    public String getWord() {
        return word;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
