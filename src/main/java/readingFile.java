import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;

public class readingFile {

    private ArrayList<String> keyWords = new ArrayList<>();
    private File[] files;
    private String titleOfJob;
    private File cvFile;

    public readingFile() {
        addFile();
        selectJobDescription();
//        readCvFile();
    }

    private void readCvFile() { // קוראת את הקובץ ומציגה את התוכן
        System.out.println(displayTheFileContents(this.cvFile));
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(ArrayList<String> keyWords) {
        this.keyWords = keyWords;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public void addFile() {
        File file = new File("jobsTitle/Automation Engineer.docx");
        File file1 = new File("jobsTitle/DevOps Engineer.docx");
        File file2 = new File("jobsTitle/Front.docx");
        File file3 = new File("jobsTitle/Full.docx");
        File file4 = new File("jobsTitle/Software Developer.docx");
        File file5 = new File("jobsTitle/Software Tester.docx");
        File file6 = new File("jobsTitle/Web Developer.docx");
        File cvFile = new File("jobsTitle/CV shoham sofer P.docx");
        File[] files = {file, file1, file2, file3, file4, file5, file6};
        this.cvFile = cvFile;
        this.files = files;
        System.out.println("Files added ");

    }


    public String displayTheFileContents(File file) {
        System.out.println(); // down line
        String fileName = file.getName();
//        System.out.println("File name: " + fileName);
        StringBuilder fileContent = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        fileContent.append(text);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileContentString = fileContent.toString();
        return fileContentString;
    }

    public void selectJobDescription() { // מבקש מהמשתמש שיבחר את תיאור המשרה שלו
        String[] jobDescription = new String[this.files.length];
        for (int i = 0; i < this.files.length; i++) {
            jobDescription[i] = this.files[i].getName();

        }
        for (int i = 0; i < jobDescription.length; i++) {
            System.out.println(i + 1 + ". " + deleteDocx(jobDescription[i]));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the job description: ");
        int jobDescriptionNumber = scanner.nextInt();
        switch (jobDescriptionNumber) {
            case 1 -> System.out.println("You have selected: " + deleteDocx(jobDescription[0]));
            case 2 -> System.out.println("You have selected: " + deleteDocx(jobDescription[1]));
            case 3 -> System.out.println("You have selected: " + deleteDocx(jobDescription[2]));
            case 4 -> System.out.println("You have selected: " + deleteDocx(jobDescription[3]));
            case 5 -> System.out.println("You have selected: " + deleteDocx(jobDescription[4]));
            case 6 -> System.out.println("You have selected: " + deleteDocx(jobDescription[5]));
            case 7 -> System.out.println("You have selected: " + deleteDocx(jobDescription[6]));
            default -> System.out.println("Invalid input");
        }
        this.titleOfJob = jobDescription[jobDescriptionNumber - 1];
        addToList(displayTheFileContents(this.files[jobDescriptionNumber - 1]));
        System.out.println("The key words are: " + this.keyWords);
        System.out.println("sum of key words: " + this.keyWords.size());
//        checkingTheInclusionOfKeywordsInResumes(displayTheFileContents(this.files[jobDescriptionNumber - 1]));
        sumOfCommonKeyWords(this.files[jobDescriptionNumber - 1] , jobDescriptionNumber);
        showAllTheJobCount();

    }

    public String deleteDocx(String decJob) { // מוריד משם הקובץ את הסיומת שלו
        String newDecJob = decJob.replace(".docx", "");
        return newDecJob;
    }

    public void addToList(String keyWords) {
        System.out.println("start addToList");
        ArrayList<String> listKeyWords = new ArrayList<>();
        String[] words = keyWords.split("\\.");
        listKeyWords.addAll(Arrays.asList(words));
        for (int i = 0; i < listKeyWords.size(); i++) {
            this.keyWords.add(deleteCounter(listKeyWords.get(i), i));

        }
    }

    public String deleteCounter(String keyWord, int index) {
//        System.out.println("start deleteCounter");
        String newKeyWord = ""; // בעיה במקרה של בחירה ב4 , מדפיס null
        int length = keyWord.length();
        if (index > 9 && index < 100) {
            if (length != 1) {
                newKeyWord = keyWord.substring(0, length - 2);
            }
        } else if (index > 99 && index < 1000) {
            if (length != 1) {
                newKeyWord = keyWord.substring(0, length - 3);
            }
        } else {
            if (length != 1) {
                newKeyWord = keyWord.substring(0, length - 1);
            }

        }
        return newKeyWord;
    }

    public File upDateFile(String fileName) { // הוספת קובץ חדש
        File file = new File("jobsTitle/" + fileName + ".docx");
        return file;
    }

    public int cvKeywordChecker(String descriptionJob , int index) { // בודק אם מילות המפתח קיימות בקובץ הקורות חיים
        System.out.println("start checking The Inclusion Of Keywords In Resumes");
        String cvFileContent = displayTheFileContents(this.cvFile);
        String[] newKeyWords = descriptionJob.split("\\.");
        int counter = 0;
        String theKeyWord = null;
        for (int i = 0; i < newKeyWords.length; i++) {
            theKeyWord = deleteCounter(newKeyWords[i] , index);
            if (cvFileContent.contains(theKeyWord)) {
                counter++;
            }
        }
        return counter;

    }

    public void checksOtherJobs() { // בודק אם יש משרה אחרת מתאימה
        System.out.println("start checksOtherJobs");
        int[] counter = new int[this.files.length];
        for (int i = 0; i < this.files.length; i++) {
            counter[i] = cvKeywordChecker(displayTheFileContents(this.files[i]) , i);
            System.out.println(this.files[i].getName() + " : " + counter[i]);
        }
        Map<String,Integer> max = new HashMap<>();
        for (int i = 0; i < counter.length; i++) {
            max.put(deleteDocx(this.files[i].getName()),counter[i]);
        }
        int maxValueInMap=(Collections.max(max.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : max.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println("The most suitable job is: " + entry.getKey());     // Print the key with max value
            }
        }


    }

    public void sumOfCommonKeyWords(File fileOfJob , int index) { // סכום מילות המפתח המשותפות
        System.out.println("start sumOfCommonKeyWords");
        int sum = cvKeywordChecker(displayTheFileContents(fileOfJob) , index);
        System.out.println("The sum of common key words is: " + sum);
    }

    public void showAllTheJobCount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to see how many keywords you have in the other jobs? (yes/no)");
        String answer = scanner.nextLine();
        if (answer.equals("yes")){
            checksOtherJobs();
        }else {
            System.out.println("Goodbye");
        }
    }
}
