package Other;

import Memories.Register;
import Memories.Wagon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Admin on 28.10.2016.
 * Просто прогон всех конструкторов, чтений и тп
 */
public class TestClass {
    public static void main(String[] args) {
        Register register = new Register("R1","Val1");
        Wagon wagon = new Wagon("ЛВ","ПВ", new ArrayList<>(Arrays.asList("1","2","3")));
        System.out.println(wagon);
        System.out.println(register);
    }
}
