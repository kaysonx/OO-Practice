package practice11;

import practice11.event.ClassChangeListener;
import practice11.event.ClassChangeNotifyEvent;
import practice11.event.EventEnum;

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

    void assignLeader(Student student) {
        if (!students.contains(student)) {
            System.out.println("It is not one of us.");
            return;
        }
        leader = student;
        notifyAllObservers(new ClassChangeNotifyEvent(student, this, EventEnum.BECOME_LEADER));
    }

    void appendMember(Student student) {
        students.add(student);
        notifyAllObservers(new ClassChangeNotifyEvent(student, this, EventEnum.JOIN_CLASS));
    }

    boolean isIn(Student student) {
        return student.getKlass().number == number;
    }

    private void notifyAllObservers(ClassChangeNotifyEvent event) {
        classChangeListeners.forEach(classChangeListener -> classChangeListener.notify(event));
    }

    void attachObserver(Teacher teacher) {
        classChangeListeners.add(teacher);
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
