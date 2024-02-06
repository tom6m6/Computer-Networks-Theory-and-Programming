public class TestProxy {
    public static void main(String[] args) {
// 构造⼀个PersonA对象
        PersonA personA = new PersonA();
// 构造⼀个代理，将personA作为参数传递进去
        IProxy proxy = new PersonB(personA);
// 由代理者来提交
        proxy.submit();
    }
}