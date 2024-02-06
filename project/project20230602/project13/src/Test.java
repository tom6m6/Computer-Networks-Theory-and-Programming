import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
public class Test {
    public static void main(String[] args) {
// 创建被代理的实例对象
        PersonA personA = new PersonA();
// 创建invocationHandler对象
        InvocationHandler invocationHandler = new DynamicProxy(personA);
// 获取 personA 的类加载器
        ClassLoader loader = personA.getClass().getClassLoader();
// 通过Proxy.newProxyInstance(loader, interfaces, h)创建代理对象，三个参数：
// loader:⽤哪个类加载器去加载代理对象
// interfaces:动态代理类需要实现的接⼝
// h:动态代理⽅法在执⾏时，会调⽤h⾥⾯的invoke⽅法去执⾏
        IProxy personProxy = (IProxy) Proxy.newProxyInstance(loader,
                personA.getClass().getInterfaces(), invocationHandler);
// 代理对象的每个执⾏⽅法都会替换执⾏InvocationHandler中的invoke⽅法
        personProxy.submit();
    }
}