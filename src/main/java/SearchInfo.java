public class SearchInfo {
    String id;
    String title;
    int termFrequency;

    @Override
    public String toString() {
        return title+" "+termFrequency;
    }
}
