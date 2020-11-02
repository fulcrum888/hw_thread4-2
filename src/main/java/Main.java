import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int MAX_VALUE = 1000;
        final int ARRAY_SIZE = 10_000_000;
        final int WRITERS_COUNT = 4;
        final int READERS_COUNT = 4;
        int[] array = generateArray(ARRAY_SIZE, MAX_VALUE);
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Writer[] writers = new Writer[WRITERS_COUNT];
        Reader[] readers = new Reader[READERS_COUNT];

        System.out.println("Старт записи/чтения в ConcurrentHashMap");
        long startTime= System.currentTimeMillis();
        for (int i = 0; i < WRITERS_COUNT; i++) {
            writers[i] = new Writer(concurrentHashMap, array, i * array.length / 4,
                    (i+1) * array.length / 4);
            writers[i].start();
        }
        for (int i = 0; i < READERS_COUNT; i++) {
            readers[i] = new Reader(concurrentHashMap, array, i * array.length / 4,
                    (i+1) * array.length / 4);
            readers[i].start();
        }
        for (Writer writer : writers) {
            writer.join();
        }
        for (Reader reader : readers) {
            reader.join();
        }
        System.out.printf("Запись/чтение %d Элементов заняло %dмс\n",
                ARRAY_SIZE, (System.currentTimeMillis()-startTime));

        System.out.println("Старт записи/чтения в SynchronizedMap");
        startTime= System.currentTimeMillis();
        for (int i = 0; i < WRITERS_COUNT; i++) {
            writers[i] = new Writer(synchronizedMap, array, i * array.length / 4,
                    (i+1) * array.length / 4);
            writers[i].start();
        }
        for (int i = 0; i < READERS_COUNT; i++) {
            readers[i] = new Reader(synchronizedMap, array, i * array.length / 4,
                    (i+1) * array.length / 4);
            readers[i].start();
        }
        for (Writer writer : writers) {
            writer.join();
        }
        for (Reader reader : readers) {
            reader.join();
        }
        System.out.printf("Запись/чтение %d Элементов заняло %dмс\n",
                ARRAY_SIZE, (System.currentTimeMillis()-startTime));

    }

    public static int[] generateArray(int arraySize, int maxValue) {
        Random random = new Random();
        int[] result = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            result[i] = random.nextInt(maxValue);
        }
        return result;
    }
}
