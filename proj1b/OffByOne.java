public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == 1 && ((Character.isLowerCase(x) && Character.isLowerCase(y))
                || (Character.isUpperCase(x) && Character.isUpperCase(y)))) {
            return true;
        }
        return false;
    }
}
