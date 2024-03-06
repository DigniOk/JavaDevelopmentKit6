import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class MontyHallGame {
    private static final int NUM_TRIALS = 1000;
    private static final List<Boolean> resultsWithoutSwitching = new ArrayList<>();
    private static final List<Boolean> resultsWithSwitching = new ArrayList<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < NUM_TRIALS; i++) {
            resultsWithSwitching.add(game(true));
            resultsWithoutSwitching.add(game(false));
        }
        long winWithSwitching = resultsWithSwitching.stream().filter(e -> e).count();
        long winWithoutSwitching = resultsWithoutSwitching.stream().filter(e -> e).count();
        System.out.println("Количество выйгрышей с изменением выбора: " + winWithSwitching + " - " + ((winWithSwitching*100.0)/NUM_TRIALS) + "%");
        System.out.println("Количество выйгрышей без изменением выбора: " + winWithoutSwitching+ " - " + ((winWithoutSwitching*100.0)/NUM_TRIALS) + "%");
    }
    private static boolean game(boolean choice) {
        boolean[] doors = new boolean[3];
        doors[random.nextInt(3)] = true;
        int tmp = random.nextInt(0, 3);
        int wieDoor = -1;
        for (int i = 0; i < 2; i++) {
            if (!doors[i]) {
                wieDoor = i;
            }
        }
        int nextChoice = tmp;
        if (choice) {
            for (int i = 0; i < 2; i++) {
                if (i == wieDoor || i == nextChoice) {
                    continue;
                }
                nextChoice = i;
            }
        }
        return doors[nextChoice];
    }
}