
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.Order;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class TestProfessorsLaboratory {
    Trout trout = new Trout("trout", "T1");
    Salmon salmon = new Salmon("salmon", "S1");
    Dog dog = new Dog("dog", "D1");
    Wolf wolf = new Wolf("wolf", "W1");
    Turtle turtle = new Turtle("turtle", "Tu1");
    Snake snake = new Snake("snake", "Sn1");
    Eagle eagle = new Eagle("eagle", "E1");
    GreatWhiteShark shark = new GreatWhiteShark("shark", "Sh1");
    EasternNewt easternNewt = new EasternNewt("easternNewt", "EN1");
    ArrayList<Animal> animals = new ArrayList<>();
    String[] expectedAnswer = new String[23];

    @Before
    public void setUp() throws IOException {
        animals.add(trout); animals.add(salmon); animals.add(dog); animals.add(wolf); animals.add(turtle);
        animals.add(snake); animals.add(eagle); animals.add(snake); animals.add(easternNewt);
        for(int i = 0;i < 23;i++) {
            FileReader fileReader = new FileReader("src/test/java/answers/test" + i + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line).append("\n");
            }
            expectedAnswer[i] = String.valueOf(text);
        }
    }

    @Order(1)
    @Test
    public void testInstanceAndAnimalInfo() {
        for(int i = 0;i < animals.size();i++) {
            Animal ourAnimal = animals.get(i);
            Animal animal = ProfessorsLaboratory.createInstance(ourAnimal.getClass().getName(), ourAnimal.getName(), ourAnimal.getDNA());
            assertEquals(ProfessorsLaboratory.getAnimalInfo(animal), expectedAnswer[i]);
        }
    }

    @Order(2)
    @Test
    public void testGetLowestCommonAncestor(){
        assertEquals(ProfessorsLaboratory.getLowestCommonAncestor(shark, salmon), Fish.class.getName());
        assertEquals(ProfessorsLaboratory.getLowestCommonAncestor(trout, salmon), BonyFish.class.getName());
        assertEquals(ProfessorsLaboratory.getLowestCommonAncestor(dog, wolf), Mammal.class.getName());
        assertEquals(ProfessorsLaboratory.getLowestCommonAncestor(turtle, snake), Reptile.class.getName());
        assertEquals(ProfessorsLaboratory.getLowestCommonAncestor(eagle, dog), Animal.class.getName());
        assertEquals(ProfessorsLaboratory.getLowestCommonAncestor(trout, snake), Animal.class.getName());
        assertEquals(ProfessorsLaboratory.getLowestCommonAncestor(easternNewt, easternNewt), EasternNewt.class.getName());
    }

    @Order(3)
    @Test
    public void testAverageReproductionsPerYear(){
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(salmon), 1500, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(trout), 450, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(shark), 1.8, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(dog), 5.4, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(wolf), 3.6, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(eagle), 1, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(snake), 9, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(turtle), 4.5, 0.01);
        assertEquals(ProfessorsLaboratory.averageReproductionsPerYear(easternNewt), 30, 0.01);
    }

    @Order(4)
    @Test
    public void testApplyGeneticModification(){
        ProfessorsLaboratory.applyGeneticModification(dog, wolf);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(dog), expectedAnswer[9]);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(wolf), expectedAnswer[10]);

        ProfessorsLaboratory.applyGeneticModification(salmon, trout);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(salmon), expectedAnswer[11]);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(trout), expectedAnswer[12]);

        ProfessorsLaboratory.applyGeneticModification(shark, trout);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(shark), expectedAnswer[13]);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(trout), expectedAnswer[14]);

        ProfessorsLaboratory.applyGeneticModification(easternNewt, wolf);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(easternNewt), expectedAnswer[15]);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(wolf), expectedAnswer[16]);

        ProfessorsLaboratory.applyGeneticModification(eagle, wolf);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(eagle), expectedAnswer[17]);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(wolf), expectedAnswer[18]);

        ProfessorsLaboratory.applyGeneticModification(easternNewt, turtle);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(easternNewt), expectedAnswer[19]);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(turtle), expectedAnswer[20]);

        ProfessorsLaboratory.applyGeneticModification(easternNewt, snake);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(easternNewt), expectedAnswer[21]);
        assertEquals(ProfessorsLaboratory.getAnimalInfo(snake), expectedAnswer[22]);
    }

}
