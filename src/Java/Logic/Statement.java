package Logic;

import Memories.Memory;

/**
 * Левый аргумент может быть только памятью(надеюсь, это так?..)
 * Правый - памятью или выражением. Проблема аналогичная с алафвитом.
 */
public class Statement {
    String leftArg;
    Operator operator;
    String rightArg;
    public static Operator getOperator(char[] chars){
        switch (chars.length){
            case(3):
                if(chars[0]=='/'){
                    return new Operator(chars[0],(""+chars[1]+chars[2]).toCharArray());
                }else
                    return new Operator((""+chars[0]+chars[1]).toCharArray(),chars[2]);
            case (4):
                return new Operator(chars[0],(""+chars[1]+chars[2]).toCharArray(),chars[3]);
            case (2):
                return new Operator(chars);
            default:
                return null;

        }
    }
    /**
     * У нас есть 2 базовых вида выражений: чтение/запись (<-, ->), для каждого из которых можно задать очистку ячейки памяти.
     * Т.о., у нас есть три основных поля для этого варианта (очистка левого операнда, стрелка, очистка правого операнда)
     * Для второго варианта(=^ вставка, =. добавление,&= поиск по совпадению, &~ поиск по несовпадению) нам нужно только одно поле, поэтому 1 и 3 заполняются null-ами.
     * ОБНОВЛЕНИЕ: надо, по идее, еще учитывать такие операторы как ==, !=, <, <=, >, >=, они, как и &= и &~ становятся в одно поле.
     */
    public static class Operator{
        Character left;
        char[] middle;
        Character right;

        private Operator(char ch1, char[] ch2, char ch3){
            left = ch1;
            middle = ch2;
            right=ch3;
        }
        private Operator(char ch1, char[] ch2){
            left = ch1;
            middle = ch2;
            right=null;
        }
        private Operator(char[] ch2, char ch3){
            left = null;
            middle = ch2;
            right=ch3;

        }
        private Operator(char[] ch2){
            left = null;
            middle = ch2;
            right=null;
        }
    }
    public static class RightSide{
        String stringRight;
        Memory memoryRight;
        RightSide(String text){
            stringRight = text;
            memoryRight=null;
        }
        RightSide(Memory rightSide){
            stringRight=null;
            memoryRight=rightSide;
        }
    }
    public Statement(String leftArg, Operator operator, String rightArg){
        this.leftArg=leftArg;
        this.operator=operator;
        this.rightArg=rightArg;
    }

//    public static void main(String[] args) {
//        Logic.Statement statement=new Logic.Statement(new Memories.Wagon("ЛВ","ПВ",new ArrayList<String>(Arrays.asList("first,Second"))),new Operator("<-".toCharArray()),new Memories.Register(""));
//
//    }
}
