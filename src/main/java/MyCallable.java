import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCallable implements Callable<AtomicInteger> {
    private final int number;
    private final AtomicInteger words;
    private final String[] names;

    public MyCallable(int number, AtomicInteger words, String[] names) {
        this.number = number;
        this.words = words;
        this.names = names;
    }

    @Override
    public AtomicInteger call() throws Exception {

        findPrettyNicknames(number, words, names);
        return words;
    }

    public static void findPrettyNicknames (int number, AtomicInteger words, String[] names) {
        for (String name : names) {
            if (name.length() == number) {
                if (rule1(name) || rule2(number, name) || rule3(name)) {
                    words.addAndGet(1);
                }
            }
        }
    }

    public static boolean rule1 (String name){
        return name.contentEquals(new StringBuilder(name).reverse());
    }

    public static boolean rule2 (int number, String name) {
        String first = "a".repeat(number);
        String second = "b".repeat(number);
        String third = "c".repeat(number);

        return name.equals(first) || name.equals(second) || name.equals(third);
    }

    public static boolean rule3 (String name) {
        char [] letters = name.toCharArray();
        boolean answer = false;

        for (int i = 0; i < letters.length-1; i++){
            if (letters[i] < letters[i+1]){
                answer = true;
            } else {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
