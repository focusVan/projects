package focus.focusstepone.focustest.highquality;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * focus Create in 9:55 2018/8/24
 */
public class TemplateMethodPatternTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        AbsPopulator absPopulator = new UserPopulator();
        absPopulator.dataInitialing();

        ReflectedAbsPopulator reflectedAbsPopulator = new ReflectedUserPopulator();
        reflectedAbsPopulator.dataInitialing();
    }
}

/**
 * 传统模板方法模式示例
 */
abstract class AbsPopulator {
    //模板方法
    public final void  dataInitialing() {
        //调用基本方法
        initName();
        initOther();
    }
    //基本方法
    protected abstract void initName();
    protected abstract void initOther();
}


class UserPopulator extends AbsPopulator {
    @Override
    protected void initName() {
        System.out.println("init name...");
    }
    @Override
    protected void initOther() {
        System.out.println("init other...");
    }
}

/**
 * 反射加强版模板方法模式
 */
abstract class ReflectedAbsPopulator {
    //模板方法
    public final void dataInitialing() throws InvocationTargetException, IllegalAccessException {
        //获得所有public方法
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            //判断是否为数据初始化方法
            if (isInitiaMethod(method)) {
                method.invoke(this);
            }
        }

    }
    private boolean isInitiaMethod(Method method) {
        return method.getName().startsWith("init")
                && Modifier.isPublic(method.getModifiers())
                && method.getReturnType().equals(Void.TYPE)
                && !method.isVarArgs() //输入参数为空
                && !Modifier.isAbstract(method.getModifiers());
    }
}

class ReflectedUserPopulator extends ReflectedAbsPopulator{
    public void initName() {
        System.out.println("initName...");
    }
    public void initAge() {
        System.out.println("initAge...");
    }
    public void otherMethod() {
        System.out.println("other method....");
    }
}