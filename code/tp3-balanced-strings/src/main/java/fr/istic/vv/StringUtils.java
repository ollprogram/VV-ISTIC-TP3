package fr.istic.vv;


public abstract class StringUtils {

    public static boolean isBalanced(String str) {
        int[] counts = new int[3];
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            switch(c){
                case '{': counts[0]++; break;
                case '[': counts[1]++; break;
                case '(': counts[2]++; break;
                case '}': counts[0]--; break;
                case ']': counts[1]--; break;
                case ')': counts[2]--; break;
                default : return false;

            }
        }
        return counts[0] == 0 && counts[1] == 0 && counts[2] == 0;
    }

}
