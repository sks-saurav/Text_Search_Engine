import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        HashSet<Movie> all_movies = Parser.parseXMLFile();
        PreProcessing.generateIndex(all_movies);
        HashMap<String, TreeSet<SearchInfo>> invertedIndex= PreProcessing.calculateInvertedIndexing(all_movies);

        PorterStemmer pt_stem = new PorterStemmer();
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.print("enter a word: ");
            String key = sc.next();

            if(key.equals("q") || key.equals("Q")) break;

            if(PreProcessing.stopword.contains(key))
                System.out.println("this is a stopword");
            else{
                key = pt_stem.stemWord(key);
                if(invertedIndex.containsKey(key)){
                    for(SearchInfo result : invertedIndex.get(key)){
                        System.out.println(result);
                    }
                }else{
                    System.out.println("Word not found!");
                }
            }
        }
    }
}
