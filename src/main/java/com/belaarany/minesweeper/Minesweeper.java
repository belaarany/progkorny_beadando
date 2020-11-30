package com.belaarany.minesweeper;

import java.util.Map;
import java.util.Scanner;

public class Minesweeper {

    public Minesweeper() { }

    public void startGame() {
        Scanner in = new Scanner(System.in);

        System.out.println("--- Aknakereso ---");

        System.out.print("Oszlopok szama (2, 100): ");
        int nbColumns = in.nextInt();
        System.out.print("Sorok szama (2, 100): ");
        int nbLines = in.nextInt();
        System.out.print("Aknak szama (2, 100): ");
        int nbMines = in.nextInt();

        CellsGenerator cg = new CellsGenerator();
        Map<Position, Cell> cells = cg.generate(nbColumns, nbLines, nbMines);
        Grid grid = new Grid(cells, nbColumns, nbLines, nbMines);

        GridState gridState = GridState.NOT_CLEAR;
        while (gridState == GridState.NOT_CLEAR) {
            System.out.println(grid.toString());

            System.out.print("Valasztott oszlop (1, " + nbColumns + "): ");
            int column = in.nextInt();
            System.out.print("Valasztott sor (1, " + nbLines + "): ");
            int line = in.nextInt();

            gridState = grid.uncoverCell(new Position(column, line));
        }

        if (gridState == GridState.BURST) {
            System.out.println(grid.toString());

            System.out.println("--- Jatek vege. Veszitettel. ---");
        } else if (gridState == GridState.CLEAR) {
            System.out.println(grid.toString());

            System.out.println("--- Jatek vege. Nyertel. ---");
        }
    }
}
