package practice;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Assistant extends Teacher {
    public Assistant(int id, String name, int age) {
        super(id, name, age);
    }

    public Assistant(int id, String name, int age, LinkedList<Klass> linkedList) {
        super(id, name, age, linkedList);
    }

    @Override
    public String introduce() {
        return basicIntroduce() + getAssistInfo();
    }

    private String getAssistInfo() {
        return " I assist" + (getClasses().isEmpty()
                ? " No Class"
                : " Class " + getClassesString()) + ".";
    }

    private String basicIntroduce() {
        return super.rawIntroduce() + " I am a Assistant.";
    }

    public String getClassesLeader() {
        return basicIntroduce() + getAssistLeaderInfo();
    }

    private String getAssistLeaderInfo() {
        return getClasses().isEmpty()
                ? " No class need assist now."
                : " I will assist these leader: " + getClassesLeaderString();
    }

    private String getClassesLeaderString() {
        return getClasses().stream()
                .map(clazz ->
                        clazz.getDisplayName() + ": " + (clazz.getLeader() == null ? "No Leader now"
                                : clazz.getLeader().getName()))
                .collect(Collectors.joining(", ", "", "."));
    }

}
