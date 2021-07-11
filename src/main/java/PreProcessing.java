import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class PreProcessing {
    static HashSet<String> stopword;
    static PorterStemmer pt;
    public static void generateIndex(HashSet<Movie> movies){
        stopword = new StopWords().getstopword();
        pt = new PorterStemmer();
        for(Movie movie : movies){
            helper(movie);
        }
    }

    private static void helper(Movie movie){
        HashMap<String, int[]> indexing = movie.index;
        //title
        insert(movie.title, indexing, 0);
        String[] words = movie.title.split(" ");
        for(String ttl : words)
            insert(ttl, indexing, 0);

        insert(movie.year, indexing, 2);
        //directors
        for(String dir : movie.directors){
            String[] str  = dir.split(",");
            for(String s : str)
                insert(s, indexing, 1);
        }
        //keywords
        for(String str : movie.keyWords)
            insert(str, indexing, 2);

        //genge
        for(String gen : movie.genres)
            insert(gen, indexing, 2);
        //plot
        String[] plot = movie.plot.split(" ");
        for(String plt : plot)
            insert(plt, indexing, 3);

    }

    private static  void insert(String in_word, HashMap<String, int[]> map, int index){
        //case conversion
        String word = in_word.toLowerCase().trim();
        if(stopword.contains(word)) return;
        String st_word = pt.stemWord(word);
        if(map.containsKey(st_word)){
            int[] arr = map.get(st_word);
            arr[index]++;
        } else {
            int[] arr = new int[4];
            arr[index]++;
            map.put(st_word, arr);
        }
    }

    public static HashMap<String, TreeSet<SearchInfo>> calculateInvertedIndexing(HashSet<Movie> all_movies){
        HashMap<String, TreeSet<SearchInfo>> inverted_index = new HashMap<>();
        for(Movie movie : all_movies){
            HashMap<String, int[]> movie_index = movie.index;
            for(String word : movie_index.keySet()){
                int tf = calculateTf(movie_index.get(word));
                TreeSet<SearchInfo> tree = null;
                if(inverted_index.containsKey(word)){
                    tree = inverted_index.get(word);
                } else{
                    tree = new TreeSet<>((a, b) -> {
                       if(a.termFrequency == b.termFrequency) return a.id.compareTo(b.id);
                       else return  b.termFrequency - a.termFrequency;
                    });
                }
                SearchInfo sif = new SearchInfo();
                sif.id = movie.id;
                sif.termFrequency = tf;
                sif.title = movie.title;
                tree.add(sif);
                inverted_index.put(word, tree);
            }
        }
        return inverted_index;
    }

    private static int calculateTf(int[] arr){
        int[] wt = {10, 5, 2, 1};
        int tf = 0;
        for(int i = 0; i < wt.length; i++)
            tf += wt[i]*arr[i];
        return tf;
    }
}
