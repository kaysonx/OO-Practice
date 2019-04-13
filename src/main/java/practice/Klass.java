package practice;

import practice.event.ClassChangeListener;
import practice.event.ClassChangeNotifyEvent;
import practice.event.EventEnum;

import java.util.ArrayList;
import java.util.List;

public class Klass {
    private int number;
    private Student leader;
    private List<Student> students = new ArrayList<>();
    private List<ClassChangeListener> classChangeListeners = new ArrayList<>();

    public Klass(int number) {
        this.number = number;
    }

    public void assignLeader(Student student) {
        if (!students.contains(student)) {
            System.out.println("It is not one of us.");
            return;
        }
        leader = student;
        notifyAllObservers(new ClassChangeNotifyEvent(student, this, EventEnum.BECOME_LEADER));
    }

    public void appendMember(Student student) {
        students.add(student);
        notifyAllObservers(new ClassChangeNotifyEvent(student, this, EventEnum.JOIN_CLASS));
    }

    boolean isIn(Student student) {
        return student.getKlass().getNumber() == number;
    }

    private void notifyAllObservers(ClassChangeNotifyEvent event) {
        classChangeListeners.forEach(classChangeListener -> classChangeListener.notify(event));
    }

    void attachObserver(ClassChangeListener classChangeListener) {
        classChangeListeners.add(classChangeListener);
    }

    public Student getLeader() {
        return leader;
    }

    public int getNumber() {
        return number;
    }

    public String getDisplayName() {
        return "Class " + number;
    }

}
