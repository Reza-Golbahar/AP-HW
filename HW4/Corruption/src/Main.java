import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String passwordHash = scanner.nextLine();
        int threadCount = scanner.nextInt();

        PasswordBreaker passwordBreaker = new PasswordBreaker();
        File file = new File("passwords.txt");
        passwordBreaker.loadPasswords(file);
        passwordBreaker.setTargetHash(passwordHash);
        passwordBreaker.startCracking(threadCount);

        System.out.println(passwordBreaker.getSpecialHashCount());
        String result = passwordBreaker.getFoundPassword();
        if (result == null)
            System.out.print("NOT FOUND");
        else
            System.out.print(result);
    }
}
