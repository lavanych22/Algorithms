import java.util.Arrays;


public class Booring {
    private static String[] stringsArray = {"NYYYY", "YNNNN", "YNNNY", "YNNNY", "YNYYN"}; // початкові стрінги
    private static int memoizationArray[][]; // оголошуємо масив двовимірний(матриця твоя)

    private static int countHowMany(String[] strings) { // масив містить 5 стрічок, тому його розмір 5
        memoizationArray = new int[strings.length][(1 << strings.length) + 1]; // 1 << 5 = 1 * 2^5 = 32
        for (int i = 0; i < strings.length; ++i) {
            Arrays.fill(memoizationArray[i], -1); // тут ми ініціалізуємо матрицю, наповнюючи її -1
        }
        return solve(strings, 0, 0); //викликаємо фукцію, описану нижче
    }

    private static int solve(String[] strings, int index, int neighbourIndex) {
        if (index == strings.length) { // якщо індекс рівний довжині стрічки, вертає нічо
            return 0;
        }

        if (memoizationArray[index][neighbourIndex] != -1) { // якщо елемент в масиві відмінний від -1, то всьо ок
            return memoizationArray[index][neighbourIndex];
        }

        int tempNumber = solve(strings, index + 1, neighbourIndex); // рекурсивно викликаємо цю ж функцію, але для наступного індекса

        if ((neighbourIndex & (1 << index)) == 0) { // 1 << index = 1 * 2^index
            for (int i = 0; i < strings.length; ++i) {
                if (i != index && (neighbourIndex & (1 << i)) == 0 && stringsArray[index].charAt(i) == 'Y') { // 1 << i = 1 * 2^i
                    tempNumber = Math.max(tempNumber, 2 + solve(strings, index + 1, ((neighbourIndex | (1 << i)) | (1 << index))));
                }
            }
        }
        return memoizationArray[index][neighbourIndex] = tempNumber;
    }

    public static void main(String[] args) {
        int answer = countHowMany(stringsArray);
        System.out.println(answer);
    }
}