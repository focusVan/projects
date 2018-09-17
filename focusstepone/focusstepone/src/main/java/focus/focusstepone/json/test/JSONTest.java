package focus.focusstepone.json.test;

import com.alibaba.fastjson.JSONArray;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * focus Create in 15:33 2018/9/13
 */
public class JSONTest {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        Person person = new Person("zhangsan", 11);
        System.out.println(person);

        set.add(person);
        System.out.println(set.toString());

        List<Person> list = JSONArray.parseArray(set.toString(), Person.class);
        System.out.println(list.toString());
    }
}
