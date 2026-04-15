package basics;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimePractise {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime lessonStart = LocalTime.of(10, 45);
        System.out.println(lessonStart);

        LocalTime nejakyNovy = LocalTime.from(lessonStart);
        System.out.println(nejakyNovy);

        LocalTime novyMinusTwo = nejakyNovy.minusHours(2);

        System.out.println(LocalTime.MIDNIGHT);
        System.out.println(LocalTime.MIN);

        System.out.println(now.toSecondOfDay() - lessonStart.toSecondOfDay());
        System.out.println(Duration.between(now, lessonStart));

        LocalDate dnesek = LocalDate.now();
        System.out.println(dnesek);
        System.out.println(LocalDate.EPOCH);
        System.out.println(LocalDate.MIN);
        System.out.println(LocalDate.MAX);

        LocalDate.of(2022, 2, 30);

    }

}
