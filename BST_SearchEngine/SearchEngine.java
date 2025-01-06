/*
 * Name: Kevin Wu
 * PID:  A17886439
 */

import javax.management.Query;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;

/**
 * Search Engine implementation.
 * 
 * @author Kevin Wu
 * @since  5/16/2024
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     *
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @return false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                for (String actor : cast) {
                    String lowerActor = actor.toLowerCase();
                    if (!movieTree.findKey(lowerActor)) {
                        movieTree.insert(lowerActor);
                    }
                    if (!ratingTree.findKey(lowerActor)) {
                        ratingTree.insert(lowerActor);
                    }
                    addUniqueData(movieTree, lowerActor, movie);
                    addUniqueData(ratingTree, lowerActor, rating);
                }

                for (String studio : studios) {
                    String lowerStudio = studio.toLowerCase();
                    if (!studioTree.findKey(lowerStudio)) {
                        studioTree.insert(lowerStudio);
                    }
                    addUniqueData(studioTree, lowerStudio, movie);
                }

                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }
    /**
     * Helper to insert elements in the BST
     *
     * @param key  Key of the tree
     * @param tree BST
     * @param data Data to be inserted
     *
     */
    private static void addUniqueData(BSTree<String> tree, String key, String data) {
        LinkedList<String> dataList = tree.findDataList(key);
        if (!dataList.contains(data)) {
            tree.insertData(key, data);
        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {


        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> documents = new LinkedList<>();

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful

        boolean firstKey = true;
        for (String key : keys) {
            if (searchTree.findKey(key)) {
                if (firstKey) {
                    documents.addAll(searchTree.findDataList(key));
                    firstKey = false;
                } else {
                    documents.retainAll(searchTree.findDataList(key));
                }
            } else {
                documents.clear();
                break;
            }
        }

        print(query, documents);

        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        if (keys.length > 1) {
            for (String key : keys) {
                if (searchTree.findKey(key)) {
                    LinkedList<String> tempDocuments =
                        new LinkedList<>(searchTree.findDataList(key));

                    tempDocuments.removeAll(documents);

                    if (tempDocuments.isEmpty()) {
                        continue;
                    }
                    print(key, tempDocuments);
                    documents.addAll(tempDocuments);
                } else {
                    print(key, null);
                }
            }
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {


        // initialize search trees
        BSTree<String> movie = new BSTree();
        BSTree<String> studio = new BSTree();
        BSTree<String> rating = new BSTree();
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        // populate search trees
        String query = "";
        for (int i = 2; i < args.length; i++) {
            query += " " + args[i];
        }
        populateSearchTrees(movie, studio, rating, args[0]);
        if (args[1].equals("0")) {
            searchMyQuery(movie, query.trim());
        }
        if (args[1].equals("1")) {
            searchMyQuery(studio, query.trim());
        }
        if (args[1].equals("2")) {
            searchMyQuery(rating, query.trim());
        }
    }
}
