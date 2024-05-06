import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GBFS {
    public static int levenshteinDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                int cost = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j - 1] + cost, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }

        return dp[word1.length()][word2.length()];
    }

    public static boolean isOneLetterDifferentSamePosition(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false; // Kata-kata harus memiliki panjang yang sama
        }

        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false; // Kata-kata berbeda lebih dari satu huruf
                }
            }
        }

        return diffCount == 1; // Kata-kata berbeda tepat satu huruf di posisi yang sama
    }

    public static List<String> Gbfs(String start, String end, HashSet<String> words) {
            List<String> path = new ArrayList<>();
            path.add(start);
            boolean found = false;
            boolean stuck = false;
            Node startNode = new Node(start,0);
            Integer min = 999999999;
            Node newStartNode = new Node("",0);
            while(found == false && stuck == false){
                for (String word : words){
                    if (isOneLetterDifferentSamePosition(startNode.getWord(), word) && !path.contains(word)){
                        Integer temp = levenshteinDistance(word, end);
                        if (temp < min){
                            min = temp;
                            newStartNode = new Node(word,0);
                        }
                    }
                }
                if (newStartNode.getWord().equals("")){
                    stuck = true;
                    return new ArrayList<>();
                }else if(newStartNode.getWord().equals(startNode.getWord())) {
                    stuck = true;
                    return new ArrayList<>();
                } else {
                    startNode = newStartNode;
                    path.add(startNode.getWord());
                    if (startNode.getWord().equals(end)){
                        found = true;
                    }
                }
            }
            return path;
        }
}
