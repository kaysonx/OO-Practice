package practice;

import practice.event.ClassChangeListener;
import practice.event.ClassChangeNotifyEvent;
import practice.event.EventEnum;

public class Student extends Person implements ClassChangeListener {
    private Klass klass;

    public Student(int id, String name, int age, Klass klass) {
        super(id, name, age);
        this.klass = klass;
        klass.attachObserver(this);
    }

    @Override
    public String introduce() {
        return basicIntroduce() + getLeaderInfo();
    }

    private String getLeaderInfo() {
        return isLeader()
                ? (" I am Leader of " + klass.getDisplayName() + ".")
                : (" I am at " + klass.getDisplayName() + ".");
    }

    private boolean isLeader() {
        return klass.getLeader() != null && klass.getLeader().getId() == this.getId();
    }

    private String basicIntroduce() {
        return super.introduce() + " I am a Student.";
    }

    public Klass getKlass() {
        return klass;
    }

    @Override
    public void notify(ClassChangeNotifyEvent event) {
        if (event.getKlass().getNumber() != klass.getNumber()) {
            return;
        }

        if (event.getEventType().equals(EventEnum.BECOME_LEADER)) {
            if (event.getStudent().equals(this)) {
                System.out.println(basicIntroduce() + " I've become the Leader of " + klass.getDisplayName() + ".");
            } else {
                System.out.printf(basicIntroduce() + " I know that %s has become the Leader of %s.%n",
                        event.getStudent().getName(),
                        event.getKlass().getDisplayName());
            }
        }
    }
}
