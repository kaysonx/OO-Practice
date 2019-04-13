package practice;

import practice.event.ClassChangeListener;
import practice.event.ClassChangeNotifyEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Teacher extends Person implements ClassChangeListener {
    private List<Klass> classes = new ArrayList<>();

    public Teacher(int id, String name, int age) {
        super(id, name, age);
    }

    public Teacher(int id, String name, int age, LinkedList<Klass> linkedList) {
        super(id, name, age);
        classes = linkedList;
        linkedList.forEach(klass -> klass.attachObserver(this));
    }

    @Override
    public String introduce() {
        return classes.isEmpty() ? teachWith("No Class")
                : teachWith("Class " + getClassesString());
    }

    public String introduceWith(Student student) {
        return isTeaching(student) ? teachWith(student.getName()) : notTeachWith(student.getName());
    }

    private String getClassesString() {
        return classes.stream().map(clazz ->
                String.valueOf(clazz.getNumber())).collect(Collectors.joining(", "));
    }

    private String basicIntroduce() {
        return super.introduce() + " I am a Teacher.";
    }

    private String teachWith(String name) {
        return basicIntroduce() + " I teach " + name + ".";
    }

    private String notTeachWith(String name) {
        return basicIntroduce() + " I don't teach " + name + ".";
    }

    public boolean isTeaching(Student student) {
        return classes.stream().anyMatch(classes ->
                classes.isIn(student)
        );
    }

    @Override
    public void notify(ClassChangeNotifyEvent event) {
        switch (event.getEventType()) {
            case JOIN_CLASS:
                System.out.printf("I am %s. I know %s has joined %s.%n",
                        getName(),
                        event.getStudent().getName(),
                        event.getKlass().getDisplayName());
                break;
            case BECOME_LEADER:
                System.out.printf("I am %s. I know %s become Leader of %s.%n",
                        getName(),
                        event.getStudent().getName(),
                        event.getKlass().getDisplayName());
                break;
        }
    }

    public List<Klass> getClasses() {
        return classes;
    }
}
