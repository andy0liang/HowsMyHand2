public class Sets {

    public static long hasRoyalFlush(boolean[][] arr) {
        for (int suit = 1; suit < 5; suit++) {
            if (arr[10][suit] && arr[11][suit] && arr[12][suit] && arr[13][suit] && arr[14][suit]) {
                return 90000000000L;
            }
        }
        return -1;
    }

    public static long hasStraightFlush(boolean[][] arr) {
        for (int i = 10; i >= 1; i--) {
            for (int suit = 1; suit < 5; suit++) {
                if (arr[i][suit] && arr[i + 1][suit] && arr[i + 2][suit] && arr[i + 3][suit] && arr[i + 4][suit]) {
                    return 80000000000L + (i + 4);
                }
            }
        }
        return -1;
    }

    public static long has4ofAKind(boolean[][] arr) {
        for (int i = 14; i >= 2; i--) {
            if (rowContains(arr, i) == 4) {
                return 70000000000L + i;
            }
        }
        return -1;
    }

    public static long hasFullHouse(boolean[][] arr) {
        long pair = hasPair(arr);
        long triple = has3ofAKind(arr);
        if (pair != -1 && triple != -1) {
            return 60000000000L + ((triple / 10000) % 100) * 100 + ((pair / 1000000) % 100);
        }
        return -1;
    }

    public static long hasFlush(boolean[][] arr) {
        for (int suit = 1; suit < 5; suit++) {
            int counter = 0;
            long result = 50000000000L;
            for (int i = 14; i >= 2; i--) {
                if (arr[i][suit]) {
                    result += i * (long) Math.pow(10, (5 - counter - 1) * 2);
                    counter++;
                }
                if (counter == 5) {
                    return result;
                }
            }
        }
        return -1;
    }

    public static long hasStraight(boolean[][] arr) {
        for (int i = 10; i >= 1; i--) {
            if (consecutive(arr, i, 5)) {
                return 40000000000L + i + 3;
            }
        }
        return -1;
    }

    public static long has3ofAKind(boolean[][] arr) {
        for (int i = 14; i >= 2; i--) {
            if (rowContains(arr, i) == 3) {
                int[] best = getHighest(arr, i);
                return 30000000000L + i * 10000 + best[0] * 100 + best[1];
            }
        }
        return -1;
    }

    public static long hasTwoPair(boolean[][] arr) {
        int counter = 0;
        int[] p = new int[2];
        for (int i = 14; i >= 2; i--) {
            if (rowContains(arr, i) == 2) {
                p[counter] = i;
                counter++;
            }
            if (counter == 2) {
                return 20000000000L + p[0] * 10000 + p[1] * 100 + getHighest(arr, p[0], p[1])[0];
            }
        }
        return -1;
    }

    public static long hasPair(boolean[][] arr) {
        for (int i = 14; i >= 2; i--) {
            if (rowContains(arr, i) == 2) {
                int[] best = getHighest(arr, i);
                return 10000000000L + i * 1000000 + best[0] * 10000 + best[1] * 100 + best[2];
            }
        }
        return -1;
    }

    public static long highCard(boolean[][] arr) {
        int[] best = getHighest(arr, -1);
        return (long) best[0] * 100000000 + best[1] * 1000000 + best[2] * 10000 + best[3] * 100 + best[4];
    }

    public static String flushDraws(boolean[][] arr, int numCommunity) {
        if (numCommunity == 3) {
            for (int suit = 1; suit <= 4; suit++) {
                int counter = 0;
                for (int x = 2; x <= 14; x++) {
                    if (arr[x][suit]) {
                        counter++;
                    }
                }
                if (counter == 3) {
                    return "Backdoor Flush Draw\n";
                }
                if (counter == 4) {
                    return "Flush Draw\n";
                }
            }
        }
        if (numCommunity == 4) {
            for (int suit = 1; suit <= 4; suit++) {
                int counter = 0;
                for (int x = 2; x <= 14; x++) {
                    if (arr[x][suit]) {
                        counter++;
                    }
                }
                if (counter == 4) {
                    return "Flush Draw\n";
                }
            }
        }
        return "";
    }

    public static String straightDraws(boolean[][] arr, int numCommunity) {
        if (numCommunity == 3) {
            for (int x = 2; x < 12; x++) {
                if (consecutive(arr, x, 4)) {
                    return "Straight Draw\n";
                }
            }
            for (int x = 2; x < 13; x++) {
                if (consecutive(arr, x, 3)) {
                    return "Backdoor Straight Draw\n";
                }
            }
        }
        if (numCommunity == 4) {
            for (int x = 2; x < 12; x++) {
                if (consecutive(arr, x, 4)) {
                    return "Straight Draw\n";
                }
            }
        }
        return "";
    }

    public static boolean consecutive(boolean[][] arr, int row, int num) {
        for (int x = row; x < row + num; x++) {
            if (rowContains(arr, x) == 0) {
                return false;
            }
        }
        return true;
    }

    public static int rowContains(boolean[][] arr, int row) {
        int counter = 0;
        for (int i = 1; i < 5; i++) {
            if (arr[row][i]) {
                counter++;
            }
        }
        return counter;
    }

    public static int[] getHighest(boolean[][] arr, int exclude1, int exclude2) {
        int[] best = new int[5];
        int index = 0;
        for (int i = 14; i >= 2 && index < 5; i--) {
            if (i == exclude1 || i == exclude2) {
                continue;
            }
            for (int suit = 1; suit < 5 && index < 5; suit++) {
                if (arr[i][suit]) {
                    best[index] = i;
                    index++;
                }
            }
        }
        return best;
    }

    public static int[] getHighest(boolean[][] arr, int exclude) {
        int[] best = new int[5];
        int index = 0;
        for (int i = 14; i >= 2 && index < 5; i--) {
            if (i == exclude) {
                continue;
            }
            for (int suit = 1; suit < 5 && index < 5; suit++) {
                if (arr[i][suit]) {
                    best[index] = i;
                    index++;
                }
            }
        }
        return best;
    }
}
