//403106595

import java.sql.Array;
import java.util.*;

public class Main {
    public static int totalLogCount = 0;


    public static boolean isNewer(Log toBeAdded, Log inOutput) {
        if (toBeAdded.year > inOutput.year) return true;
        if (toBeAdded.year < inOutput.year) return false;

        if (toBeAdded.month > inOutput.month) return true;
        if (toBeAdded.month < inOutput.month) return false;

        if (toBeAdded.day > inOutput.day) return true;
        if (toBeAdded.day < inOutput.day) return false;

        if (toBeAdded.hour > inOutput.hour) return true;
        if (toBeAdded.hour < inOutput.hour) return false;

        if (toBeAdded.minute > inOutput.minute) return true;
        if (toBeAdded.minute < inOutput.minute)
            return false;

        if (toBeAdded.second > inOutput.second) return true;
        if (toBeAdded.second < inOutput.second) return false;

        return true; //if we are comparing the same thing with itself
        //essential for hour 23:59:59 of the second day in DATE_RANGE
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputLinesCount = scanner.nextInt();
        scanner.nextLine();


        ArrayList<Log> logs = new ArrayList<>();
        for (int linesRead = 0; linesRead < inputLinesCount; linesRead++) {
            totalLogCount++;
            String line = scanner.nextLine();

            Log newLog = new Log();

            newLog.year = Integer.valueOf(line.substring(1, 5));
            newLog.month = Integer.valueOf(line.substring(6, 8));
            newLog.day = Integer.valueOf(line.substring(9, 11));
            newLog.hour = Integer.valueOf(line.substring(12, 14));
            newLog.minute = Integer.valueOf(line.substring(15, 17));
            newLog.second = Integer.valueOf(line.substring(18, 20));

            String type = "";
            int index = 23;
            for (; ; index++) {
                if (line.charAt(index) == ']') {
                    break;
                }
                type += line.charAt(index);
            }

            newLog.type = type;

            String message = "";
            for (int index2 = index + 2; index2 < line.length(); index2++)
                message += line.charAt(index2);

            newLog.message = message;
            newLog.wholeInputMessage = line;
            newLog.dateInputMessage = line.substring(1, 20);


            logs.add(newLog);
        }

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine().trim();

            if (line.startsWith("DATE_RANGE")) {
                Log date1 = new Log();
                Log date2 = new Log();

                date1.year = Integer.parseInt(line.substring(11, 15));
                date1.month = Integer.parseInt(line.substring(16, 18));
                date1.day = Integer.parseInt(line.substring(19, 21));
                date1.hour = 0;
                date1.minute = 0;
                date1.second = 0;

                date2.year = Integer.parseInt(line.substring(22, 26));
                date2.month = Integer.parseInt(line.substring(27, 29));
                date2.day = Integer.parseInt(line.substring(30, 32));
                date2.hour = 23;
                date2.minute = 59;
                date2.second = 59; // Set to end of the day

                ArrayList<Log> output = new ArrayList<>();
                for (Log log : logs) {
                    if (isNewer(log, date1) && isNewer(date2, log)) {
                        output.add(log);
                    }
                }

                for (Log toBePrinted : output) {
                    System.out.println(toBePrinted.wholeInputMessage);
                }
                System.out.println();
            } else if (line.startsWith("LEVEL")) {
                String logLevel = line.substring(6);

                for (int logIndex = 0; logIndex < totalLogCount; logIndex++) {
                    if (logs.get(logIndex).type.equals(logLevel))
                        System.out.println(logs.get(logIndex).wholeInputMessage);
                }
                System.out.println();
            } else if (line.startsWith("ERROR_TIMESTAMPS")) {
                ArrayList<String> output = new ArrayList<>();
                for (int logIndex = 0; logIndex < totalLogCount; logIndex++) {
                    if (logs.get(logIndex).type.equals("ERROR"))
                        output.add(logs.get(logIndex).dateInputMessage);
                }

                if (line.length() > 20 && line.substring(17, 26).equals("--reverse")) {
                    for (int toBePrinted = output.size() - 1; toBePrinted >= 0; toBePrinted--)
                        System.out.println(output.get(toBePrinted));
                } else {
                    for (int toBePrinted = 0; toBePrinted < output.size(); toBePrinted++)
                        System.out.println(output.get(toBePrinted));
                }
                System.out.println();
            } else if (line.startsWith("CONTAINS")) {
                String toBeFound = line.substring(9);

                for (int logIndex = 0; logIndex < totalLogCount; logIndex++) {
                    if (logs.get(logIndex).message.contains(toBeFound))
                        System.out.println(logs.get(logIndex).wholeInputMessage);
                }
                System.out.println();

            } else if (line.startsWith("TOP_K_LEVEL")) {
                int secondWhiteSpaceIndex = line.indexOf(" ", 12);
                int number = Integer.parseInt(line.substring(12, secondWhiteSpaceIndex));
                String logLevel = line.substring(secondWhiteSpaceIndex + 1);

                boolean reverseOrder = line.contains("--reverse");
                if (reverseOrder) {
                    logLevel = logLevel.replace("--reverse", "");
                    logLevel = logLevel.trim();
                }


                ArrayList<Log> filteredLogs = new ArrayList<>();
                for (Log log : logs) {
                    if (log.type.equals(logLevel)) {
                        filteredLogs.add(log);
                    }
                }


                if (reverseOrder) {
                    filteredLogs.sort((log1, log2) -> isNewer(log1, log2) ? 1 : -1);
                } else {
                    filteredLogs.sort((log1, log2) -> isNewer(log1, log2) ? -1 : 1);
                }

                int size = Math.min(number, filteredLogs.size());
                ArrayList<Log> output = new ArrayList<>(filteredLogs.subList(0, size));

                for (Log log : output) {
                    System.out.println(log.wholeInputMessage);

                }

                System.out.println();

            } else if (line.startsWith("COUNT_LEVEL")) {
                String logLevel = line.substring(12);
                int output = 0;

                for (int logIndex = 0; logIndex < totalLogCount; logIndex++) {
                    if (logs.get(logIndex).type.equals(logLevel))
                        output++;
                }
                System.out.println(output);

                System.out.println();

            } else if (line.startsWith("FREQUENCY_ANALYSIS")) {

                HashMap<String, Integer> frequencies = new HashMap<>();
                for (Log log : logs) {

                    String[] words = log.message.toLowerCase().split("\\s+"); //important regex

                    for (String word : words) {
                        frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
                    }
                }

                List<Map.Entry<String, Integer>> sortedFrequencies = new ArrayList<>(frequencies.entrySet());

                // Sort by frequency (descending order)
                sortedFrequencies.sort((a, b) -> {
                    int freqComparison = b.getValue().compareTo(a.getValue()); // Sort by frequency (descending)
                    return (freqComparison != 0) ? freqComparison : a.getKey().toLowerCase().compareTo(b.getKey().toLowerCase()); // Sort by key (ascending) if frequencies are equal
                });


                int number;
                if (line.contains("--top"))
                    number = Math.min(Integer.parseInt(line.substring(25).trim()), sortedFrequencies.size());

                else number = Math.min(5, sortedFrequencies.size());

                for (int printed = 0; printed < number; printed++)
                    System.out.println(sortedFrequencies.get(printed).getKey()
                            + ": " + sortedFrequencies.get(printed).getValue());

                System.out.println();
            }

        }


    }
}

class Log {
    String message;
    String type;
    String wholeInputMessage;
    String dateInputMessage;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;

    public void printAttr() {
        System.out.println(this.year + " " + this.month + " " + this.day + " " + this.hour +
                " " + this.minute + " " + this.second);

        System.out.println("\t" + this.type + "\t" + this.message);

    }
}