
/**
 * Created by john on 01/03/2017.
 */

public class testExtendsClass {

    static class FatherClass{
        public FatherClass() {
            System.out.println("FatherClass");
        }

        public FatherClass(int i){
            System.out.println("FatherClass22");
        }
    }

    static class ChildClass extends FatherClass{
        public ChildClass() {
            super(3);
            System.out.println("ChildClass");
        }
    }

    public static void main(String[] args) {
        FatherClass fatherClass = new FatherClass();
        ChildClass childClass = new ChildClass();
    }
}
