package focus.focusstepone.focustest.highquality;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * focus Create in 16:23 2018/8/22
 */
public class ComparatorTest {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>(5);
        list.add(new Employee(1001,"老板",Position.BOSS));
        list.add(new Employee(1006,"经理A",Position.MANAGER));
        list.add(new Employee(1003,"经理B",Position.MANAGER));
        list.add(new Employee(1002,"员工A",Position.STAFF));
        list.add(new Employee(1005,"员工B",Position.STAFF));

        Collections.sort(list);
        for (Employee e : list) {
            System.out.println(e);
        }

        System.out.println("****************************");
        Collections.sort(list, new PositionComparator());
        for (Employee e : list) {
            System.out.println(e);
        }
    }
}

class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private Position position;
    public Employee(int _id, String _name, Position _position) {
        this.id = _id;
        this.name = _name;
        this.position = _position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int compareTo(Employee e) {
        return new CompareToBuilder().append(id, e.getId()).toComparison();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

enum Position {
    BOSS, MANAGER, STAFF
}

class PositionComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return new CompareToBuilder().append(e1.getPosition(), e2.getPosition()).append(e1.getId(), e2.getId()).toComparison();
    }
}
