import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;

public class UCS {
    public static int calculateCost(String startWord, String endWord) {
        int cost = 0;
        for (int i = 0; i < startWord.length(); i++) {
            if (startWord.charAt(i) != endWord.charAt(i)) {
                cost++; // Tambahkan cost jika karakter tidak sama
            }
        }

        return cost;
    }

    public static List<String> Ucs(String start, String end, HashSet<String> words) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        queue.add(new Node(start, 0));

        HashSet<String> visited = new HashSet<>();
        visited.add(start);

        HashMap<String, String> cameFrom = new HashMap<>();
        HashMap<String, Integer> costSoFar = new HashMap<>();
        costSoFar.put(start, 0);

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // mengembalikan head queue

            if (current.getWord().equals(end)) {
                // Reconstruct path
                List<String> path = new ArrayList<>();
                while (current != null && current.getWord() != null) {
                    path.add(current.getWord());
                    if (cameFrom.get(current.getWord()) != null) {
                        current = new Node(cameFrom.get(current.getWord()), costSoFar.get(cameFrom.get(current.getWord())));
                    } else {
                        current = null;
                    }
                }
                Collections.reverse(path);
                return path;
            }

            for (String word : words) {
                if (GBFS.isOneLetterDifferentSamePosition(current.getWord(), word) && !visited.contains(word)) {
                    if (costSoFar.containsKey(current.getWord())) {
                        int newCost = costSoFar.get(current.getWord()) + calculateCost(current.getWord(), word);
                        
                        if (!costSoFar.containsKey(word) || newCost < costSoFar.get(word)) {
                            costSoFar.put(word, newCost);
                            queue.add(new Node(word, newCost));
                            cameFrom.put(word, current.getWord());
                            visited.add(word);
                        }
                    }
                }
            }
        }

        return null; // Return null if no path is found
    }
}
