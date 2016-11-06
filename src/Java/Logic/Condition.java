package Logic; /**
 * Created by Admin on 28.10.2016.
 */
import Memories.*;
import Other.*;

import java.util.Objects;


// Пока хз как без костылей реализовать множество вариантов в правой части условия,
// буду благодарен за подсказки. Из вариантов через свтитч по enum вариантов или заполнение двух неиспользуемых
// полей null-ами.
// ОБНОВЛЕНИЕ: предлагаю перегрузку функций. В правой части могут быть 3 типа данных: цепочка термов, память, синтерм
// (который по сути, подгружаемый массив термов) см. учебник стр. 45. Однако кое-где может и не быть правой части, так
// что задача, с одной стороны облегчается, с другой усложняется (множество вариантов проверки условия)

public class Condition {
    String endArmNumber;
    Alphabet alphabet;
    String text;
    Boolean aBoolean;

    public Condition(String text, Alphabet alphabet, String endArmNumber) {
        this.text = text;
        this.alphabet = alphabet;
        this.endArmNumber = endArmNumber;
    }

//  1. Объединяем первый два пункта условий из книги в один, так как первый является подмножеством второго: проверка
//     цепочки термов на соответствие с вимволами входной ленты.
    boolean compare(String str, Tape tape) {
        for(int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) == tape.read())){
                aBoolean = false;
                return aBoolean;
            }
        }
        aBoolean = true;
        return aBoolean;
    }

//  2. 3 пункт: сравниваем ленту ввода с памятью.
    boolean compare(Memory memory, Tape tape) {
        for(int i = 0; i < memory.read().length(); i++) {
            if (!(memory.read().charAt(i) == tape.read())) {
                aBoolean = false;
                return aBoolean;
            }
        }
        aBoolean = true;
        return aBoolean;
    }

//  3. Предикат синтерм: сверка текущего символа входной ленты с любым символом данного алфавита
    boolean compare(Alphabet alphabet, Tape tape) {
        char buf = tape.read();
        for (int i = 0; i < alphabet.read().length; i++) {
            if (alphabet.read()[i] == buf) {
                aBoolean = true;
                return aBoolean;
            }
        }
        aBoolean = false;
        return aBoolean;
    }

//  4. Предикат образец: по символу '!' происходит поиск элемента не схожего с теми, что описаны в операнде
    boolean compare(char ch, Memory memory, Tape tape) {
        if(ch == '!') {
            String str;
            int count = 0;
            for(int i = 0; i < memory.size(); i++) {
                if(memory.read().charAt(i) == tape.read()){}
                else {
                    aBoolean = false;
                    return aBoolean;
                }
            }
            aBoolean = true;
            return aBoolean;
        } else if (ch == '?') {
            String buf = null;
            while (buf != memory.read() || tape.read() != null) {
                char cursymbol = tape.read();
                int count = 0;
                for (int i = 0; i < tape.size(); i++) {
                    if(memory.read().charAt(i) != cursymbol) {}
                    else  {
                        buf += cursymbol;
                        break;
                    }
                }
                for(int j = 0; j < memory.size(); j++) {
                    if (memory.read().charAt(j) == (cursymbol = tape.read())) {
                        buf += cursymbol;
                    }
                    else break;
                }
            }
            aBoolean = true;
            return aBoolean;
        }
        System.out.println("Error. Wrong symbol-sign.");
        return false;
    }

    boolean compare(char ch, Alphabet alphabet, Tape tape) {
        if(ch == '!') {
            int i = 0;
            aBoolean = true;
            while(aBoolean) {
                char cursymbol = tape.read();
                while (alphabet.read()[i] != cursymbol && i < alphabet.read().length) {
                    i++;
                }
                if (i >= alphabet.read().length) aBoolean = false;
                aBoolean = true;
            }
            return !aBoolean;
        } else if (ch == '?') {
            int i = 0;
            aBoolean = true;
            while(aBoolean) {
                char cursymbol = tape.read();
                while (alphabet.read()[i] != cursymbol && i < alphabet.read().length) {
                    i++;
                }
                if (i >= alphabet.read().length) aBoolean = true;
                aBoolean = false;
            }
            aBoolean = true;
            while(aBoolean) {
                char cursymbol = tape.read();
                for(int k = 0; k < alphabet.read().length; k++) {
                    if(alphabet.read()[k] == cursymbol) {
                        break;
                    }
                }
                if(i >= alphabet.read().length) aBoolean = false;
                else aBoolean = true;
            }
            return !aBoolean;
        }
        System.out.println("Error. Wrong symbol-sign.");
        return (aBoolean = false);
    }

    boolean compare(Memory memoryleft, String oper, Memory memoryright) {
        switch (oper) {
            case "==":
                aBoolean = (Objects.equals(memoryleft.read(), memoryright.read()));
                break;
            case "!=":
                aBoolean = (!Objects.equals(memoryleft.read(), memoryright.read()));
                break;
            case  "<":
                aBoolean = (new Integer(memoryleft.read()) < new Integer(memoryright.read()));
                break;
            case "<=":
                aBoolean = (new Integer(memoryleft.read()) <= new Integer(memoryright.read()));
                break;
            case  ">":
                aBoolean = (new Integer(memoryleft.read()) > new Integer(memoryright.read()));
                break;
            case ">=":
                aBoolean = (new Integer(memoryleft.read()) >= new Integer(memoryright.read()));
                break;
        }
        return aBoolean;
    }
}
