import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class ProfessorsLaboratory {
    public static Animal createInstance(String className, String name, String DNA) {
        Animal animal = null;
        try {
            Class<?> class2 = Class.forName(className);
            Constructor<?> constructor = class2.getConstructor(String.class, String.class);
            animal =  (Animal) constructor.newInstance(name, DNA);
        } catch (Exception ignored) {

        }
        return animal;
    }

    public static String getLowestCommonAncestor(Animal animal1, Animal animal2){
        ArrayList<Class<?>> animal1Ancestors = findAllAncestors(animal1);
        ArrayList<Class<?>> animal2Ancestors = findAllAncestors(animal2);

        int minLength = Math.min(animal1Ancestors.size(), animal2Ancestors.size());
        Class<?> lowestCommonAncestor = Object.class;
        for (int i = 0; i < minLength; i++) {
            Class<?> animal1Ancestor = animal1Ancestors.get(i);
            if (animal2Ancestors.contains(animal1Ancestor))
                lowestCommonAncestor = animal1Ancestor;
        }

        return lowestCommonAncestor.getName();
    }

    private static ArrayList<Class<?>> findAllAncestors(Animal animal) {
        Class<?> currentClass = animal.getClass();
        ArrayList<Class<?>> animalAncestors = new ArrayList<>();
        do {
            animalAncestors.add(0, currentClass);
            currentClass = currentClass.getSuperclass();
        } while (currentClass != Object.class);
        return animalAncestors;
    }

    public static String getAnimalInfo(Animal animal){
        Class<?> currentClass = animal.getClass();

        StringBuilder stringBuilder = new StringBuilder();
        do {
            Field[] fields = currentClass.getDeclaredFields();

            stringBuilder.append("class %s=>\n".formatted(currentClass.getSimpleName()));
            for (Field field : fields) {
                field.setAccessible(true);
                String pronoun = (Modifier.isStatic(field.getModifiers())) ?"all" : "this";

                if (field.getType() == Class.class)
                    continue;

                try {
                    Object value = Modifier.isStatic(field.getModifiers()) ? field.get(null) : field.get(animal);
                    stringBuilder.append("\tfor %s %s-> %s: %s\n".formatted(
                            pronoun, currentClass.getName(), field.getName(), value
                    ));
                } catch (IllegalAccessException ignored) {
                }
            }
            currentClass = currentClass.getSuperclass();

        } while(currentClass != Object.class);

        return stringBuilder.toString();
    }

    public static void applyGeneticModification(Animal animal1, Animal animal2){
        Animal stronger = animal1;
        Animal weaker = animal2;
        if (animal2.getDNA().compareTo(animal1.getDNA()) > 0) {
            stronger = animal2;
            weaker = animal1;
        }


        try {
            Field isWarmBlooded = Animal.class.getDeclaredField("isWarmBlooded");
            isWarmBlooded.setAccessible(true);
            if (!isWarmBlooded.get(animal1).equals(isWarmBlooded.get(animal2)))
                return;

            Field name = Animal.class.getDeclaredField("name");
            name.setAccessible(true);
            name.set(weaker, "%sEnhanced".formatted(weaker.getName()));

            Field DNA = Animal.class.getDeclaredField("DNA");
            DNA.setAccessible(true);
            DNA.set(weaker, "%s%s".formatted(weaker.getDNA(), stronger.getDNA()));

            Field habitat = Animal.class.getDeclaredField("habitat");
            habitat.setAccessible(true);
            if (habitat.get(stronger) == "WATER_AND_LAND" ||
                    (habitat.get(animal1) == "WATER" && habitat.get(animal2) == "LAND") ||
                    (habitat.get(animal1) == "LAND" && habitat.get(animal2) == "WATER"))
                habitat.set(weaker, "WATER_AND_LAND");

            Field bodyColor = Animal.class.getDeclaredField("bodyColor");
            bodyColor.setAccessible(true);
            bodyColor.set(weaker, "%s/%s".formatted(bodyColor.get(stronger), bodyColor.get(weaker)));

            Field averageWeight = Animal.class.getDeclaredField("averageWeight");
            averageWeight.setAccessible(true);
            double sum = ((Double)averageWeight.get(stronger)) + (Double) averageWeight.get(weaker);
            averageWeight.set(weaker, (sum / 2.0));

            Field averageLifespan = Animal.class.getDeclaredField("averageLifespan");
            averageLifespan.setAccessible(true);
            int sum2 = ((Integer)averageLifespan.get(stronger)) + (Integer) averageLifespan.get(weaker);
            averageLifespan.set(weaker, (sum2 / 2));

            Field strongerHumanUsesField = stronger.getClass().getDeclaredField("humanUses");
            Field weakerHumanUsesField = weaker.getClass().getDeclaredField("humanUses");

            ArrayList<String> weakerHumanUses = ((ArrayList<String>) weakerHumanUsesField.get(stronger));
            for (String humanUse : ((ArrayList<String>) strongerHumanUsesField.get(stronger))) {
                if (!weakerHumanUses.contains(humanUse))
                    weakerHumanUses.add(humanUse);
            }
        } catch (Exception ignored) {

        }
    }

    public static double averageReproductionsPerYear(Animal animal){
        Class<?> currentClass = animal.getClass();
        Method survivalToMaturityRate = null, yearlyReproductionYield = null;
        do {
            for (Method method : currentClass.getMethods()) {
                if (method.getName().equals("survivalToMaturityRate") && survivalToMaturityRate == null)
                    survivalToMaturityRate = method;
                else if (method.getName().equals("yearlyReproductionYield") && yearlyReproductionYield == null)
                    yearlyReproductionYield = method;
            }
            if (currentClass != Object.class)
                currentClass = currentClass.getSuperclass();
            else
                break;
        } while (survivalToMaturityRate != null && yearlyReproductionYield != null);

        double survival = 0;
        int reproduction = 0;
        try {
            survival = (Double) survivalToMaturityRate.invoke(animal);
            reproduction = (Integer) yearlyReproductionYield.invoke(animal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return survival * reproduction;
    }

}
