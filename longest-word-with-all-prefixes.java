import java.util.*;

class SolutionLongestWord {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }
    
    TrieNode root = new TrieNode();
    
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isWord = true;
    }
    
    public String longestWord(String[] words) {
        // Step 1: Insert all words
        for (String w : words) insert(w);
        
        String ans = "";
        for (String w : words) {
            if (isValid(w)) {
                if (w.length() > ans.length() || 
                   (w.length() == ans.length() && w.compareTo(ans) < 0)) {
                    ans = w;
                }
            }
        }
        return ans;
    }
    
    private boolean isValid(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children[c - 'a'];
            if (!node.isWord) return false;
        }
        return true;
    }
}
