import java.util.Map;

public class Writer extends Thread {
    Map map;
    int[] array;

    public Writer(Map map, int[] array) {
        this.map = map;
        this.array = array;
    }

    public void run() {
        for (int i : array) {
            map.putIfAbsent(array[i], array[i]);
        }
    }
}
