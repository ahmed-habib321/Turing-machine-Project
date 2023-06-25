/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TurnigMachine;

import java.util.InputMismatchException;
import java.util.Scanner;
import TurnigMachine.readyTMs.*;

/**
 *
 * @author Ahmed
 */

/**
 * The Main class provides a command-line interface to interact with different Turing Machines.
 * It allows the user to create a new Turing Machine or select from pre-defined options.
 * The user can add transitions, edit existing transitions, display the transition function,
 * and run the Turing Machine on custom inputs.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice = 0;
        TuringMachine turingMachine = null;
        
        // Display Turing Machine options
        System.out.println("Turing Machine Options:");
        System.out.println("0 - Exit");
        System.out.println("1 - Create a new Turing Machine");
        System.out.println("2 - Unary Addition: 111#111 --> 111111##");
        System.out.println("3 - Identical Substring: <abab --> <A###");
        System.out.println("4 - Palindrome: #A --> ##A(A^R)#");
        System.out.print("Enter the option number: ");
        int turingMachineOption = scanner.nextInt();
        
        // Select and initialize the chosen Turing Machine
        switch (turingMachineOption) {
            case 0:
                System.exit(0);
            case 1:
                System.out.println("Enter the initial state: ");
                String initialState = scanner.next();
                turingMachine = new TuringMachine(initialState);
                break;
            case 2:
                turingMachine = new TuringMachine3();
                break;
            case 3:
                turingMachine = new TuringMachine1();
                break;
            case 4:
                turingMachine = new TuringMachine2();
                break;
            default:
                System.out.println("Option not found");
                System.exit(0);
        }
        
        // Perform actions based on user input
        do {
            try {
                // Display available options based on the current Turing Machine
                
                System.out.println("\u001B[41m" + "0 - Exit ");
                System.out.println("\u001B[42m" + "1 - Add Transition ");

                if (!turingMachine.isTransitionFunctionEmpty()) {
                    System.out.println("\u001B[43m" + "2 - Edit Transition ");
                    System.out.println("\u001B[44m" + "3 - Display Transition Function ");
                    System.out.println("\u001B[45m" + "4 - Run ");
                }
                System.out.print("\u001B[40m");
                System.out.print("Enter your option number: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        choice = 'y';
                        break;
                    case 1:
                        // Add a new transition to the Turing Machine
                        char addTransitionChoice;
                        do {
                            System.out.print("Enter the current state name: ");
                            String currentState = scanner.next();
                            System.out.print("Enter the current symbol: ");
                            char currentSymbol = scanner.next().charAt(0);
                            System.out.print("Enter the new state name: ");
                            String newState = scanner.next();
                            System.out.print("Enter the new symbol: ");
                            char newSymbol = scanner.next().charAt(0);
                            System.out.print("Enter the action (R:L:Y:N): ");
                            char action = scanner.next().toUpperCase().charAt(0);
                            turingMachine.addTransition(new Transition(currentState, currentSymbol, newState, newSymbol, action));
                            System.out.print("Do you want to add another transition? (Y:N): ");
                            addTransitionChoice = scanner.next().toUpperCase().charAt(0);
                        } while (addTransitionChoice == 'Y');
                        break;
                    case 2:
                        if (!turingMachine.isTransitionFunctionEmpty()) {
                            // Edit an existing transition in the Turing Machine
                            char editTransitionChoice;
                            do {
                                turingMachine.displayTransitionFunction();
                                System.out.print("Enter the transition number you want to change: ");
                                int transitionNumber = scanner.nextInt();
                                System.out.print("Enter the current state name: ");
                                String currentState = scanner.next();
                                System.out.print("Enter the current symbol: ");
                                char currentSymbol = scanner.next().charAt(0);
                                System.out.print("Enter the new state name: ");
                                String newState = scanner.next();
                                System.out.print("Enter the new symbol: ");
                                char newSymbol = scanner.next().charAt(0);
                                System.out.print("Enter the action (R:L:Y:N): ");
                                char action = scanner.next().toUpperCase().charAt(0);
                                turingMachine.replaceTransition(new Transition(currentState, currentSymbol, newState, newSymbol, action), transitionNumber);
                                turingMachine.displayTransitionFunction();
                                System.out.print("Do you want to change more transitions? (Y:N): ");
                                editTransitionChoice = scanner.next().toUpperCase().charAt(0);
                            } while (editTransitionChoice == 'Y');
                        }
                        break;
                        
                    case 3:
                        if (!turingMachine.isTransitionFunctionEmpty()) {
                            // Display the transition function of the Turing Machine
                            turingMachine.displayTransitionFunction();
                        }
                        break;
                    case 4:
                        if (!turingMachine.isTransitionFunctionEmpty()) {
                            // Run the Turing Machine on custom inputs
                            System.out.print("Do you want to show steps? (Y:N): ");
                            boolean showSteps = scanner.next().toUpperCase().charAt(0) == 'Y';
                            turingMachine.setShowSteps(showSteps);

                            char runChoice;
                            do {
                                turingMachine.resetSettings();
                                System.out.print("Enter the tape: ");
                                String tape = scanner.next();
                                turingMachine.setTape(tape);
                                turingMachine.run();
                                System.out.print("\033[0;30m");
                                System.out.print("Do you want to enter another tape? (Y:N): ");
                                runChoice = scanner.next().toUpperCase().charAt(0);
                            } while (runChoice == 'Y');
                        }
                        break;
                    default:
                        System.out.println("Option not found");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }
        } while (choice != 'y');
        
        scanner.close();
    }

}
