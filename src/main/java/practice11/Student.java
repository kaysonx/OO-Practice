package practice11;

public class Student extends Person {
    private Klass klass;

    public Student(int id, String name, int age, Klass klass) {
        super(id, name, age);
        this.klass = klass;
    }

    @Override
    public String introduce() {
        return basicIntroduce() + (isLeader()
                        ? (" I am Leader of " + klass.getDisplayName() + ".")
                        : (" I am at " + klass.getDisplayName() + "."));
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
}
