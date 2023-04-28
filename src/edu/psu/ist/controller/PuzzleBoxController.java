package edu.psu.ist.controller;

import edu.psu.ist.model.PuzzleBox;
import edu.psu.ist.view.PuzzleBoxForm;
import edu.psu.ist.view.PuzzleBoxView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleBoxController {

    private PuzzleBox<Integer> model;
    private PuzzleBoxView view;

    public PuzzleBoxController(PuzzleBox model, PuzzleBoxView view) {
        this.model = model;
        this.view = view;

        PuzzleBoxForm form = view.getForm();

        //todo: make into method
        //default disables clear, sort, ordered buttons
        form.getClearButton().setEnabled(false);
        form.getSortButton().setEnabled(false);
        form.getDoubleOrderedButton().setEnabled(false);


        /**
         *  clear and add button controller
         */
        form.getClearAndAdd().addActionListener(e -> {
            String textFieldStr = form.getTextFieldStr().getText();
            if (textFieldStr.trim().isEmpty()){
                JOptionPane.showMessageDialog(view, "Enter items in the text field");
                return;
            }
            List<String> itemsToAdd = getTextFieldContents(textFieldStr);
            model.clear();
            model.addItemsTo(itemsToAdd);
            form.getNumOfItems().setText(model.numOfItems()+"");
            form.getClearButton().setEnabled(true);
            form.getSortButton().setEnabled(true);
            form.getDoubleOrderedButton().setEnabled(true);
        });
    }

    private List<String> getTextFieldContents(String textFieldStr) {
        List<String> result = new ArrayList<>();

        // want to split on one or more whitespace chars
        for (String s : textFieldStr.split("\\s+")) {
            s = s.trim(); // clean up the string, then verify (below)
            if (s.isEmpty() || s.isBlank() || s.contains(" ")) {
                JOptionPane.showMessageDialog(view,
                        "Invalid pattern: must be a " +
                                "whitespace delimited list");
                return new ArrayList<>();
            }
            result.add(s);
        }
        return result;
    }

}