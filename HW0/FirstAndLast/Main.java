//403106595


import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.min;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        String[] inputWords = inputLine.split("-");

        for (int index = 0; index < inputWords.length; index++) {
            inputWords[index] = inputWords[index].trim();
        }

        ArrayList<Integer> firstUniqueLetter = new ArrayList<>();
        for (int index = 0; index < inputWords.length; index++)
            firstUniqueLetter.add(1);

        ArrayList<Integer> firstUniqueLetterFromBehind = new ArrayList<>();
        for (int index = 0; index < inputWords.length; index++)
            firstUniqueLetterFromBehind.add(1);

        for (int firstWordIndex = 0; firstWordIndex < inputWords.length; firstWordIndex++) {
            for (int secondWordIndex = 0; secondWordIndex < inputWords.length; secondWordIndex++) {
                int maxIndex = min(inputWords[firstWordIndex].length(), inputWords[secondWordIndex].length());

                for (int characterIndex = 0; characterIndex < maxIndex; characterIndex++) {
                    if (inputWords[firstWordIndex].charAt(characterIndex) != inputWords[secondWordIndex].charAt(characterIndex)) {
                        int uniqueLetterIndex = Math.max(characterIndex + 1, firstUniqueLetter.get(firstWordIndex));
                        firstUniqueLetter.set(firstWordIndex, uniqueLetterIndex);
                        break;
                    }

                    if (characterIndex == maxIndex - 1 &&
                            firstWordIndex != secondWordIndex) {

                        if (characterIndex == inputWords[firstWordIndex].length() - 1)
                            firstUniqueLetter.set(firstWordIndex, inputWords[firstWordIndex].length());
                        else firstUniqueLetter.set(firstWordIndex, maxIndex + 1);
                    }
                }


                int maxDelta = min(inputWords[firstWordIndex].length(), inputWords[secondWordIndex].length());

                for (int delta = 0; delta < maxDelta; delta++) {
                    int firstWordCharacterIndex = inputWords[firstWordIndex].length() - delta - 1;
                    int secondWordCharacterIndex = inputWords[secondWordIndex].length() - delta - 1;

                    if (inputWords[firstWordIndex].charAt(firstWordCharacterIndex) != inputWords[secondWordIndex].charAt(secondWordCharacterIndex)) {
                        int uniqueLetterIndex = Math.max(delta + 1, firstUniqueLetterFromBehind.get(firstWordIndex));
                        firstUniqueLetterFromBehind.set(firstWordIndex, uniqueLetterIndex);
                        break;
                    }

                    if (delta == maxDelta - 1 &&
                            firstWordIndex != secondWordIndex) {

                        if (delta == inputWords[firstWordIndex].length() - 1)
                            firstUniqueLetterFromBehind.set(firstWordIndex, inputWords[firstWordIndex].length());
                        else firstUniqueLetterFromBehind.set(firstWordIndex, maxDelta + 1);
                    }
                }
            }
        }


        for (int index = 0; index < inputWords.length; index++)
            System.out.print(firstUniqueLetter.get(index) + " ");

        System.out.println();

        for (int index = 0; index < inputWords.length; index++)
            System.out.print(firstUniqueLetterFromBehind.get(index) + " ");
    }
}