import java.util.Map;

public class Reader extends Thread{
    Map map;
    int[] array;
    int startIndex;
    int endIndex;

    public Reader(Map map, int[] array, int startIndex, int endIndex) {
        this.map = map;
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public void run () {
        for (int i = startIndex; i < endIndex; i++) {
            map.get(array[i]);
        }
    }
}
