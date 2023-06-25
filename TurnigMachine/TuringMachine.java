/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TurnigMachine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed
 */
/**
 * Represents a Turing machine.
 */
public class TuringMachine {

    private final String initialState;
    private String currentState;
    private int headPosition;
    private final List<Transition> transitionFunction = new ArrayList<>();
    private List<Character> tape = new ArrayList<>();
    private boolean showSteps;

    /**
     * Constructs a new TuringMachine object with the specified initial state.
     *
     * @param initialState the initial state of the Turing machine
     */
    public TuringMachine(String initialState) {
        this.initialState = initialState;
        this.currentState = initialState;
        this.headPosition = 0;
    }

    /**
     * Checks if the transition function is empty.
     *
     * @return true if the transition function is empty, false otherwise
     */
    public boolean isTransitionFunctionEmpty() {
        return transitionFunction.isEmpty();
    }

    /**
     * Resets the Turing machine's settings to the initial state and head
     * position.
     */
    public void resetSettings() {
        this.headPosition = 0;
        this.currentState = initialState;
    }

    /**
     * Extends the tape by adding a '#' symbol if needed based on the current
     * head position.
     */
    private void extendTape() {
        try {
            getCurrentTransition();
        } catch (Exception e) {
            tape.add('#');
        }
    }

    /**
     * Checks the tape starting from the specified max head position to ensure
     * there are no symbols beyond it.
     *
     * @param maxHeadPosition the maximum position the head reached
     * @return an array containing the result of the check (true if the tape is
     * valid, false otherwise) and the index of the first invalid symbol (-1 if
     * the tape is valid)
     */
    private Object[] checkTape(int maxHeadPosition) {
        Object[] output = new Object[2];
        output[0] = true;
        int headTemp = maxHeadPosition + 1;
        while (headTemp < tape.size()) {
            if (tape.get(headTemp) != '#') {
                output[0] = false;
                output[1] = headTemp;
                break;
            }
            headTemp++;
        }
        return output;
    }

    /**
     * Sets the tape of the Turing machine.
     *
     * @param tape the tape as a string
     */
    public void setTape(String tape) {
        this.tape = strToList(tape);
    }

    /**
     * Converts a string to a list of characters.
     *
     * @param input the input string
     * @return the list of characters
     */
    private List<Character> strToList(String input) {
        char[] charArray = input.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : charArray) {
            list.add(c);
        }
        return list;
    }

    /**
     * Sets whether to show the steps of the Turing machine during execution.
     *
     * @param showSteps true to show steps, false otherwise
     */
    public void setShowSteps(boolean showSteps) {
        this.showSteps = showSteps;
    }

    /**
     * Adds a transition to the transition function.
     *
     * @param transition the transition to add
     */
    public void addTransition(Transition transition) {
        transitionFunction.add(transition);
    }

    /**
     * Retrieves the current transition based on the current state and input
     * symbol.
     *
     * @return the current transition, or null if no valid transition is found
     */
    public Transition getCurrentTransition() {
        Transition transition = null;
        for (Transition t : transitionFunction) {
            if (t.getCurrentState().equals(currentState) && t.getInput() == tape.get(headPosition)) {
                transition = t;
                break;
            }
        }
        return transition;
    }

    /**
     * Returns the current tape as a string.
     *
     * @return the tape as a string
     */
    public String getTape() {
        StringBuilder tapeString = new StringBuilder();
        for (char c : tape) {
            tapeString.append(c);
        }
        return tapeString.toString();
    }

    /**
     * Skips the start mark ('&lt;') on the tape if it is the first symbol.
     */
    private void skipStartMark() {
        if (headPosition == 0 && tape.get(headPosition) == '<') {
            headPosition++;
        }
    }

    /**
     * Displays the transition function.
     */
    public void displayTransitionFunction() {
        int i = 1;
        for (Transition t : transitionFunction) {
            System.out.println(i + "- (" + t.getCurrentState() + "," + t.getInput() + ") -->"
                    + " (" + t.getNewState() + "," + t.getNewSymbol() + "," + t.getAction() + ")"
            );
            i++;
        }
    }

    /**
     * Replaces a transition in the transition function at the specified index.
     *
     * @param newTransition the new transition
     * @param index the index at which to replace the transition
     */
    void replaceTransition(Transition newTransition, int index) {
        transitionFunction.add(index - 1, newTransition);
    }

    /**
     * Runs the Turing machine.
     */
    public void run() {
        skipStartMark();
        Transition currentTransition = getCurrentTransition();
        String result = "";
        int maxHeadPosition = headPosition;
        OUTER:
        while (headPosition < tape.size()) {
            if (headPosition > maxHeadPosition) {
                maxHeadPosition = headPosition;
            }
            if (currentTransition == null) {
                System.out.println("\033[1;31m" + "No valid transition found for state " + currentState
                        + " and symbol " + tape.get(headPosition));
                System.out.println("\033[1;31m" + "Crash");
                return;
            }
            if (showSteps) {
                System.out.println("Head Position: " + headPosition);
                System.out.println("( " + currentState + " , " + getTape() + " )" + "-->"
                        + "( " + currentTransition.getNewState() + " , "
                        + currentTransition.getNewSymbol() + " , " + currentTransition.getAction() + " )"
                );
            }
            currentState = currentTransition.getNewState();
            tape.set(headPosition, currentTransition.getNewSymbol());
            switch (currentTransition.getAction()) {
                case 'R':
                    headPosition++;
                    break;
                case 'L':
                    headPosition--;
                    break;
                case 'Y':
                    Object[] check = checkTape(maxHeadPosition);
                    if ((boolean) check[0]) {
                        result = "\033[2;32m" + "String Accepted";
                    } else {
                        System.out.println("The tape has a problem in index " + check[1]
                                + " please check your inputs");
                        return;
                    }
                    break OUTER;
                case 'N':
                    result = "\033[1;31m" + "String Rejected";
                    break OUTER;
                default:
                    System.out.println("Bad action input");
                    break OUTER;
            }
            extendTape();
            currentTransition = getCurrentTransition();
        }
        tape.add('#');
        System.out.println("Final Tape: " + getTape());
        System.out.println("Head Index: " + headPosition);
        System.out.println("Output:     " + result);
    }

}
