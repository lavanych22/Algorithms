import java.util.*;

import java.io.*;


public class Main {
    // Матрицю суміжності графа зберігаємо в массиві rel. Зарплату i-го працівника підраховуємо у комірці salary[i]
    private static String[] relations;
    private static long[] salary;

    // Пошук в глибину з вершини workernumber – визначаємо з/п workernumber-го працівника.
    private static long dfs(int workernumber) {
        // Якщо з/п workernumber-го працівника вже підрахована (salary[workernumber] != 0), то завершуємо пошук
        if (salary[workernumber] > 0) {
            return salary[workernumber];
        }

        // В іншому ж випадку запускаємо пошук в глибину по всіх "синах" вершини workernumber, визначаємо з/п всіх підлеглих workernumber-го працівника
        // Зарплата workernumber-го працівника рівна сумі з/п всіх його підлеглих
        for (int index = 0; index < relations.length; index++) {
            if (relations[workernumber].charAt(index) == 'Y') {
                salary[workernumber] = salary[workernumber] + dfs(index);
            }
        }

        // Якщо res = 0, то у workernumber-го працівника нема підлеглих. Відповідно встановлюємо його з/п 1(згідно з умовою завдання)
        if (salary[workernumber] == 0) {
            salary[workernumber] = 1;
        }

        return salary[workernumber];
    }


    public static void main(String[] args) throws IOException {
        Scanner con = new Scanner(new FileReader("bosses.in"));

        // Зчитуємо в масив rel матрицю суміжності графа
        while (con.hasNext()) {

            int n = con.nextInt();

            relations = new String[n];

            for (int i = 0; i < n; i++) {
                relations[i] = con.next();
            }

            long result = 0;

            salary = new long[n];

            // Запускаємо дфс на орієнтованому графі. Знаходимо з/п кожного працівника
            for (int i = 0; i < n; i++) {
                if (salary[i] == 0) {
                    dfs(i);
                }
            }

            // Знаходимо і виводимо сумарну зарплату всіх працівників result.
            for (int i = 0; i < n; i++) {
                result = result + salary[i];
            }

            System.out.println(result);
        }























































































































        con.close();
    }

}

 