package Other;
import Logic.Arm;
import Memories.*;
import Logic.ArmLine;
import Memories.Memory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * То что мы получаем от редактора: алгоритм программы и список(только список!) всех памятей.
 * Предварительно заполнить память пока нельзя!
 */
public class Storage {
    /**
     * Ключ - значение номера/названия вершины
     */
    HashMap<String, Arm> arms;
    /**
     * Ключ - название памяти(для вагона - полное название, например 'ЛВ*ПВ'
     */
    HashMap<String, Memory> memories;
    HashMap<String, Alphabet> alphabets;
    Tape tape;

    public Storage(HashMap<String, Arm> arms, HashMap<String, Memory> memories, HashMap<String, Alphabet> alphabets, Tape tape) {
        this.arms = arms;
        this.memories = memories;
        this.alphabets = alphabets;
        this.tape = tape;
    }
}
