package Other;
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
    HashMap<String,ArrayList<ArmLine>> arms;
    /**
     * Ключ - название памяти(для вагона - полное название, например 'ЛВ*ПВ'
     */
    //TODO:Реализовать пустые конструкторы для всех памятей(только с названиями)
    HashMap<String, Memory> memories;
    //TODO: Реализовать алфавит(работа с регулярными выражениями) ОБНОВЛЕНИЕ: Алфавит реализован в виде памяти
    HashMap<String,Alphabet> alphabets;


}
