import javax.management.ValueExp;
import java.util.*;

public class WordFrequencyGame {
    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);

        if (words.length == 1) {
            return inputStr + " 1";
        }

        try {
            List<Input> frequencies = countFrequencies(words);
            frequencies.sort((w1, w2) -> w2.count() - w1.count());
            return composeOutput(frequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private static String composeOutput(List<Input> frequencies) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input word : frequencies) {
            String s = word.value() + " " + word.count();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, Integer> groups = groupSameWords(words);
        List<Input> frequencies = new ArrayList<>();

        for (String key : groups.keySet()) {
            Input input = new Input(key, groups.get(key));
            frequencies.add(input);
        }

        return frequencies;
    }

    private static Map<String, Integer> groupSameWords(String[] words) {
        Map<String, Integer> groups = new HashMap<>();
        for (String word : words) {
            groups.computeIfPresent(word, (key, count) -> ++count);
            groups.computeIfAbsent(word, key -> 1);
        }
        return groups;
    }

}
