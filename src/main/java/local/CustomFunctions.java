package local;

public class CustomFunctions {

    public static String invertFirstCharacter(String string) {
        char firstCharacter = string.charAt(0);
        if (Character.isLowerCase(firstCharacter))
            firstCharacter = Character.toUpperCase(firstCharacter);
        else firstCharacter = Character.toLowerCase(firstCharacter);
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.setCharAt(0, firstCharacter);
        return stringBuilder.toString();
    }

}
