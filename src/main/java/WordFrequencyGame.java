import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        if (inputStr.split(ANY_SPACE_SEPARATOR).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(ANY_SPACE_SEPARATOR);

                List<Input> frequencies = countFrequencies(words);

                frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : frequencies) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, List<String>> groups = groupSameWords(words);
        List<Input> frequencies = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            frequencies.add(input);
        }

        return frequencies;
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        List<String> inputList = new ArrayList<>();
        for (String s : words) {
            inputList.add(s);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<String>> groups = new HashMap<>();
        for (String input1 : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!groups.containsKey(input1)) {
                ArrayList arr = new ArrayList<>();
                arr.add(input1);
                groups.put(input1, arr);
            } else {
                groups.get(input1).add(input1);
            }
        }
        return groups;
    }

}
