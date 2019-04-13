package practice.event;

import practice.Klass;
import practice.Student;

public class ClassChangeNotifyEvent {
    private Student student;
    private Klass klass;
    private EventEnum eventType;

    public ClassChangeNotifyEvent(Student student, Klass klass, EventEnum eventType) {
        this.student = student;
        this.klass = klass;
        this.eventType = eventType;
    }

    public Student getStudent() {
        return student;
    }

    public Klass getKlass() {
        return klass;
    }

    public EventEnum getEventType() {
        return eventType;
    }
}
