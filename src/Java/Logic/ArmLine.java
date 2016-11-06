package Logic;

import java.util.ArrayList;

/**
 * Created by Admin on 28.10.2016.
 */
public class ArmLine {
    public ArmLine(String currentArmNumber, Condition condition, ArrayList<Statement> statements) {
        this.currentArmNumber = currentArmNumber;
        this.condition = condition;
        this.statements = statements;
    }

    String currentArmNumber;
    Condition condition;
    ArrayList<Statement> statements;

}
