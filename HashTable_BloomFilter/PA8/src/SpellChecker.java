/*
 * Name: TODO
 * PID: TODO
 */

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Spell checks words.
 *
 * @author Kevin Wu
 */

public class SpellChecker {

    public KeyedSet dictWords;

    /**
     * Reads a dictionary.
     * @param reader Reader object.
     * @param useHashTable True or false if HashTable is wanted.
     *
     */
    public void readDictionary(Reader reader, boolean useHashTable) {
        if (useHashTable) {
            dictWords = new MyHashTable();
        } else {
            dictWords = new MyBloomFilter();
        }

        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            dictWords.insert(word.toLowerCase());
        }

        scanner.close();
    }

    /**
     * Checks for a wrong letter in a word
     * @param word String to be checked.
     * @return Linked list of the matches found.
     *
     */
    private LinkedList<String> checkWrongLetter(String word) {
        LinkedList<String> matches = new LinkedList<>();
        word = word.toLowerCase();

        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j != originalChar) {
                    chars[i] = j;
                    String newWord = new String(chars);
                    if (dictWords.lookup(newWord)) {
                        matches.add(newWord);
                    }
                }
            }
            chars[i] = originalChar;
        }
        return matches;
    }

    /**
     * Inserts letter to see if a word is founnd.
     * @param word String to be checked.
     * @return Linked list of the matches found.
     *
     */
    private LinkedList<String> checkInsertedLetter(String word) {
        LinkedList<String> matches = new LinkedList<>();
        word = word.toLowerCase();

        for (int i = 0; i <= word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            for (char j = 'a'; j <= 'z'; j++) {
                sb.insert(i, j);
                String newWord = sb.toString();
                if (dictWords.lookup(newWord)) {
                    matches.add(newWord);
                }
                sb.deleteCharAt(i);
            }
        }
        return matches;
    }

    /**
     * Deletes letter to see if a word is found.
     * @param word String to be checked.
     * @return Linked list of the matches found.
     *
     */
    private LinkedList<String> checkDeleted(String word) {
        LinkedList<String> matches = new LinkedList<>();
        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            String wordCopy = word.substring(0, i) + word.substring(i + 1);
            if (dictWords.lookup(wordCopy)) {
                matches.add(wordCopy);
            }
        }
        return matches;
    }

    /**
     * Swaps letter and cehcks if a word is formed.
     * @param word String to be checked.
     * @return Linked list of the matches found.
     *
     */
    private LinkedList<String> checkTransposedLetter(String word) {
        LinkedList<String> matches = new LinkedList<>();
        word = word.toLowerCase();
        for (int i = 0; i < word.length() - 1; i++) {
            StringBuilder wordCopy = new StringBuilder(word);

            char tempChar = word.charAt(i);
            wordCopy.setCharAt(i, word.charAt(i + 1));
            wordCopy.setCharAt(i + 1, tempChar);

            if (dictWords.lookup(wordCopy.toString())) {
                matches.add(wordCopy.toString());
            }
        }
        return matches;
    }

    /**
     * Inserts spaces in between letters and checks if a word is formed.
     * @param word String to be checked.
     * @return Linked list of the matches found.
     *
     */
    private LinkedList<String> checkInsertSpace(String word) {
        LinkedList<String> matches = new LinkedList<>();

        for (int i = 1; i < word.length(); i++) {

            String firstWord = word.substring(0, i);
            String secondWord = word.substring(i);

            if (dictWords.lookup(firstWord) && dictWords.lookup(secondWord)) {
                matches.add(firstWord + " " + secondWord);
            }
        }

        return matches;
    }

    /**
     * Checks the words with all the spell checks and returns an array of all the matches.
     * @param word String to be checked.
     * @return Linked list of the matches found.
     *
     */
    private String[] checkWord(String word) {
        MyHashTable uniqueMatches = new MyHashTable();

        for (String match : checkWrongLetter(word)) {
            uniqueMatches.insert(match);
        }
        for (String match : checkInsertedLetter(word)) {
            uniqueMatches.insert(match);
        }
        for (String match : checkDeleted(word)) {
            uniqueMatches.insert(match);
        }
        for (String match : checkTransposedLetter(word)) {
            uniqueMatches.insert(match);
        }
        for (String match : checkInsertSpace(word)) {
            uniqueMatches.insert(match);
        }

        return uniqueMatches.returnAll();
    }

    /**
     * Main method to test.
     *
     * @param args // args[0]: 0 if we should use a MyHashTable and 1 for a MyBloomFilter
     *             // args[1]: path to dict file
     *             // args[2]: path to inpu t file
     *
     *
     */
    public static void main(String[] args) {
        // args[0]: 0 if we should use a MyHashTable and 1 for a MyBloomFilter
        // args[1]: path to dict file
        // args[2]: path to input file

        SpellChecker checker = new SpellChecker();

        File dictionary = new File(args[1]);
        try {
            Reader reader = new FileReader(dictionary);

            //TODO - call readDictionary with the given reader on the correct data structure.

            if (args[0].equals("0")) {
                checker.readDictionary(reader, true);
            } else {
                checker.readDictionary(reader, false);
            }


        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + dictionary);
            System.exit(1);
        }

        File inputFile = new File(args[2]);
        try {
            Scanner input = new Scanner(inputFile); // Reads list of words

            //TODO - go through each word from Scanner
            while (input.hasNext()) {
                String word = input.nextLine().trim();
                String[] matchList = checker.checkWord(word);

                if (checker.dictWords.lookup(word)) {
                    System.out.println(word.toLowerCase() + ": " + "ok");

                } else if (matchList.length == 0) {
                    System.out.println(word.toLowerCase() + ": " + "not found");

                } else {
                    System.out.println(word.toLowerCase() + ": "
                            + String.join(", ", matchList));
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + inputFile);
            System.exit(1);
        }
    }
}

