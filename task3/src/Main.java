import java.util.Objects;

public class Main {
    private static final String PASSWORD = "d4dff3B4";
    public static void main(String[] args) {

        String[] signs = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F",
                "a", "b", "c", "d", "e", "f"
        };
        String pass = "00000000";
        int elementNumber = 1;
        int counter = 0;
        int signIterator = 1;
        while (true) {
            counter++;
            System.out.println(pass);
            String response = checkPass(pass);
            if (Objects.equals(response, "success")) {
                System.out.println("Success!");
                break;
            }
            if (Objects.equals(response, "This password is too long")) {
                System.out.println("This password is too long!");
                break;
            }
            if (Objects.equals(response, "Password has different length")) {
                System.out.println("Password has different length!");
                break;
            }
            if (signIterator == 22) {
                signIterator = 1;
                elementNumber++;
                continue;
            }
            if (Objects.equals(response, "<pass")) {
                pass = pass.substring(0, elementNumber - 1) + signs[signIterator] + pass.substring(elementNumber);
                signIterator++;
            } else {
                pass = pass.substring(0, elementNumber - 1) + signs[signIterator - 2] + pass.substring(elementNumber);
                elementNumber++;
                signIterator = 1;
            }
        }
        System.out.println("Number of requests: " + counter);
        System.out.println("Password: " + pass);
    }

    public static String checkPass(String pass) {
        System.out.println("check: " + pass);
        if (pass.length() > 8) {
            return "This password is too long";
        }
        if (pass.length() != 8) {
            return "Password has different length";
        }
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) == PASSWORD.charAt(i)) {
                continue;
            }
            if (pass.charAt(i) > PASSWORD.charAt(i)) {
                return ">pass";
            }
            return "<pass";
        }
        return "success";
    }
}