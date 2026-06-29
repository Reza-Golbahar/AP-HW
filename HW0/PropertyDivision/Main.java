//403106595

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int propertyCount = scanner.nextInt();
        int connectionsInfoLinesCount = scanner.nextInt();

        ArrayList<HashSet<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < propertyCount; i++) {
            connections.add(new HashSet<>());
        }

        for (int inputLinesRead = 0; inputLinesRead < connectionsInfoLinesCount; inputLinesRead++) {
            int property1 = scanner.nextInt();
            int property2 = scanner.nextInt();

            connections.get(property1).add(property2);
            connections.get(property2).add(property1);
        }

        ArrayList<Integer> groups = new ArrayList<>(Collections.nCopies(propertyCount, -1));

        groups.set(0, 1);
        for (int neighborOf0 : connections.get(0)) {
            groups.set(neighborOf0, 2);
        }

        for (int propertyNumber = 1; propertyNumber < propertyCount; propertyNumber++) {
            boolean hasNighborFromGroup1 = false;
            for (int neighbor : connections.get(propertyNumber)) {
                if (groups.get(neighbor) == 1) {
                    hasNighborFromGroup1 = true;
                    break;
                }
            }

            if (hasNighborFromGroup1) {
                if (groups.get(propertyNumber) == 1) {
                    System.out.print("No");
                    return;
                }

                groups.set(propertyNumber, 2);
                for (int neighbor : connections.get(propertyNumber)) {
                    if (groups.get(neighbor) == 2) {
                        System.out.print("No");
                        return;
                    }
                    groups.set(neighbor, 1);
                }
            } else {
                if (groups.get(propertyNumber) == 2) {
                    System.out.print("No");
                    return;
                }

                groups.set(propertyNumber, 1);
                for (int neighbor : connections.get(propertyNumber)) {
                    if (groups.get(neighbor) == 1) {
                        System.out.print("No");
                        return;
                    }
                    groups.set(neighbor, 2);
                }
            }
        }

        System.out.print("Yes");
    }
}