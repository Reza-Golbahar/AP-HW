import engine.Format;
import engine.PersonalInfo;
import engine.SearchEngine;
import engine.Type;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    @Test
    public void testStringNormal() {
        PersonalInfo personalInfo = new PersonalInfo("puns_n_roses", "sweetLaughOMine",
                "puns.roses@de-mail.de", "+49 160 99887766",
                "35", "female", "Germany");
        SearchEngine<String> searchEngine = new SearchEngine<>(personalInfo);
        String result = searchEngine.handleSearch(
                "What is the currency used in Japan?",
                Type.STRING,
                Format.NORMAL);

        String expected = "Yen";
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testListUnion() {
        PersonalInfo personalInfo = new PersonalInfo("queen_b", "beyHive2025",
                "queen.b@tokyo.jp", "+81 90 9876 5432",
                "28", "female", "Japan");
        SearchEngine<List<String>> searchEngine = new SearchEngine<>(personalInfo);
        List<String> result = searchEngine.handleSearch(
                "What are the top programming languages in 2024?",
                Type.LIST_STRING,
                Format.UNION);

        List<String> expected = List.of(new String[]
                {"Python", "JavaScript", "Go", "Rust", "TypeScript", "Kotlin", "Swift"});
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testListNormal() {
        PersonalInfo personalInfo = new PersonalInfo("nate_dog", "snoopFriend",
                "nate.dog@ukmail.co.uk", "+44 7900 123456",
                "37", "male", "UK");
        SearchEngine<List<String>> searchEngine = new SearchEngine<>(personalInfo);
        List<String> result = searchEngine.handleSearch(
                "What are the primary cloud platforms?",
                Type.LIST_STRING,
                Format.NORMAL);

        List<String> expected = List.of(new String[]
                {"AWS", "Oracle Cloud", "Google Cloud"});
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntegerNormal1() {
        PersonalInfo personalInfo = new PersonalInfo("glitch_goblin", "bugHunter777",
                "goblin.glitch@usamail.co.usa", "+1 415 555 9012",
                "24", "female", "USA");
        SearchEngine<Integer> searchEngine = new SearchEngine<>(personalInfo);
        Integer result = searchEngine.handleSearch(
                "How many Mbappe goals were scored in Qatar 2022?",
                Type.INTEGER,
                Format.NORMAL);

        Integer expected = 7;
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntegerNormal2() {
        PersonalInfo personalInfo = new PersonalInfo("tofu_terminator", "illBeVeggie",
                "tofu.terminator@veganmail.ca", "+1 604 555 3210",
                "61", "male", "Canada");
        SearchEngine<Integer> searchEngine = new SearchEngine<>(personalInfo);
        Integer result = searchEngine.handleSearch(
                "What is the percentage of gun-related crimes?",
                Type.INTEGER,
                Format.NORMAL);

        Integer expected = 41;
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntegerNormal3() {
        PersonalInfo personalInfo = new PersonalInfo("charlie_c", "password1",
                "charlie_c@yahoo.com", "+61 417 987 654",
                "60", "male", "Australia");
        SearchEngine<Integer> searchEngine = new SearchEngine<>(personalInfo);
        Integer result = searchEngine.handleSearch(
                "What is the global child vaccination coverage?",
                Type.INTEGER,
                Format.NORMAL);

        Integer expected = 88;
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntegerAverage() {
        PersonalInfo personalInfo = new PersonalInfo("bob_the_builder", "hammerTime!",
                "bob.builder@outlook.com", "+27 82 123 4567",
                "42", "male", "South Africa");
        SearchEngine<Integer> searchEngine = new SearchEngine<>(personalInfo);
        Integer result = searchEngine.handleSearch(
                "What is the average annual rainfall in London?",
                Type.INTEGER,
                Format.AVERAGE);

        Integer expected = 616;
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDoubleNormal() {
        PersonalInfo personalInfo = new PersonalInfo("diana_k", "hunterX2024",
                "diana.k@mail.com", "+27 83 555 8899",
                "55", "female", "South Africa");
        SearchEngine<Double> searchEngine = new SearchEngine<>(personalInfo);
        Double result = searchEngine.handleSearch(
                "What is the IMDb rating of the movie Forest Gump?",
                Type.DOUBLE,
                Format.NORMAL);

        Double expected = 8.8;
        assertNotNull(result);
        System.out.println("Test Result: " + result);
        System.out.println("Expected: " + expected);
        assertEquals(result, expected);
    }
}
