package websites;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.PersonalInfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public abstract class Website {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final PersonalInfo personalInfo;
    private boolean isLocked = true;
    protected ArrayList<String> categories;

    public Website(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
        categories = new ArrayList<>();
    }

    public abstract LoginProcess login();

    public List<String> getCategories() {
        return categories;
    }

    protected PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    protected void setLocked() {
        isLocked = false;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public <T> T getAnswer(String question, Class<T> valueType) throws IOException {
        if (isLocked)
            return (T) login();
        return findMatchingValue(question, valueType);
    }

    private <T> T findMatchingValue(String question, Class<T> valueType) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(getName());

        if (resource == null)
            throw new IllegalArgumentException("Folder not found in resources: " + getName());

        File folder = new File(resource.getFile());
        if (!folder.exists() || !folder.isDirectory())
            throw new IllegalArgumentException("Invalid folder path: " + folder.getPath());

        Set<String> questionWords = tokenize(question);

        for (File file : Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".json") && !name.equals("Users.json")))) {
            JsonNode root = mapper.readTree(file);

            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                Set<String> keyWords = tokenize(entry.getKey());

                boolean allMatch = questionWords.containsAll(keyWords);

                if (allMatch) {
                    JsonNode valueNode = entry.getValue();
                    return convertNodeToType(valueNode, valueType);
                }
            }
        }
        return null;
    }

    // Tokenize text: lowercase, remove punctuation, split by whitespace
    private static Set<String> tokenize(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9 ]", " ");
        String[] tokens = cleaned.trim().split("\\s+");
        return new HashSet<>(Arrays.asList(tokens));
    }

    private static <T> T convertNodeToType(JsonNode node, Class<T> valueType) {
        if (valueType == String.class)
            return (T) node.asText();
        else if (valueType == Integer.class)
            return (T) Integer.valueOf(node.asInt());
        else if (valueType == Double.class)
            return (T) Double.valueOf(node.asDouble());
        else if (valueType == List.class) {
            if (node.isArray()) {
                List<String> list = new ArrayList<>();
                for (JsonNode element : node)
                    list.add(element.asText());
                return (T) list;
            } else {
                throw new IllegalArgumentException("Expected a JSON array");
            }
        } else
            throw new IllegalArgumentException("Unsupported return type: " + valueType.getSimpleName());
    }
}
