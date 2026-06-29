import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PasswordBreaker {
    private String targetHash;
    private final Queue<String> passwordQueue = new LinkedList<>();
    private final AtomicReference<String> foundPassword = new AtomicReference<>(null);
    private final AtomicInteger specialHashCount = new AtomicInteger(0);

    public void setTargetHash(String hash) {
        this.targetHash = hash;
    }

    public void loadPasswords(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                passwordQueue.offer(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startCracking(int numThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        while (!passwordQueue.isEmpty() && foundPassword.get() == null) {
            List<Future<?>> futures = new ArrayList<>();

            for (int i = 0; i < numThreads && !passwordQueue.isEmpty(); i++) {
                String password = passwordQueue.poll();
                if (password == null) break;

                Future<?> future = executor.submit(() -> {
                    String hash = CryptoHash.hashString(password);

                    // Check for match
                    if (hash.equals(targetHash)) {
                        foundPassword.compareAndSet(null, password);
                    }

                    // Special hash check (even if match found)
                    for (int j = 0; j < 3; j++) {
                        char ch = hash.charAt(j);
                        if (ch >= '0' && ch <= '6') {
                            specialHashCount.incrementAndGet();
                            break;
                        }
                    }
                });

                futures.add(future);
            }

            // Wait for this batch to finish
            for (Future<?> future : futures) {
                try {
                    future.get(); // Waits for the task to complete
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            if (foundPassword.get() != null) break;
        }

        executor.shutdownNow(); // Cancel remaining tasks
    }

    public String getFoundPassword() {
        return foundPassword.get();
    }

    public int getSpecialHashCount() {
        return specialHashCount.get();
    }
}
