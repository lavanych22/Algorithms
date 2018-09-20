import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int coc; //comparison operation counter
    private static int eoc; //exchange operation counter

    public static void main(String[] args) {
        List<Conference> conferences = new ArrayList<>();
        Scanner scanner;
        try {
            String filePath = "file.cvs";
            scanner = new Scanner(new FileReader(filePath));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                conferences.add(new Conference(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        coc = 0;
        eoc = 0;
        long startTime = System.nanoTime();
        insertionSort(conferences);
        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("INSERTION SORT");
        System.out.println("Algorithm running time : " + estimatedTime + " (nano)");
        System.out.println("The number of comparison operations : " + coc);
        System.out.println("Number of exchange operations : " + eoc);
        System.out.println("Sort result :");
        for (Conference conference : conferences) {
            System.out.println("Participants = " + conference.getParticipants());
        }


        coc = 0;
        eoc = 0;
        startTime = System.nanoTime();
        List<Conference> conferenceList = sort(conferences);
        estimatedTime = System.nanoTime() - startTime;

        System.out.println("\nMERGE SORT");
        System.out.println("Algorithm running time : " + estimatedTime);
        System.out.println("The number of comparison operations : " + coc);
        System.out.println("Number of exchange operations : " + eoc);
        System.out.println("Sort result :");
        for (Conference conference : conferenceList) {
            System.out.println("Price = " + conference.getPrice());
        }
    }

    private static List<Conference> sort(List<Conference> arr) {
        coc++;
        if (arr.size() < 2) {
            return arr;
        }

        int m = arr.size() / 2;

        List<Conference> arr1 = new ArrayList<>(arr.subList(0, m));
        List<Conference> arr2 = new ArrayList<>(arr.subList(m, arr.size()));

        return merge(sort(arr1), sort(arr2));
    }

    private static List<Conference> merge(List<Conference> arr1, List<Conference> arr2) {
        int n = arr1.size() + arr2.size();
        List<Conference> arr = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < n; i++) {
            coc++;
            if (i1 == arr1.size()) {
                coc++;
                arr.add(i, arr2.get(i2++));
            } else if (i2 == arr2.size()) {
                coc += 2;
                eoc++;
                arr.add(i, arr1.get(i1++));
            } else {
                coc += 2;
                coc = +3;
                if (arr1.get(i1).getPrice() > arr2.get(i2).getPrice()) {
                    eoc++;
                    arr.add(i, arr1.get(i1++));
                } else {
                    eoc++;
                    arr.add(i, arr2.get(i2++));
                }
            }
        }
        return arr;
    }

    private static void insertionSort(List<Conference> conferenceList) {
        for (int min = 0; min < conferenceList.size() - 1; min++) {
            int least = min;
            for (int j = min + 1; j < conferenceList.size(); j++) {
                coc++;
                if (conferenceList.get(j).getParticipants() < conferenceList.get(least).getParticipants()) {
                    least = j;
                }
            }

            eoc++;
            Conference tmpConference = conferenceList.get(min);
            conferenceList.set(min, conferenceList.get(least));
            conferenceList.set(least, tmpConference);
        }
    }
}
