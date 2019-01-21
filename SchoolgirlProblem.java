//INCOMPLETE CODE

/*
Kirkman's Schoolgirl Problem: Fifteen schoolgirls (named A, B, C, ..., M, N, O)
 take their daily walks in five rows, with three girls per row.
 How can it be arranged so that each schoolgirl walks in the same row with
 each other schoolgirl exactly once each week? Make SchoolgirlSolver.java,
 a program that answers this question. For each day of the week,
 it should output the girls' walking schedule as a 5-row x 3-column
 array of letters
 */

import java.util.ArrayList;

public class SchoolgirlProblem {

    public static boolean [][] alreadyWalked;
    public static int[] walkers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    public static char[] names = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};

    static int rows = 5;
    static int placeInLine = 3;
    static int numOfGirls = 15;
    static int columns = 7;
    static boolean walkedToday = false;

    private static int[][][] initializeTables() {
        alreadyWalked = new boolean[numOfGirls][numOfGirls];
        for( int i = 0; i < numOfGirls; i++ ){
            for (int j = 0; j < numOfGirls; j++) {
                if (i == j) {
                    alreadyWalked[i][j] = true;
                } else{
                    alreadyWalked[i][j] = false;
                }
            }
        }
        int [][][] children = new int [rows][columns][placeInLine];



        int count = 0;
        for (int row = 0; row < 5; row++) {
            for (int place = 0; place < 3; place++) {
                children[row][0][place] = count;
                count ++;
            }
            alreadyWalked[count - 1][count - 2] = true;
            alreadyWalked[count - 1][count - 3] = true;
            alreadyWalked[count - 2][count - 3] = true;
            alreadyWalked[count - 2][count - 1] = true;
            alreadyWalked[count - 3][count - 1] = true;
            alreadyWalked[count - 3][count - 2] = true;
        }

        for( int day = 1; day < 7; day++ ){
            for( int row = 0; row < 3; row++ ){
                children[row][day][0] = row;
            }
        }
        return children;
    }

    private static boolean walkedToday(int index) {
        return true;
    }

    private static boolean validity(int[][][] children, int row, int column, int placeInLine, int val){

        if (placeInLine == 0) {
            if (!walkedToday(val)) {
                place(children, row, column, placeInLine, val);
                return true;
            } else {
                return false;
            }
        }
        if (placeInLine == 1) {
            int previousVal = children[row][column][placeInLine - 1];
            if (!alreadyWalked[val][previousVal]) {
                if (!walkedToday(val)) {

                    place(children, row, column, placeInLine, val);
                    alreadyWalked[val][previousVal] = true;
                    placeInLine++;
                    return true;
                } else {
                    return false;
                }

            } else {
                    return false;
                }
        }
        if (placeInLine == 2) {
            int previousVal = children[row][column][placeInLine - 1];
            int previousVal2 = children[row][column][placeInLine - 2];
            if (!alreadyWalked[val][previousVal]) {
                if (!alreadyWalked[val][previousVal2]) {
                    if (!walkedToday(val)) {
                        place(children, row, column, placeInLine, val);
                        alreadyWalked[val][previousVal] = true;
                        alreadyWalked[val][previousVal2] = true;
                        placeInLine = 0;
                        row++;
                        return true;
                    } else {
                        return false;
                    }
                    } else {
                        return false;
                    }

                    } else {
                        return false;
                    }
                }
                return false;
            }


    private static void place(int[][][] children, int row, int column, int placeInLine, int val) {
        children[row][column][placeInLine] = val;
        walkedToday(val);
    }


    private static void backtrack(int[][][] children, int row, int column, int placeInLine, int val) {
        int count1 = row;
        int count2 = column;

        if (placeInLine > 0) {
            placeInLine -= 1;
            //alreadyWalked[val][previousVal] = true;
        } else{
            placeInLine = 2;
            row -= 1;
            //alreadyWalked[val][placeInLine - 1] = true;
        }
    }

    public static void printTable( int [][][] children, int rows, int columns, int placeInLine ){
        for( int row = 0; row < rows; row++ ){
            for( int day = 0; day < columns; day ++ ){
                for( int kid = 0; kid < placeInLine; kid++ ){
                    System.out.print(children[rows][columns][placeInLine] + "       " );
                }
                System.out.print( "   ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public static void main( String [] args ){

        int[][][] children = initializeTables();

        for( int row = 0; row < 5; row++ ){
            for( int day = 0; day < 7; day ++ ){
                for( int kid = 0; kid < placeInLine; kid++ ){
                    System.out.print(names[children[ row][day][ kid ]] + " " );
                }
                System.out.print( "   ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");

        for (int row = 0; row < 5; row++) {
            for (int column = 1; column < 7; column++) {
                for (int placeInLine = 0; placeInLine < 3; placeInLine++) {
                    for (int index = 3; index < 15; index++) {
                        if (validity(children, row, column, placeInLine, index)) {
                            printTable(children, row, column, placeInLine);
                        } else {
                            backtrack(children, row, column, placeInLine, index);
                        }
                    }
                }
            }
        }
    }
}
