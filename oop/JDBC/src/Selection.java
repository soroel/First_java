import java.awt.*;
import java.awt.event.*;

public class Selection extends Frame {
    private Choice selectionList;

    public Selection() {
        setLayout(new FlowLayout());

        selectionList = new Choice();
        selectionList.addItem("School");
        selectionList.addItem("Diploma");
        selectionList.addItem("Graduate");
        selectionList.addItem("Masters");
        selectionList.addItem("PhD");
        add(selectionList);

        setTitle("Selection List Example");
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Selection();
    }
}
