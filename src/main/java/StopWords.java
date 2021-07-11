import java.io.*;
import java.util.HashSet;

public class StopWords {
    HashSet<String> stopword;
    static final String stopword_file_path = "src/main/data/stopword.txt";
    StopWords(){
        stopword = new HashSet<>();
        extractWord();
    }

    public HashSet<String> getstopword(){
        return stopword;
    }

    private void extractWord(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(stopword_file_path)));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        String line;
        try{
            while ((line = br.readLine()) != null){
                String[] words = line.toLowerCase().split(",");
                for(String word : words){
                    stopword.add(word);
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
