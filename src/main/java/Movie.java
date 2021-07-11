import java.util.HashMap;
import java.util.List;

public class Movie {
    String id;
    String title;
    String year;
    List<String> genres;
    List<String> directors;
    List<String> keyWords;
    String plot;
    HashMap<String, int[]> index;
    //0-title, 1-directors, 2-keywords + genres + years, 3-plot

    Movie(String id){
        this.id = id;
        index = new HashMap<>();
    }

    @Override
    public String toString() {
        return  id+" "+title;
    }
    void printindex(){
        for(String str : index.keySet()){
            int[] arr = index.get(str);
            System.out.print(str+"-->");
            for(int i : arr)
                System.out.print(i+" ");
            System.out.println();
        }
    }
}
