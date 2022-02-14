public class Sets {

    public static long hasRoyalFlush(boolean[][] arr){
        for(int suit = 1; suit < 5; suit++){
            if(arr[10][suit] && arr[11][suit] && arr[12][suit] && arr[13][suit] && arr[1][suit]){
                return 90000000000L;
            }
        }
        return -1;
    }

    public static long hasStraightFlush(boolean[][] arr){
        for(int i = 1; i < 11; i++){
            for(int suit = 1; suit < 5; suit++){
                if(arr[i][suit] && arr[i+1][suit] && arr[i+2][suit] && arr[i+3][suit] && arr[((i + 3)%13) + 1][suit]){
                    return 80000000000L + (i+4);
                }
            }
        }
        return -1;
    }

    public static long has4ofAKind(boolean[][] arr){
        for(int i = 1; i < 14; i++){
            if(arr[i][1] && arr[i][2] && arr[i][3] && arr[i][4]){
                if(i == 1){
                    return 70000000000L + 14;
                }
                return 70000000000L + i;
            }
        }
        return -1;
    }

    public static long hasFullHouse(boolean[][] arr){
        long pair = hasPair(arr);
        long triple = has3ofAKind(arr);
        if(pair != -1 && triple != -1){
            return 60000000000L + (triple % 100) * 100 + (pair % 100);
        }
        return -1;
    }

    public static long hasFlush(boolean[][] arr){
        for(int suit = 1; suit < 5; suit++){
            int counter = 0;
            long result = 0;
            for(int i = 14; i > 0; i--){
                if(arr[((i - 1)% 13) + 1][suit]){
                    if(i == 14){
                        result += 14 * (long)Math.pow(10, (5 - counter - 1) * 2);
                    }
                    else{
                        result += i * (long)Math.pow(10, (5 - counter - 1) * 2);
                    }
                    counter++;
                }
                if(counter == 5) {
                    return result;
                }
            }

        }
        return -1;
    }

    public static long hasStraight(boolean[][] arr){
        return -1;
    }

    public static long has3ofAKind(boolean[][] arr){
        return -1;
    }

    public static long hasTwoPair(boolean[][] arr){
        return -1;
    }

    public static long hasPair(boolean[][] arr){
        return -1;
    }

    public static long highCard(boolean[][] arr){
        return -1;
    }


}
