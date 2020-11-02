import java.util.Map;

public class Writer extends Thread {
    Map map;
    int[] array;
    int startIndex;
    int endIndex;

    public Writer(Map map, int[] array, int startIndex, int endIndex) {
        this.map = map;
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            map.putIfAbsent(array[i], array[i]);
        }
    }
}
