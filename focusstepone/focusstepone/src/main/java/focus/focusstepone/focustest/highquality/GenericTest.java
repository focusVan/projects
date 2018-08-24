package focus.focusstepone.focustest.highquality;

import java.util.ArrayList;
import java.util.List;

/**
 * focus Create in 15:14 2018/8/23
 */
public class GenericTest {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        System.out.println(stringList.getClass());
        System.out.println(integerList.getClass());

    }
    public void arrayMethod(String[] strArray){}
    public void arrayMethod(Integer[] intArray){}

    // both method have save erasure
    //public void listMethod(List<String> stringList){}
    //public void listMethod(List<Integer> intList){}
}

class Foo<E>{
    private List<E> list = new ArrayList<>();
    private Number read(List<? extends Number> list) {
        return list.get(0);
    }

    private void write(List<? super Number> list) {
        list.add(12);
    }
}
