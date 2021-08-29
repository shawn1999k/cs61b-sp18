public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        Palindrome pd = new Palindrome();
        Deque dq = pd.wordToDeque(word);
        return isPdwithRecursion(dq);
    }

    private boolean isPdwithRecursion(Deque dq) {
        if (dq.size() == 1 || dq.size() == 0) {
            return true;
        }
        if (dq.removeFirst() != dq.removeLast()) {
            return false;
        }
        return isPdwithRecursion(dq);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome pd = new Palindrome();
        Deque dq = pd.wordToDeque(word);
        return newIsPdwithRecursion(dq, cc);
    }

    private boolean newIsPdwithRecursion(Deque dq, CharacterComparator cc) {
        if (dq.size() == 1 || dq.size() == 0) {
            return true;
        }
        if (!cc.equalChars((char) dq.removeFirst(), (char) dq.removeLast())) {
            return false;
        }
        return newIsPdwithRecursion(dq, cc);
    }
}
