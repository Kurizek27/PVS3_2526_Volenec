package streams.maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Scores {
    public static void main(String[] args) throws IOException {
        List<TestResult> scores = Files.lines(Paths.get("data/scores.csv"))
                .map(line -> line.split(";"))
                .map(tokens -> new TestResult(tokens[0], tokens[1], Long.parseLong(tokens[2]), Integer.parseInt(tokens[3])))
                .toList();

        System.out.println(scores);


        HashMap<String, List<TestResult>> scoreMap = new HashMap<>();

        for (String subject : scoreMap.keySet()){
            System.out.println(subject);
            for (TestResult t : scoreMap.get(subject)){
                System.out.println("-- " + t.getStudent_name());
            }
        }



    }
}
class TestResult {
    String student_name;
    String subject;
    long score;
    int timeSpent;

    public TestResult(String student_name, String subject, long score, int timeSpent) {
        this.student_name = student_name;
        this.subject = subject;
        this.score = score;
        this.timeSpent = timeSpent;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "student_name='" + student_name + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", timeSpent=" + timeSpent +
                '}';
    }
}

