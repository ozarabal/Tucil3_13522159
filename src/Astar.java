import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Astar {
    public static List<String> AStar(String start, String end, HashSet<String> words) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        queue.add(new Node(start, 0));

        HashSet<String> visited = new HashSet<>();
        visited.add(start);

        HashMap<String, String> cameFrom = new HashMap<>();
        HashMap<String, Integer> costSoFar = new HashMap<>();
        costSoFar.put(start, 0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

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
                    int newCost = costSoFar.get(current.getWord()) + UCS.calculateCost(current.getWord(), word);
                    if (!costSoFar.containsKey(word) || newCost < costSoFar.get(word)) {
                        costSoFar.put(word, newCost);
                        int priority = newCost + GBFS.levenshteinDistance(word, end);
                        queue.add(new Node(word, priority));
                        cameFrom.put(word, current.getWord());
                        visited.add(word);
                    }
                }
            }
        }

        return null; // Return null if no path is found
    }
}
