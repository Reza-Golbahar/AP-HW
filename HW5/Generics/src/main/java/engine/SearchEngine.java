package engine;

import java.util.*;

import browsers.*;
import websites.LoginProcess;
import websites.Website;

public class SearchEngine<T> {
    private final List<Browser> browsers;

    public SearchEngine(PersonalInfo personalInfo) {
        browsers = Arrays.asList(
                new GoogleChrome(personalInfo),
                new MozillaFireFox(personalInfo),
                new Opera(personalInfo)
        );
    }

    public T handleSearch(String question, Type type, Format format) {
        QueryHolder<T> stack = new QueryHolder<>();
        // TODO: Extract category using extractCategory(question)
        String category = extractCategory(question);

        for (Browser browser : browsers) {
            // TODO: Check if browser supports the category and push it into the stack
            if (browser.supportCategory(category))
                stack.push((T) browser);
        }

        List<String> unionList = new ArrayList<>();
        List<Number> numericResults = new ArrayList<>();

        // TODO: Pop from stack and handle logic based on whether it's a Browser, Website, or LoginProcess
        while (!stack.isEmpty()) {
            T operation = stack.pop();

            if (operation instanceof Browser<?> browser) {
                QueryHolder<T> websites = (QueryHolder<T>) browser.getRelevantWebsites(category);
                stack.join(websites);
            }

            else if (operation instanceof Website website) {
                T result = null;
                try {
                    result = (T) website.getAnswer(question, mapType(type));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (result instanceof LoginProcess loginProcess)
                    stack.push(result);
                else {
                    if (format.equals(Format.NORMAL))
                        return result;
                    else if (format.equals(Format.UNION)) {
//                        String entry = (String) result;
//                        if (!unionList.contains(entry))
//                            unionList.add(entry);
                        List<String> list = (List<String>) result;
                        for (String item : list) {
                            if (!unionList.contains(item)) {
                                unionList.add(item);
                            }
                        }
                    }
                    else if (format == Format.AVERAGE)
                        numericResults.add((Number) result);
                }
            } else if (operation instanceof LoginProcess loginProcess) {
                if (loginProcess.performLogin())
                    stack.push((T) loginProcess.getWebsite());
            }
        }

        // TODO: Handle final return based on format (UNION, AVERAGE, etc.)
        if (format.equals(Format.AVERAGE)) {
            int sum = 0;
            for (Number number : numericResults) {
                sum = sum + number.intValue();
            }
            return (T) Integer.valueOf(sum / numericResults.size());
        }
        if (format == Format.UNION && !unionList.isEmpty()) {
            return (T) unionList;
        }
        return null;
    }

    private String extractCategory(String question) {
        String str = question.toLowerCase();

        if (str.contains("loan") || str.contains("budget") || str.contains("tax") || str.contains("currency"))
            return "finance";
        if (str.contains("scholarship") || str.contains("programming") || str.contains("homework") || str.contains("lecture"))
            return "education";
        if (str.contains("software") || str.contains("robotics") || str.contains("platforms") || str.contains("installation"))
            return "technology";
        if (str.contains("goals") || str.contains("player") || str.contains("coach") || str.contains("trophy"))
            return "sports";
        if (str.contains("police") || str.contains("rubbery") || str.contains("arrest") || str.contains("gun"))
            return "crime";
        if (str.contains("actor") || str.contains("oscar") || str.contains("scene") || str.contains("imdb"))
            return "cinema";
        if (str.contains("climate") || str.contains("storm") || str.contains("rainfall") || str.contains("broadcast"))
            return "weather";
        if (str.contains("cancer") || str.contains("vaccination") || str.contains("surgery") || str.contains("strain"))
            return "health";
        return "unknown";
    }

    private Class<?> mapType(Type type) {
        return switch (type) {
            case STRING -> String.class;
            case INTEGER -> Integer.class;
            case DOUBLE -> Double.class;
            case LIST_STRING -> List.class;
        };
    }

}
