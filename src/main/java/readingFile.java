import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.util.regex.*;

import java.io.FileInputStream;

import static com.sun.tools.javac.util.StringUtils.toUpperCase;

public class readingFile {

    private final String[] jobDescription = new String[100];
    private Set<String> keyWords = new HashSet<>();
    private File[] files;
    private cvFile cvFile;


    public readingFile() {
        this.cvFile = new cvFile();
        addFile();
        mainMenu();
    }


    private void readCvFile() { // קוראת את הקובץ ומציגה את התוכן
        cvFile cvFile = new cvFile();
        System.out.println(displayTheFileContents(cvFile.getCvFile()));
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public void addFile() {
        File file = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\Automation Engineer.docx");
        File file1 = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\DevOps Engineer.docx");
        File file2 = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\Front.docx");
        File file3 = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\Full.docx");
        File file4 = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\Software Developer.docx");
        File file5 = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\Software Tester.docx");
        File file6 = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\Web Developer.docx");
//        File cvFile = new File("C:\\Users\\sofer\\OneDrive\\שולחן העבודה\\פרויקט\\CV shoham sofer P.docx");
        File[] files = {file, file1, file2, file3, file4, file5, file6};
//        this.cvFile = cvFile;
        this.files = files;
        System.out.println("Files added ");
        toFillTheJobDescription();

    }

    public void toFillTheJobDescription() {
        for (int i = 0; i < this.files.length; i++) {
            this.jobDescription[i] = deleteDocx(this.files[i].getName());
        }

    }


    public String displayTheFileContents(File file) { // מציגה את התוכן של הקובץ
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

    public void showAllJobs() { // מציגה את כל המשרות
        for (int i = 0; i < this.jobDescription.length; i++) {
            if (this.jobDescription[i] == null) {
                break;
            }
            System.out.println(i + 1 + ". " + this.jobDescription[i]);
        }
        selectJobDescription();
    }

    public void selectJobDescription() { // מבקש מהמשתמש שיבחר את תיאור המשרה שלו
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
        addToList(displayTheFileContents(this.files[jobDescriptionNumber - 1]));
        System.out.println("The key words are: " + this.keyWords);
        System.out.println("sum of key words: " + this.keyWords.size());
//        checkingTheInclusionOfKeywordsInResumes(displayTheFileContents(this.files[jobDescriptionNumber - 1]));
        sumOfCommonKeyWords(this.files[jobDescriptionNumber - 1], jobDescriptionNumber);
        showAllTheJobCount();

    }

    public String deleteDocx(String decJob) { // מוריד משם הקובץ את הסיומת שלו
        String newDecJob = decJob.replace(".docx", "");
        return newDecJob;
    }

    public void addToList(String stringOfJob) { // מוסיף את מילות המפתח לרשימה
        System.out.println("start addToList");
        ArrayList<String> listKeyWords = new ArrayList<>();
        String[] words = stringOfJob.split("\\.");
        listKeyWords.addAll(Arrays.asList(words));
        checkFile checkFile1 = new checkFile(stringOfJob , displayTheFileContents(this.cvFile.getCvFile()));
        for (int i = 0; i < listKeyWords.size(); i++) {
            this.keyWords.add(checkFile1.deleteCounter(listKeyWords.get(i), i));

        }
    }




    public void checksOtherJobs() { // בודק אם יש משרה אחרת מתאימה
        System.out.println("start checksOtherJobs");
        int[] counter = new int[this.files.length];
        for (int i = 0; i < this.files.length; i++) {
            checkFile checkFile1 = new checkFile(displayTheFileContents(this.files[i]) , displayTheFileContents(this.cvFile.getCvFile()));
            counter[i] = checkFile1.cvKeywordChecker(displayTheFileContents(this.files[i]), i);
            System.out.println("Job Name - " + deleteDocx(this.files[i].getName()) + "\n" + "Total keywords: " + counter[i]);
            checkFile1.showMissingKeyWords();
            checkFile1.showCommonKeyWords();
            double percent = ( (double) counter[i] / this.keyWords.size()) * 100;
            percent = Math.round(percent * 100.0) / 100.0;
            System.out.println("Percent of common keywords: " + percent + "%");
        }
        Map<String, Integer> max = new HashMap<>();
        for (int i = 0; i < counter.length; i++) {
            max.put(deleteDocx(this.files[i].getName()), counter[i]);
        }
        int maxValueInMap = (Collections.max(max.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : max.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                System.out.println("The most suitable job is: " + entry.getKey());     // Print the key with max value
            }
        }
    }

    public void sumOfCommonKeyWords(File fileOfJob, int index) { // סכום מילות המפתח המשותפות
//        System.out.println("start sumOfCommonKeyWords");
        checkFile checkFile1 = new checkFile(displayTheFileContents(fileOfJob) , displayTheFileContents(this.cvFile.getCvFile()));
        int sum = checkFile1.cvKeywordChecker(displayTheFileContents(fileOfJob), index);
        System.out.println("The sum of common key words is: " + sum);
    }

    public void showAllTheJobCount() { // פונקצית מעבר לבדיקת כמות מילות המפתח לכל המשרות
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to see how many keywords you have in the other jobs? (yes/no)");
        String answer = scanner.nextLine();
        if (answer.equals("yes")) {
            checksOtherJobs();
        } else {
            System.out.println("Goodbye");
        }
    }

    public void mainMenu() { // תפריט ראשי
        Scanner scanner = new Scanner(System.in);
        System.out.println("main menu: ");
        System.out.println("which of the following would you like to do?");
        System.out.println("1. show the job description");
        System.out.println("2. show the cv");
//        System.out.println("3. show the missing key words");
//        System.out.println("4. show the common key words + sum of common key words");
        System.out.println("3. show the most suitable job");
        System.out.println("4. exit");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1 -> {
                showAllJobs();
                mainMenu();
            }
            case 2 -> {
                readCvFile();
                mainMenu();
            }
            case 3 -> {
                checksOtherJobs();
                mainMenu();
            }
            case 4 -> System.out.println("Goodbye");
            default -> {
                System.out.println("Wrong input");
                mainMenu();
            }
        }
    }



}
