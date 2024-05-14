import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger words3 = new AtomicInteger(0);
    static AtomicInteger words4 = new AtomicInteger(0);
    static AtomicInteger words5 = new AtomicInteger(0);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Future<AtomicInteger> task1 = executor.submit(new MyCallable(3, words3, texts));
        Future<AtomicInteger> task2 = executor.submit(new MyCallable(4, words4, texts));
        Future<AtomicInteger> task3 = executor.submit(new MyCallable(5, words5, texts));
        task1.get();
        task2.get();
        task3.get();

        System.out.println("Красивых слов с длинной 3: " + words3.get() + " шт");
        System.out.println("Красивых слов с длинной 4: " + words4.get() + " шт");
        System.out.println("Красивых слов с длинной 5: " + words5.get() + " шт");

        executor.shutdown();
    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}



