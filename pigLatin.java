import java.util.Scanner;
import java.util.Arrays;
public class pigLatin {
    public static void main(String[] args) {
        String[] userList = userInput();
        String[] pigLatin = new String[userList.length];
        int counter = 0;
        for (String i: userList) {
            // If word has no vowels, use -1 as a sentinel
            if (firstVowel(i) == -1) {
                String trans = i + "ay";
                pigLatin[counter] = trans;
                counter ++;
            }
            else {
                int firstVowel = firstVowel(i);
                String ch = i.substring(0, firstVowel);
                String sent = i.substring(firstVowel);
                String trans = sent + ch + "ay";
                pigLatin[counter] = trans;
                counter++;
            }
        }
        System.out.println(Arrays.toString(pigLatin).replace("[", "").replace("]", "")
        .replace(",", ""));
    }

    private static int firstVowel(String sent) {
        for (int i = 0; i < sent.length(); i++) {
            if ( sent.charAt(i) == 'a' || sent.charAt(i) == 'e' || sent.charAt(i) == 'i' || sent.charAt(i) == 'o' ||
            sent.charAt(i) == 'u') {
                return i;
            }
        }
        return -1;
    }

    private static String[] userInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter something to convert to pig latin: ");
        String userInput = input.nextLine();
        return userInput.split(" ");
    }
}