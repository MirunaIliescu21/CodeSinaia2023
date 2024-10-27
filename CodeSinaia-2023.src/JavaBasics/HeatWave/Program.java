package JavaBasics.HeatWave;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A log file containes a log of daily temperatures, in the form
 * of integer values, one number per line. The first number in the file
 * gives the total count of temperatures that follow in the file.
 * A Heat Wave is defined as a sequence of increasing temperatures, followed
 * by a sequence of decreasing temperatures. For instance, in the following
 * temperature log of 13 temperatures:
 * -4 -5 -3 0 0 4 10 11 9 6 7 5 3
 * The sequence starting at index 1 and ending at index 9 is a Heat Wave.
 * Write a program to determine the starting, ending and maximum
 * temperature in a given log file.
 */
public class Program {

    private static int[][] _logs;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        System.out.printf("Welcome to Heat Wave...\n");
        System.out.printf("Current directory: %s\n", (new File(".")).getAbsolutePath());
        System.out.printf("Log filename? > ");
        String logFilename = console.nextLine();

        readLogs(logFilename);
        computeWaves();
        printLogs();
        printLongestWave();

        System.out.printf("Goodbye!\n");
        console.close();
    }

    private static void readLogs(String logFilename) throws FileNotFoundException {
        Scanner logReader = new Scanner(new File(logFilename));
        int nLogs = logReader.nextInt();
        _logs = new int[nLogs][3];
        for (int i = 0; i < _logs.length; i++) {
            _logs[i][0] = logReader.nextInt();
            _logs[i][1] = 0;
            _logs[i][2] = 0;
        }
        logReader.close();
    }

    private static void computeWaves() {
        for (int i = 1; i < _logs.length; i++) {
            if (_logs[i][0] > _logs[i-1][0]) {
                _logs[i][1] = _logs[i-1][1] + 1;
            }
        }

        for (int i = _logs.length-2; i >= 0; i--) {
            if (_logs[i][0] > _logs[i+1][0]) {
                _logs[i][2] = _logs[i+1][2] + 1;
            }
        }
    }

    private static void printLongestWave() {
        int iLongest = 0;
        int wLongest = 1 + _logs[0][2];
        for (int i = 1; i < _logs.length; i++) {
            int wCurrent = 1 + _logs[i][1] + _logs[i][2];
            if (wCurrent > wLongest) {
                iLongest = i;
                wLongest = wCurrent;
            }
        }

        System.out.printf("Longest wave:\n");
        System.out.printf("Starts at: %d\n", iLongest - _logs[iLongest][1]);
        System.out.printf("Ends at:   %d\n", iLongest + _logs[iLongest][2]);
        System.out.printf("Peak at:   %d\n", iLongest);
        System.out.printf("Max temp:  %d\n", _logs[iLongest][0]);
    }

    private static void printLogs() {
        for (int i = 0; i < _logs.length; i++) {
            System.out.printf("[%3d:%2d:%2d] ", _logs[i][0], _logs[i][1], _logs[i][2]);
            if ((i+1) % 10 == 0) {
                System.out.println();
            }
        }
    }
}

