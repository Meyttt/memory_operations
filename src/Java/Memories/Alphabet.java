package Memories;
import Logic.*;
import javax.lang.model.type.NullType;

/**
 * Created by Anton on 05.11.2016.
 */
public class Alphabet {
    private char [] alphabet;
    private String fullname;
    private String name;

    public char[] read() {
        return alphabet;
    }

    public String getName() {
        return name;
    }

    public String getFullname() {
        return fullname;
    }

    public Alphabet(String fullname, String name, char [] alphabet) {
        this.fullname = fullname;
        this.name = name;
        this.alphabet = alphabet;
    }
    //заглушка
    public Alphabet(String fullName){
        this.fullname=fullName;
        this.alphabet=null;
        this.name=null;
    }
}
