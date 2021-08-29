public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int x) {
        N = x;
    }
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == N && Character.isLowerCase(x) && Character.isLowerCase(y)) {
            return true;
        }
        return false;
    }
}
