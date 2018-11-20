package com.focustest.highquality;

/**
 * focus Create in 10:06 2018/8/23
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Role.BOSS.getTitle() + " id is : " + Role.BOSS.getId());
    }
}

enum Role {
    BOSS(1,"A","boss"),
    MANAGER(2,"B","manager"),
    STAFF(3,"C","staff");

    private int id;
    private String name;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    Role (int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }
}
