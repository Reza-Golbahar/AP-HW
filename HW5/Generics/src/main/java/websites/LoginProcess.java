package websites;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LoginProcess {
    private final Website website;
    private final String username;
    private final HashMap<String, String> info;

    public LoginProcess(Website website, String username, HashMap<String, String> info) {
        this.website = website;
        this.username = username;
        this.info = info;
    }

    public boolean performLogin() {
        try {
            String resourcePath = website.getName() + "/Users.json";
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

            if (inputStream == null) {
                System.err.println("Couldn't find Users.json in resources for " + website.getName());
                return false;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                jsonContent.append(line);
            reader.close();

            JSONArray users = new JSONArray(jsonContent.toString());

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (username.equals(user.getString("username"))) {
                    for (Map.Entry<String, String> entry : info.entrySet())
                        if (!user.getString(entry.getKey()).equals(entry.getValue()))
                            return false;
                    website.setLocked();
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't read from Users.json: " + e.getMessage());
            return false;
        }
        return false;
    }

    public Website getWebsite() {
        return website;
    }
}
