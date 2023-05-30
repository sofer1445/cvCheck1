import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readingFile {

    private ArrayList<String> keyWords = new ArrayList<>();
    private File[] files;
    private String titleOfJob;

    public readingFile() {
        addFile();
        selectJobDescription();
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(ArrayList<String>keyWords) {
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

        File[] files = {file, file1, file2, file3, file4, file5, file6};
        this.files = files;
        System.out.println("Files added ");

    }


    public String displayTheFileContents(File file) {
        System.out.println("start reading file");
        String fileName = file.getName();
        System.out.println("File name: " + fileName);
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

    public void selectJobDescription(){ // מבקש מהמשתמש שיבחר את תיאור המשרה שלו
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

    public String deleteCounter(String keyWord , int index){ //HTML5 2
        System.out.println("start deleteCounter");
        String newKeyWord = null; // בעיה במקרה של בחירה ב4 , מדפיס null
        int length = keyWord.length();
        if(index > 9 && index < 100) {
            if(length != 1) {
                newKeyWord = keyWord.substring(0, length - 2);
            }
        }
        else if(index > 99 && index < 1000){
            if(length != 1) {
                newKeyWord = keyWord.substring(0, length - 3);
            }
        }
        else{
            if(length != 1) {
                newKeyWord = keyWord.substring(0, length - 1);
            }

        }
        return newKeyWord;
    }
}
