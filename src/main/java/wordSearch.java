public class wordSearch {
    private String text;
    private String word;

    public wordSearch(String text, String word) {
        this.text = text;
        this.word = word;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean containsWord() {
        int[] failureTable = buildFailureTable(this.word);
        int i = 0; // Index for the text string
        int j = 0; // Index for the word string
        try {
            while (i < this.text.length()) {
                if (this.text.charAt(i) == this.word.charAt(j)) {
                    if (j == this.word.length() - 1) {
                        return true; // Word found in the text
                    }
                    i++;
                    j++;
                } else if (j > 0) {
                    j = failureTable[j - 1];
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return false; // Word not found in the text
    }

    public int[] buildFailureTable(String pattern) {
        try{
        int[] failureTable = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                failureTable[i] = j + 1;
                j++;
            } else {
                if (j != 0) {
                    j = failureTable[j - 1];
                    i--;
                } else {
                    failureTable[i] = 0;
                }
            }
        }
        return failureTable;
    }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}


