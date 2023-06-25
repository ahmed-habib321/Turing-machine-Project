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
 * A ready-to-use Turing Machine implementations.
 */
public class readyTMs {

    /**
     * Turing Machine for transforming "&lt;abab" into "&lt;A###". The string is a
     * concatenation of similar sub strings: abab / abaaba / aa.
     */
    public static class TuringMachine1 extends TuringMachine {

        public TuringMachine1() {
            super("0");
            addTransition(new Transition("0", 'a', "1", 'A', 'R'));
            addTransition(new Transition("0", 'b', "1", 'B', 'R'));
            addTransition(new Transition("0", 'A', "4", 'A', 'L'));
            addTransition(new Transition("0", 'B', "4", 'B', 'L'));
            addTransition(new Transition("0", '#', "0", '#', 'Y'));

            addTransition(new Transition("1", 'a', "1", 'a', 'R'));
            addTransition(new Transition("1", 'b', "1", 'b', 'R'));
            addTransition(new Transition("1", 'A', "2", 'A', 'L'));
            addTransition(new Transition("1", 'B', "2", 'B', 'L'));
            addTransition(new Transition("1", '#', "2", '#', 'L'));

            addTransition(new Transition("2", 'a', "3", 'A', 'L'));
            addTransition(new Transition("2", 'b', "3", 'B', 'L'));

            addTransition(new Transition("3", 'a', "3", 'a', 'L'));
            addTransition(new Transition("3", 'b', "3", 'b', 'L'));
            addTransition(new Transition("3", 'A', "0", 'A', 'R'));
            addTransition(new Transition("3", 'B', "0", 'B', 'R'));

            addTransition(new Transition("4", 'A', "4", 'a', 'L'));
            addTransition(new Transition("4", 'B', "4", 'b', 'L'));
            addTransition(new Transition("4", '<', "5", '<', 'R'));

            addTransition(new Transition("5", 'a', "7", 'A', 'R'));
            addTransition(new Transition("5", 'b', "6", 'B', 'R'));
            addTransition(new Transition("5", '#', "5", '#', 'Y'));

            addTransition(new Transition("6", 'a', "6", 'a', 'R'));
            addTransition(new Transition("6", 'b', "6", 'b', 'R'));
            addTransition(new Transition("6", 'B', "8", '#', 'L'));
            addTransition(new Transition("6", '#', "6", '#', 'R'));

            addTransition(new Transition("7", 'a', "7", 'a', 'R'));
            addTransition(new Transition("7", 'b', "7", 'b', 'R'));
            addTransition(new Transition("7", 'A', "8", '#', 'L'));
            addTransition(new Transition("7", '#', "7", '#', 'R'));

            addTransition(new Transition("8", 'a', "8", 'a', 'L'));
            addTransition(new Transition("8", 'b', "8", 'b', 'L'));
            addTransition(new Transition("8", 'A', "5", 'A', 'R'));
            addTransition(new Transition("8", 'B', "5", 'B', 'R'));
            addTransition(new Transition("8", '#', "8", '#', 'L'));
        }
    }

    /**
     * Turing Machine for transforming "#ab" into "##abba#". "#A" is transformed
     * into "##A(A^R)#".
     */
    public static class TuringMachine2 extends TuringMachine {

        public TuringMachine2() {
            super("0");
            addTransition(new Transition("0", '#', "1", '#', 'R'));

            addTransition(new Transition("1", 'a', "1", 'a', 'R'));
            addTransition(new Transition("1", 'b', "1", 'b', 'R'));
            addTransition(new Transition("1", '#', "2", '#', 'L'));

            addTransition(new Transition("2", 'a', "3", '#', 'R'));
            addTransition(new Transition("2", 'b', "5", '#', 'R'));
            addTransition(new Transition("2", '#', "3", '#', 'Y'));

            addTransition(new Transition("3", '#', "4", 'a', 'R'));

            addTransition(new Transition("4", 'a', "4", 'a', 'R'));
            addTransition(new Transition("4", 'b', "4", 'b', 'R'));
            addTransition(new Transition("4", '#', "7", 'a', 'L'));

            addTransition(new Transition("5", '#', "6", 'b', 'R'));

            addTransition(new Transition("6", 'a', "6", 'a', 'R'));
            addTransition(new Transition("6", 'b', "6", 'b', 'R'));
            addTransition(new Transition("6", '#', "7", 'b', 'L'));

            addTransition(new Transition("7", 'a', "7", 'a', 'L'));
            addTransition(new Transition("7", 'b', "7", 'b', 'L'));
            addTransition(new Transition("7", '#', "2", '#', 'L'));
        }
    }

    /**
     * Turing Machine for unary addition.
     */
    public static class TuringMachine3 extends TuringMachine {

        public TuringMachine3() {
            super("0");
            addTransition(new Transition("0", '1', "0", '1', 'R'));
            addTransition(new Transition("0", '#', "1", '1', 'R'));

            addTransition(new Transition("1", '1', "1", '1', 'R'));
            addTransition(new Transition("1", '#', "2", '#', 'L'));

            addTransition(new Transition("2", '1', "2", '#', 'Y'));
            addTransition(new Transition("2", '#', "2", '#', 'N'));
        }
    }

}
