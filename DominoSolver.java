/*
Make Java program DominoSolver:
its arguments will specify a valid sequence of dominoes;
its sole output should be the maximum possible reward for reducing the
sequence to a single domino. For example, if we were to execute
java DominoSolver 5 10 10 7 7 6, the program would output 720.
If the arguments do not constitute a valid sequence,
the program must throw an IllegalArgumentException.
*/


public class DominoSolver {
    private static int solveTable(int array[], int n) {

        int table[][] = new int[n][n];
        int result = 0;

        for (int x = 2; x < n; x++) {
            for (int i = 1; i < n - x + 1; i++){
                int j = i + x - 1;
                for (int k = i; k <= j - 1; k++) {
                    result += table[i][k] + table[k + 1][j] + array[i - 1] * array[k] * array[j];
                    if (result > table[i][j])
                        table[i][j] = result;
                        result = 0;
                }
            }
        }
        return table[1][n - 1];
    }

    public static void main(String args[]) {
        int[] dominoValues = new int[args.length / 2 + 1];
        //VALIDITY CHECK
        if (args.length % 2 == 1 || args.length == 2) {
          throw new IllegalArgumentException();
        }
        for (int x = 0; x < args.length - 1; x++) {
            try {
                int checkInt = Integer.parseInt(args[x]);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException();
            }
        }
        //create array
        int j = 1;
        dominoValues[0] = Integer.parseInt(args[0]);
        dominoValues[args.length / 2] = Integer.parseInt(args[args.length - 1]);
        for (int i = 1; i < args.length - 1; i += 2) {
          if (Integer.parseInt(args[i]) == Integer.parseInt(args[i + 1])) {
            dominoValues[j] = Integer.parseInt(args[i]);
          } else {
            throw new IllegalArgumentException();
          }
          j++;
        }
        System.out.println(solveTable(dominoValues, dominoValues.length));
    }
}
