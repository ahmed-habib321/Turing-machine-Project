/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TurnigMachine;

/**
 *
 * @author Ahmed
 */

/**
 * Represents a transition in a Turing machine.
 */
public class Transition {

    private final String currentState;
    private final char input;
    private final String newState;
    private final char newSymbol;
    private final char action;

    /**
     * Constructs a new Transition object.
     *
     * @param currentState the current state of the Turing machine
     * @param input the input character triggering the transition
     * @param newState the new state to transition to
     * @param newSymbol the new symbol to write on the tape
     * @param action the action to perform ('L' for left, 'R' for right, 'N' for reject
     * , 'Y' for accept)
     */
    public Transition(String currentState, char input, String newState, char newSymbol, char action) {
        this.currentState = currentState;
        this.input = input;
        this.newState = newState;
        this.newSymbol = newSymbol;
        this.action = action;
    }

    /**
     * Returns the current state of the Turing machine.
     *
     * @return the current state
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * Returns the input character triggering the transition.
     *
     * @return the input character
     */
    public char getInput() {
        return input;
    }

    /**
     * Returns the new state to transition to.
     *
     * @return the new state
     */
    public String getNewState() {
        return newState;
    }

    /**
     * Returns the new symbol to write on the tape.
     *
     * @return the new symbol
     */
    public char getNewSymbol() {
        return newSymbol;
    }

    /**
     * Returns the action to perform.
     *
     * @return the action ('L' for left, 'R' for right, 'N' for no movement)
     */
    public char getAction() {
        return action;
    }
}
