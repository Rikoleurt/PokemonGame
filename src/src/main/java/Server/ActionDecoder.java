package Server;

import Model.Person.Action;

import java.io.IOException;

public class ActionDecoder {

    public ActionDecoder() {}

    public Action getActionFromMessage(String message) throws IOException {
        int actionIndex = Integer.parseInt(message);
        if(actionIndex <= 3) return Action.Attack;
        else if (actionIndex == 4) return Action.Switch;
        else if (actionIndex == 5) return Action.Item;
        else {
            System.out.println("Unknown Action" + actionIndex);
            return null;
        }
    }
}
