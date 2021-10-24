package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String fileName = "./test.txt";
            //initialize automata
            DFA dfa = new DFA(getScanner(fileName));


            System.out.format("Enter w0: ");
            String w1 = scanner.next();

            Set<Integer> rechableStates= dfa.getStates(dfa.startState);

            for (int i:rechableStates){
                int state=dfa.Run(w1,i);
                if (state==-1) continue;
                if (dfa.finalStates.contains(state)) {
                    System.out.println("There DOES exist w1 such that w1w0 is acceptable.");

                    return;
                }
            }
                System.out.println("There does NOT exist w1 such that w1w0 is acceptable.");
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid file pathname");
        }

    }
    static Scanner getScanner(String pathname) throws FileNotFoundException {
        File file = new File(pathname);

        if (!file.exists()) {
            System.out.format("File '%s' does not exist.%n", pathname);
        }

        if (!file.canRead()) {
            System.out.format("Cannot read file '%s'.%n", pathname);
        }

        return new Scanner(file);
    }
}

