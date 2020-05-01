import java.util.Scanner;

public class StringComparisonByHashing {
    private long p = 1000000007;
    private int x = 263;

    int getHashCode(String str) {
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            long pow = modPow(x, i); // cashing inside map ??
            hash += (int)str.charAt(i) * pow;
        }

        return (int)(hash % p);
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

    public void calculate(String pattern, String text) {
        int tLength = text.length();
        int pLength = pattern.length();

        long hash, pow;
        int k;
        boolean ok;
        long[] hashes = new long[tLength - pLength + 1]; //  save just prevHash
        long patternHash = getHashCode(pattern);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= tLength - pLength; i++) {
            hash = 0;
            k = 0;

            if (i > 0) {
                hash = hashes[i - 1] % p - (int)text.charAt(i - 1) % p + ((int)text.charAt(i + pLength - 1) * modPow(x, pLength)) % p;
                hash *= 836501907;
            } else {
                for (int j = i; j < i + pLength; j++) {
                    pow = modPow(x, k++);
                    hash += text.charAt(j) * pow;
                }
            }

            hashes[i] = (hash % p);

            if (hashes[i] == patternHash) {
                k = 0;
                ok = true;

                for (int l = i; l < pLength; l++) {
                    if (text.charAt(l) != pattern.charAt(k++)) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    result.append(i).append(" ");
                }
           }
        }

        System.out.println(result);
        /*for (int i = tLength - pLength; i >= 0; i--) {
            hash = 0;
            k = 0;

            if (i < tLength - pLength) {
                multiply = (int)text.charAt(i + pLength) * modPow(x, pLength - 1);
                diff = hashes[i + 1] - multiply;
                hashes[i] = ( (diff % p + p) % p ) * x + (int)text.charAt(i) % p;
                hashes[i] %= p;
            } else {
                for (int j = i; j < i + pLength; j++) {
                    pow = modPow(x, k++);
                    hash += text.charAt(j) * pow;
                }

                hashes[i] = (hash % p);
            }

            if (hashes[i] == patternHash) {
                result.append(i);
            }
        } */
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringComparisonByHashing task = new StringComparisonByHashing();
        task.calculate(scanner.nextLine(), scanner.nextLine());
    }
}
