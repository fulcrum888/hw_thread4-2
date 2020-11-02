import java.util.Map;

public class Reader extends Thread{
    Map map;
    int[] array;

    public Reader(Map map, int[] array) {
        this.map = map;
        this.array = array;
    }

    public void run () {
        for (int i : array) {
            map.get(array[i]);
        }
    }
}
