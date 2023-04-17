package ltdd.doan.mangxahoi.data;

import java.util.Random;

public class Utils {

    public static String randomS(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String randomS(int length ){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int random(){
        return new Random().nextInt(100);
    }
    public static int random(int range){
        return new Random().nextInt(range);
    }
    public static String randomT(){
        Integer random = new Random().nextInt(3);
        return random.toString();
    };
}
