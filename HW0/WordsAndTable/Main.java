//403106595

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int tableSize;
    public static ArrayList<Integer> visitedPointsY;
    public static ArrayList<Integer> visitedPointsX;


    public static boolean FindWord(String wordToBeFound, ArrayList<String> tableRows, int currentIndex, int currentCoordinateX, int currentCoordinateY) {
        if (currentIndex == wordToBeFound.length()) {
            return true;
        }

        if (currentIndex == 0) {
            for (int rowNumber = 0; rowNumber < tableSize; rowNumber++) {
                for (int index = 0; index < tableSize; index++) {
                    if (tableRows.get(rowNumber).charAt(index) == wordToBeFound.charAt(currentIndex)) {
                        visitedPointsY.set(currentIndex, rowNumber);
                        visitedPointsX.set(currentIndex, index);
                        boolean isFound = FindWord(wordToBeFound, tableRows, currentIndex + 1, index, rowNumber);
                        if (isFound) return true;
                    }
                }
            }
        } else {
            int[] deltaX = {-1, 0, 1, -1, 1, -1, 0, 1};
            int[] deltaY = {1, 1, 1, 0, 0, -1, -1, -1};

            for (int currentDirection = 0; currentDirection < 8; currentDirection++) {
                int newCoordinateX = currentCoordinateX + deltaX[currentDirection];
                int newCoordinateY = currentCoordinateY + deltaY[currentDirection];

                if (0 <= newCoordinateX &&
                        newCoordinateX < tableSize &&
                        0 <= newCoordinateY &&
                        newCoordinateY < tableSize) {

                    if (tableRows.get(newCoordinateY).charAt(newCoordinateX) == wordToBeFound.charAt(currentIndex)) {
                        boolean isDuplicate = false;
                        for (int visitedPointIndex = 0; visitedPointIndex < currentIndex; visitedPointIndex++) {
                            if (visitedPointsX.get(visitedPointIndex) == newCoordinateX &&
                                    visitedPointsY.get(visitedPointIndex) == newCoordinateY) {
                                isDuplicate = true;
                                break;
                            }
                        }
                        if (isDuplicate) continue;


                        visitedPointsY.set(currentIndex, newCoordinateY);
                        visitedPointsX.set(currentIndex, newCoordinateX);
                        boolean isFound = FindWord(wordToBeFound, tableRows, currentIndex + 1, newCoordinateX, newCoordinateY);
                        if (isFound) return true;
                    }
                }
                visitedPointsY.set(currentIndex, -1);
                visitedPointsX.set(currentIndex, -1);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tableSize = scanner.nextInt();
        scanner.nextLine();  // Fix for skipping newline

        ArrayList<String> tableRows = new ArrayList<>();
        for (int linesRead = 0; linesRead < tableSize; linesRead++) {
            tableRows.add(scanner.nextLine().trim());
        }


        visitedPointsX = new ArrayList<>();
        visitedPointsY = new ArrayList<>();

        //giving initial values to visitedPointsX and visitedPointsY so that we don't get to problem with set method used later
        for (int valuesGiven = 0; valuesGiven < 30; valuesGiven++) {
            visitedPointsY.add(-1);
            visitedPointsX.add(-1);
        }

        int wordCount = scanner.nextInt();
        scanner.nextLine();  // Fix for skipping newline

        ArrayList<Boolean> isWordFound = new ArrayList<>();
        for (int wordsRead = 0; wordsRead < wordCount; wordsRead++) {
            isWordFound.add(FindWord(scanner.nextLine().trim(), tableRows, 0, 0, 0));
        }

        for (boolean found : isWordFound) {
            System.out.println(found ? "FOUND" : "NOT FOUND");
        }
    }
}
