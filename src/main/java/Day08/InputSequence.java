package Day08;

import java.util.*;
import java.util.stream.Collectors;

public class InputSequence {
    private final List<String> signalPatters;

    private List<String> outputValues;
    private final Map<String, String> numbers;

    public InputSequence(String inputLine) {
        this.signalPatters = Arrays.stream(inputLine.split("\\s\\|\\s")[0].split(" "))
                .collect(Collectors.toList());
        this.outputValues = Arrays.stream(inputLine.split("\\s\\|\\s")[1].split(" "))
                .collect(Collectors.toList());
        this.numbers = new HashMap<>();
        this.numbers.put("cf", "1");      // 2
        this.numbers.put("acf", "7");     // 3
        this.numbers.put("bcdf", "4");    // 4
        this.numbers.put("acdeg", "2");
        this.numbers.put("acdfg", "3");
        this.numbers.put("abdfg", "5");
        this.numbers.put("abdefg", "6");
        this.numbers.put("abcefg", "0");
        this.numbers.put("abcdfg", "9");
        this.numbers.put("abcdefg", "8"); // 7
    }

    public int solveInputSequence() {
        Map<Character, String> state = new HashMap<>();
        state.put('a', "abcdefg");
        state.put('b', "abcdefg");
        state.put('c', "abcdefg");
        state.put('d', "abcdefg");
        state.put('e', "abcdefg");
        state.put('f', "abcdefg");
        state.put('g', "abcdefg");

        processCombination(state, "cf");
        processCombination(state, "acf");
        processCombination(state, "bcdf");

//       This is the status at this point, showing the possiblities out there
//
//        1   c  f	(2)	a=a				c=cf					f=cf
//
//        7 a c  f	(3)	a=a				c=cf					f=cf
//
//        4  bcd f	(4)	a=a		b=bd	c=cf	d=bd			f=cf
//
//        2 a cde g	(5)	a=a				c=cf	d=bd					g=eg
//        3 a cd fg	(5)	a=a				c=cf	d=bd			f=cf	g=eg
//        5 ab d fg	(5)	a=a		b=bd			d=bd			f=cf	g=eg
//
//        0 abc efg	(6)	a=a		b=bd	c=cf			e=eg	f=cf	g=eg
//        6 ab defg	(6)	a=a		b=bd			d=bd	e=ge	f=cf	g=eg
//        9 abcd fg	(6)	a=a		b=bd	c=cf	d=bd			f=cf	g=eg
//
//        8 abcdefg	(8)	a=a		b=bd	c=cf	d=bd	e=ge	f=cf	g=eg


//      We know that for length 5 we have:
//        2 a cde g
//        3 a cd fg
//        5 ab d fg
//
//      The characters b and e only occur once if we consider only these three series.
//      Of those, b is the only one of the two that is present in 4 bcd f.
//      So this allows us to figure out which one is b (and d) and which one is e (and g)
//
        List<String> charactersLenght5 = Arrays.stream(signalPatters.stream()
                .filter(pattern -> pattern.length() == 5)
                .reduce("", (a, b) -> a + b)
                .split(""))
                .collect(Collectors.toList());
        String charactersLenght4 = signalPatters.stream()
                .filter(pattern -> pattern.length() == 4)
                .findFirst()
                .orElse("");
        charactersLenght5.sort(String::compareTo);

        String charE = "";
        String charB = "";

        for (String character: charactersLenght5) {
            long occurances = charactersLenght5.stream()
                    .filter(c -> c.equals(character))
                    .count();
            if (occurances == 1L) {
                if (charactersLenght4.contains(character)) {
                    charB = character;
                } else {
                    charE = character;
                }
            }
        }

        String charBPair = state.get('b').replace(charB, "");
        state.put('b', charB);
        state.put('d', charBPair);

        String charEPair = state.get('g').replace(charE, "");
        state.put('e', charE);
        state.put('g', charEPair);

//       This is the status at this point, showing the possiblities out there
//
//        1   c  f	(2)	a=a				c=cf					f=cf
//
//        7 a c  f	(3)	a=a				c=cf					f=cf
//
//        4  bcd f	(4)	a=a		b=b		c=cf	d=d				f=cf
//
//        2 a cde g	(5)	a=a				c=cf	d=d						g=g
//        3 a cd fg	(5)	a=a				c=cf	d=d				f=cf	g=g
//        5 ab d fg	(5)	a=a		b=b				d=d				f=cf	g=g
//
//        0 abc efg	(6)	a=a		b=b		c=cf			e=e		f=cf	g=g
//        6 ab defg	(6)	a=a		b=b				d=d		e=e		f=cf	g=g
//        9 abcd fg	(6)	a=a		b=b		c=cf	d=d				f=cf	g=g
//
//        8 abcdefg	(8)	a=a		b=b		c=cf	d=d		e=e		f=cf	g=g
//
//       Looking at the ones with lenght 6, only f is in there three times, not c

        List<String> charactersLenght6 = Arrays.stream(signalPatters.stream()
                .filter(pattern -> pattern.length() == 6)
                .reduce("", (a, b) -> a + b)
                .split(""))
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        String charF = "";

        for (String character: charactersLenght6) {
            System.out.println(character);
            long occurances = charactersLenght6.stream()
                    .filter(c -> c.equals(character))
                    .count();
//            if (occurances == 3L) {

///////////////////////////


//                if (isCorrectKey) {
//                    charF = character;
//                }
//            }
        }

        String charFPair = state.get('f').replace(charF, "");
        state.put('f', charF);
        state.put('c', charFPair);

        String number = outputValues.stream()
                .map(line -> {
                    String mappedLine = Arrays.stream(line.split(""))
                            .map(c -> state.keySet().stream()
                                    .filter(key -> state.get(key).equals(c))
                                    .findFirst()
                                    .orElse('0'))
                            .sorted(Character::compareTo)
                            .map(c -> c + "")
                            .collect(Collectors.joining());
                    System.out.println(line);
                    System.out.println(mappedLine);
                    return mappedLine;
                })
                .map(numbers::get)
                .reduce("", (a, b) -> a + b);
        return Integer.parseInt(number);
    }

    private void processCombination(Map<Character, String> state, String combination) {
        int lenght = combination.length();
        String toProcess = signalPatters.stream().filter(pattern -> pattern.length() == lenght).findFirst().orElse(null);
        if (toProcess != null) {
            for (int i = 0; i < lenght; i++) {
                state.put(combination.charAt(i), mergeStrings(state.get(combination.charAt(i)), toProcess));
                int finalI = i;
                state.keySet()
                        .forEach(key -> {
                            if (combination.indexOf(key) == -1) {
                                state.put(key, state.get(key).replace(toProcess.charAt(finalI) + "", ""));
                            }
                        });
            }
        }
    }

    private String mergeStrings(String one, String two) {
        return Arrays.stream(one.split(""))
                .filter(two::contains)
                .reduce((a, b) -> a + b)
                .orElse("");
    }

    public List<String> getOutputValues() {
        return outputValues;
    }

}
