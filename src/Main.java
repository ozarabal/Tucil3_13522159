import java.util.HashSet;
import java.util.Scanner;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        HashSet<String> words = WordLoader.loadWords("words.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start word: ");
        String start = scanner.nextLine();
        System.out.print("Enter end word: ");
        String end = scanner.nextLine();
        
        System.out.println("1. UCS");
        System.out.println("2. GBFS");
        System.out.println("3. A*");
        System.out.print("Pilih Algoritma: ");
        int choice = scanner.nextInt();
        

        long time_start = System.currentTimeMillis();
        if (choice == 1){
            List<String> path = UCS.Ucs(start, end, words);
            if (path != null) {
                System.out.println("Path: " + path);
                System.out.println("Cost: " + (path.size() - 1));
            } else {
                System.out.println("No path found");
            }
        } else if (choice == 2){
            List<String> path = GBFS.Gbfs(start, end, words);
            if (path != null && path.size() != 0){
                System.out.println("Path: " + path);
                System.out.println("Cost: " + (path.size() - 1));
            } else {
                System.out.println("No path found");
            }
        } else if (choice == 3){
            List<String> path = Astar.AStar(start, end, words);
            if (path != null) {
                System.out.println("Path: " + path);
                System.out.println("Cost: " + (path.size() - 1));
            } else {
                System.out.println("No path found");
            }
        } else {
            System.out.println("Invalid choice");
        }     
        long time_end = System.currentTimeMillis();
        System.out.println("Time: " + ((time_end - time_start)) + " ms");
        scanner.close();
    }
}
