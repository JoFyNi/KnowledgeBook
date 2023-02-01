package main.components;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    TrieNode root;
    Node trieNode;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(List<WIKI.Node> nodes) {
        TrieNode current = root;
        for (WIKI.Node node : nodes) {
            for (int i = 0; i < TrieNode.getWord().length(); i++) {
                char c = TrieNode.getWord().charAt(i);
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
            current.node = node;
        }
        current.isEndOfWord = true;
    }

    public List<String> search(String prefix) {
        TrieNode current = root;
        List<String> words = new ArrayList<>();

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(Character.isLowerCase(c)) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    return words;
                }
                current = current.children[index];
            }
        }

        getWords(current, prefix, words);
        return words;
    }

    private void getWords(TrieNode current, String prefix, List<String> words) {
        if (current.isEndOfWord) {
            words.add(prefix);
        }
        for (int i = 0; i < 26; i++) {
            if (current.children[i] != null) {
                getWords(current.children[i], prefix + (char)('a' + i), words);
            }
        }
    }
}

class TrieNode {
    static String word;
    public WIKI.Node node;
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
    public TrieNode(String word) {
        this.word = word;
    }
    public static void setWord(String word) {
        TrieNode.word = word;
    }
    public static String getWord() {
        return word;
    }
}