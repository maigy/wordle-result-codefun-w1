package com.codefun.com.codefun.week1;

import java.util.*;
import java.util.stream.IntStream;

public class WordleGuessResult {
    private static final int NUM_OF_CHARS = 5;
    private static final char[] SECRET_WORD = new char[NUM_OF_CHARS];


    static {
        for (int i = 0; i < NUM_OF_CHARS; i++) {
            SECRET_WORD[i] = (char) (Math.floor(Math.random() * 25) + 65);
        }

    }



    public static Integer[] guessResultLambda(String guessword) {

        Integer[] result = new Integer[NUM_OF_CHARS];
        HashSet<Character> charSet = new HashSet<>();

        IntStream.range(0, NUM_OF_CHARS).forEach(i -> {
            if (guessword.charAt(i) == SECRET_WORD[i])
                result[i] = 1;
            else
                charSet.add(SECRET_WORD[i]);
        });

        IntStream.range(0, NUM_OF_CHARS).forEach(i -> Optional.ofNullable(result[i]).ifPresentOrElse(
                r-> {},
                ()-> result[i] = (charSet.contains(guessword.charAt(i)) ? 2 : 0)
        ));

        return result;

    }

    public static Integer[] guessResultTradition(String guessword) {
        Integer[] result = new Integer[NUM_OF_CHARS];
        HashSet<Character> charSet = new HashSet<>();

        for (int i = 0; i < NUM_OF_CHARS; i++) {
            if (guessword.charAt(i) == SECRET_WORD[i])
                result[i] = 1;
            else
                charSet.add(SECRET_WORD[i]);
        }

        for (int i = 0; i < NUM_OF_CHARS; i++) {
            if (result[i] == null){
                result[i] =  charSet.contains(guessword.charAt(i)) ? 2 : 0;
            }
        }

        return result;
    }


    public static Integer[] guessResultCleanCode(String guessword) {
        Integer[] result = new Integer[NUM_OF_CHARS];
        String secretWordString = new String(SECRET_WORD);
        for (int i = 0; i < NUM_OF_CHARS; i++) {
            result[i] = (guessword.charAt(i) == SECRET_WORD[i] ? 1 :
                    (secretWordString.contains("" + guessword.charAt(i)) ? 2 : 0));

        }
        return result;
    }



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your guess word with " + NUM_OF_CHARS + " lettres: ");

        String guessWord;
        while ((guessWord = input.next().toUpperCase()).length() < NUM_OF_CHARS) {
            System.out.println(guessWord + " is not a " + NUM_OF_CHARS + " letter word!");
        }

        input.close();

        System.out.println("Secret word is " + new String(SECRET_WORD));

        long startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(guessResultLambda(guessWord)));
        System.out.println("Time spent on Lambda Func Code (ms) " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(guessResultTradition(guessWord)));
        System.out.println("Time spent on Traditional Java Code (ms) " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(guessResultCleanCode(guessWord)));
        System.out.println("Time spent using String's Contains Method (ms) " + (System.currentTimeMillis() - startTime));

    }

}
