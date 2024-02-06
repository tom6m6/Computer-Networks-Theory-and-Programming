// 编写类PersonB
class PersonB implements IProxy {
    //被代理者的引⽤
    private IProxy m_Person;
    PersonB(IProxy person) {
        m_Person = person;
    }
    @Override
    public void submit() {
        before();
        m_Person.submit();
    }
    public void before() {
        System.out.println("PersonB加上抬头");
    }
}