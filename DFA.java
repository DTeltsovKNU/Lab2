package com.company;

import java.util.*;

public class DFA {
    Set<Character> alphabet;

    Set<Integer> states;

    // states.contains(startState);
    Integer startState;

    // states.containsAll(finalStates);
    Set<Integer> finalStates;

    // states.containsAll(transitionFunctions.keySet();
    // alphabet.containsAll(new HashSet<Character>(transitionFunctions.values()));
    Map<Integer, Map<Character, Integer>> transitionFunction;
    DFA() {
        alphabet = new HashSet<>();
        states = new HashSet<>();
        startState = 0;
        finalStates = new HashSet<>();
        transitionFunction = new HashMap<>();
    }
    public DFA(Scanner fileScanner) {

        String preAlphabet = "abcdefghijklmnopqrstuvwxyz";
        int alphabetSize = fileScanner.nextInt();
        alphabet = new HashSet<>();
        for (int i = 0; i < alphabetSize; ++i) {
            alphabet.add(preAlphabet.charAt(i));
        }

        int numberOfStates = fileScanner.nextInt();
        states = new HashSet<>(numberOfStates);
        for (int i = 0; i < numberOfStates; ++i) {
            states.add(i);
        }

        startState = fileScanner.nextInt();

        int numberOfFinalStates = fileScanner.nextInt();
        finalStates = new HashSet<>(numberOfFinalStates);
        for (int i = 0; i < numberOfFinalStates; ++i) {
            finalStates.add(fileScanner.nextInt());
        }

        transitionFunction = new HashMap<>(numberOfStates);

        for (Integer state : states) {
            transitionFunction.put(state, new HashMap<>());
        }

        while (fileScanner.hasNext()) {
            Integer fromState = fileScanner.nextInt();
            Character viaLetter = fileScanner.next().charAt(0);
            Integer toState = fileScanner.nextInt();
            transitionFunction.get(fromState).put(viaLetter,toState);
        }
    }

    public Set<Integer> getStates(Integer startState){
        Set<Integer> states= new HashSet<>();
        Queue<Integer> q= new LinkedList<>();
        q.add(startState);
        while (q.size()>0){
            Integer state=q.peek();
            q.remove();
            states.add(state);
            for (Integer to:transitionFunction.get(state).values()){
                if (states.contains(to)) continue;
                q.add(to);
            }
        }
        return states;
    }

    public Integer Run(Character viaLetter, Integer state){
        if (!transitionFunction.get(state).keySet().contains(viaLetter)){
            return -1;
        }
        return transitionFunction.get(state).get(viaLetter);
    }

    public Integer Run(String s, Integer state){
        for (int i=0; i<s.length(); ++i){
            Character viaLetter =s.charAt(i);
            state=Run(viaLetter,state);
            if (state==-1) return state;
        }
        return state;
    }

}
