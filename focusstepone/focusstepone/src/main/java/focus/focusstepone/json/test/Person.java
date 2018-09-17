package focus.focusstepone.json.test;

/**
 * focus Create in 15:32 2018/9/13
 */
public class Person {
    public Person(){}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" + "\"" + "name" + "\":" + "\"" + this.name + "\"" + "," + "\"" + "age" + "\":" + this.age + "}";
    }
}
