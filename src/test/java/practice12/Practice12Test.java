package practice12;

import org.junit.Before;
import org.junit.Test;
import practice11.Klass;
import practice11.Student;
import practice11.Teacher;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.fest.assertions.api.Assertions.assertThat;

public class Practice12Test {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private String systemOut() {
        return outContent.toString();
    }

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void should_assistant_introduce_itself_with_which_classes_it_assist() {
        LinkedList<Klass> linkedList = new LinkedList<>();
        linkedList.add(new Klass(2));
        linkedList.add(new Klass(3));
        Assistant tom = new Assistant(1, "Tom", 21, linkedList);
        assertThat(tom.introduce()).isEqualTo("My name is Tom. I am 21 years old. I am a Assistant. I assist Class 2, 3.");
    }

    @Test
    public void should_assistant_introduce_itself_with_no_class_assist() {
        Assistant tom = new Assistant(1, "Tom", 21);
        assertThat(tom.introduce()).isEqualTo("My name is Tom. I am 21 years old. I am a Assistant. I assist No Class.");
    }

    @Test
    public void should_student_be_notified_when_student_become_leader_of_classes_it_belongs() {
        Klass klass = new Klass(1);

        Student jerry = new Student(1, "Jerry", 8, klass);
        Student mark = new Student(1, "Mark", 18, klass);

        klass.appendMember(jerry);
        klass.appendMember(mark);
        klass.assignLeader(mark);

        assertThat(systemOut().contains("My name is Jerry. I am 8 years old. I am a Student. I know that Mark has become the Leader of Class 1.\n")).isTrue();
        assertThat(systemOut().contains("My name is Mark. I am 18 years old. I am a Student. I've become the Leader of Class 1.\n")).isTrue();
    }

    @Test
    public void should_student_not_be_notified_when_student_become_leader_of_classes_it_not_belongs() {
        Klass klass = new Klass(1);
        Klass klass2 = new Klass(2);


        Student jerry = new Student(1, "Jerry", 8, klass2);
        Student mark = new Student(1, "Mark", 18, klass);

        klass2.appendMember(jerry);
        klass.appendMember(mark);
        klass.assignLeader(mark);

        assertThat(systemOut().contains("My name is Jerry. I am 8 years old. I am a Student. I know that Mark has become the Leader of Class 1.\n")).isFalse();
        assertThat(systemOut().contains("My name is Mark. I am 18 years old. I am a Student. I've become the Leader of Class 1.\n")).isTrue();
    }

}
