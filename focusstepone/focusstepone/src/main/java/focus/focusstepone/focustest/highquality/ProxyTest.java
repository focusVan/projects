package focus.focusstepone.focustest.highquality;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * focus Create in 17:23 2018/8/23
 */
public class ProxyTest {
    public static void main(String[] args) {
        new Proxy().request();


        //具体主题角色
        Subject subject = new RealSubject();
        //代理实例的处理handler
        InvocationHandler invocationHandler = new SubjectHandler(subject);
        //当前加载器
        ClassLoader classLoader = subject.getClass().getClassLoader();
        //动态代理
        Subject proxy = (Subject) java.lang.reflect.Proxy.newProxyInstance(classLoader, subject.getClass().getInterfaces(), invocationHandler);
        //执行具体主题角色方法
        proxy.request();
    }
}

//抽象主题角色
interface Subject {
    public void request();
}

//具体主题角色
class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real subject");
    }
}

//静态代理................
//代理主题角色
class Proxy implements Subject {
    //要代理哪个实现类
    private Subject subject = null;
    //默认被代理者
    public Proxy() {
        subject = new RealSubject();
    }
    //通过构造函数传递被代理者
    public Proxy(Subject subject) {
        this.subject = subject;
    }
    //实现接口中定义的方法
    @Override
    public void request() {
        before();
        this.subject.request();
        after();
    }
    //预处理
    private void before() {
        System.out.println("before....");
    }
    //善后处理
    private void after() {
        System.out.println("after...");
    }
}


//动态代理
class  SubjectHandler implements InvocationHandler {
    //被代理对象
    private Subject subject;
    public SubjectHandler(Subject subject) {
        this.subject = subject;
    }

    //委托处理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        //预处理
        System.out.println("预处理...");
        //直接调用被代理类的方法
        Object obj = method.invoke(subject, args);
        //善后处理
        System.out.println("善后...");
        return obj;
    }
}

