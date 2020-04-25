import java.util.ArrayList;
import java.util.Scanner;

public class ChainHashing {
    private int m;
    private long p = 1000000007;
    private int x = 263;

    private ArrayList<String>[] hashTable;

    ChainHashing(int m) {
        this.m = m;
        hashTable = new ArrayList[m];
    }

    int getHashCode(String str) {
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            long pow = modPow(x, i); // cashing inside map ??
            hash += (int)str.charAt(i) * pow;
        }

        return (int)((hash % p) % m);
    }

    private long modPow(long base, long pow) {
        if (pow == 0) {
            return 1;
        }
        if (pow == 1) {
            return base;
        }

        if (pow % 2 == 0) {
            long t = modPow(base, pow / 2);
            return t * t % p;
        } else {
            return modPow(base, pow - 1) * base % p;
        }
    }

    public void addString(String string) {
        int code = getHashCode(string);

        if (hashTable[code] == null) {
            hashTable[code] = new ArrayList<>();
        } else if (hashTable[code].contains(string)) {
            return;
        }

        hashTable[code].add(0, string);
    }

    public void deleteString(String string) {
        int code = getHashCode(string);

        if (hashTable[code] != null) {
            hashTable[code].remove(string);
        }
    }

    public String findString(String string) {
        int code = getHashCode(string);

        if (hashTable[code] != null && hashTable[code].contains(string)) {
            return "yes";
        }

        return "no";
    }

    public String checkList(int index)
    {
        StringBuilder items = new StringBuilder();

        if (index >= m)
            return "";

        ArrayList<String> list = hashTable[index];

        if (list == null) {
            return "";
        }

        for (String key: list) {
            items.append(key).append(" ");
        }

        return items.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChainHashing hashing = new ChainHashing(scanner.nextInt());

        StringBuilder report = new StringBuilder();

        int count = scanner.nextInt();

        while (count-- >= 0 && scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");

            if (line.length < 2) {
                continue;
            }

            String command = line[0];
            String argument = line[1];

            switch (command) {
               case "add":
                   hashing.addString(argument);
                   break;
               case "check":
                   report.append(hashing.checkList(Integer.parseInt(argument))).append("\n");
                   break;
               case "find":
                   report.append(hashing.findString(argument)).append("\n");
                   break;
               case "del":
                   hashing.deleteString(argument);
                   break;
            }
        }

        System.out.println(report);
    }
}
