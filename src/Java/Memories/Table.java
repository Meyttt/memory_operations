package Memories;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by master on 24.10.2016.
 */
public class Table implements Memory {
    private String tname;
    ArrayList<ArrayList<String>> table = new ArrayList<>();
    ArrayList<String> colnames = new ArrayList<>();
    private int strnumber;
    private int colnumber;
//    private int columns;

    public Table(String tname, ArrayList<ArrayList<String>> table, ArrayList<String> colnames) {
        this.tname = tname;
        this.table = table;
        this.colnames = colnames;
    }

    public String getName() {
        return tname;
    }

    public int getColumns() {
        return colnames.size();
    }

    public int getStrNumber() {
        return strnumber;
    }

    public int getColNumber() {
        return colnumber;
    }

    ArrayList getString(int num) {
        return this.table.get(num);
    }

    public int size() { return this.table.size(); }

    public String read(String... args) {
        return this.table.get(strnumber).get(colnumber);
    }

    @Override
    public boolean write(String... args) {
        this.table.get(strnumber).add(colnumber, args[0]);
        return (this.table.get(strnumber).get(colnumber) != null);
    }

    @Override
    public boolean clear() {
        table.clear();
        return table.isEmpty();
    }

    public boolean write(String value) {
        this.table.get(strnumber).add(colnumber, value);
        return (this.table.get(strnumber).get(colnumber) != null);
    }

    boolean searchTrue(String value) {
        this.write();

        int i, j = 0;
        for (i = 0; i < this.table.size(); i++)
            for (j = 0; j < this.table.get(i).size(); j++)
                if (Objects.equals(this.table.get(i).get(j), value)) {
                    this.strnumber = i;
                    this.colnumber = j;
                    break;
                }
        return !(i == (this.table.size() - 1) && j == this.table.get(i).size() && this.strnumber != i && this.colnumber != j);
    }

    boolean searchTrue(int searchcolumn, String value) {
        int i;
        for (i = 0; i < this.table.size(); i++)
            if (Objects.equals(this.table.get(i).get(searchcolumn), value)) {
                this.strnumber = i;
                this.colnumber = searchcolumn;
                break;
            }
        return !(i == (this.table.size() - 1) && this.strnumber != i);
    }

    boolean searchFalse(String value) {
        int i, j = 0;
        for (i = 0; i < this.table.size(); i++)
            for (j = 0; j < this.table.get(i).size(); j++)
                if (Objects.equals(this.table.get(i).get(j), value)) {
                    this.strnumber = i;
                    this.colnumber = j;
                    break;
                }
        return i == (this.table.size() - 1) && j == this.table.get(i).size() && this.strnumber != i && this.colnumber != j;
    }

    boolean searchFalse(int searchcolumn, String value) {
        int i;
        for (i = 0; i < this.table.size(); i++)
            if (Objects.equals(this.table.get(i).get(searchcolumn), value)) {
                this.strnumber = i;
                this.colnumber = searchcolumn;
                break;
            }
        return i == (this.table.size() - 1) && this.strnumber != i;
    }

    public String toString(){
        return "Table name: "+tname+"; value: "+table.toString();
    }
}
