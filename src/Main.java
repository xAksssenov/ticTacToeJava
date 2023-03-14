import java.util.Scanner;

import java.util.Random;

public class Main {

    public enum Field {
        CROSS('X'),
        ZERO('0'),
        EMPTY('.');

        private final char value;

        Field(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }
    }

    public enum Player {
        USER("Игрок"),
        COMP("Компьютер");


        Player(String value) {
        }
    }

    public static void main(String[] args) {

        //region Обозначение переменных
        Random random = new Random();

        Player currentPlayer, winPlayer = null;

        Field[][] allField;

        System.out.println("Добро пожаловать в игру: ");

        int fieldSize = 3;

        allField = new Field[fieldSize][fieldSize];
        //endregion

        //region Создание поля
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                allField[i][j] = Field.EMPTY;
            }
        }
        //endregion

        //region Рандомирование хода
        int rand;

        rand = random.nextInt(1000) + 1;

        if (rand > 500) {
            currentPlayer = Player.USER;
            System.out.println("Первым ходит игрок: ");
        } else {
            currentPlayer = Player.COMP;
            System.out.println("Первым ходит компьютер: ");
        }
        System.out.println("Для продолжения нажмите <Enter>: ");
        new Scanner(System.in).nextLine();
        //endregion

        //region Игровой процесс
        boolean playGame = true;

        while (playGame) {

            for (int i = 0; i < 50; i++) {
                System.out.println();
            }

            System.out.println("Игровое поле: ");

            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    System.out.print(allField[i][j].getValue());
                }
                System.out.println();
            }
            System.out.println("Поле выведено. Для продолжения нажмите <Enter>: ");
            new Scanner(System.in).nextLine();

            if (currentPlayer == Player.USER) {
                System.out.println("Ход игрока: ");

                boolean Motion;
                int iMotion = 0, jMotion = 0;

                do {

                    System.out.printf("Введите строку хода от %d до %d: %n", 1, fieldSize);
                    Motion = true;

                    try {
                        Scanner scanner = new Scanner(System.in);
                        iMotion = scanner.nextInt() - 1;

                        if (iMotion + 1 > fieldSize || iMotion < 0) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        Motion = false;
                    }

                    System.out.printf("Введите столбец хода от %d до %d: %n", 1, fieldSize);

                    try {
                        Scanner scanner = new Scanner(System.in);
                        jMotion = scanner.nextInt() - 1;

                        if (jMotion + 1 > fieldSize || jMotion < 0) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        Motion = false;
                    }

                } while (!Motion);

                if (allField[iMotion][jMotion] == Field.EMPTY) {
                    allField[iMotion][jMotion] = Field.CROSS;
                    currentPlayer = Player.COMP;
                }
            } else {
                System.out.println("Ход компьютера: ");

                int iMotion, jMotion;
                do {
                    iMotion = random.nextInt(fieldSize);
                    jMotion = random.nextInt(fieldSize);

                } while (allField[iMotion][jMotion] == Field.ZERO || allField[iMotion][jMotion] == Field.CROSS);

                if (allField[iMotion][jMotion] == Field.EMPTY) {
                    allField[iMotion][jMotion] = Field.ZERO;
                    currentPlayer = Player.USER;
                }
            }

            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (j == 0) {
                        if (allField[i][j] == Field.CROSS && allField[i][j + 1] == Field.CROSS && allField[i][j + 2] == Field.CROSS) {
                            winPlayer = Player.USER;
                            playGame = false;
                        }
                    }
                    if (i == 0) {
                        if (allField[i][j] == Field.CROSS && allField[i + 1][j] == Field.CROSS && allField[i + 2][j] == Field.CROSS) {
                            winPlayer = Player.USER;
                            playGame = false;
                        }
                    }
                    if (i == 0 && j == 0 && allField[i][j] == Field.CROSS && allField[i + 1][j + 1] == Field.CROSS && allField[i + 2][j + 2] == Field.CROSS) {
                        winPlayer = Player.USER;
                        playGame = false;
                    }
                }
            }
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (j == 0) {
                        if (allField[i][j] == Field.ZERO && allField[i][j + 1] == Field.ZERO && allField[i][j + 2] == Field.ZERO) {
                            winPlayer = Player.COMP;
                            playGame = false;
                        }
                    }
                    if (i == 0) {
                        if (allField[i][j] == Field.ZERO && allField[i + 1][j] == Field.ZERO && allField[i + 2][j] == Field.ZERO) {
                            winPlayer = Player.COMP;
                            playGame = false;
                        }
                    }
                    if (i == 0 && j == 0 && allField[i][j] == Field.ZERO && allField[i + 1][j + 1] == Field.ZERO && allField[i + 2][j + 2] == Field.ZERO) {
                        winPlayer = Player.COMP;
                        playGame = false;
                    }
                }
            }
            if (allField[0][2] == Field.ZERO && allField[1][1] == Field.ZERO && allField[2][0] == Field.ZERO) {
                winPlayer = Player.COMP;
                playGame = false;
            }
            if (allField[0][2] == Field.CROSS && allField[1][1] == Field.CROSS && allField[2][0] == Field.CROSS) {
                winPlayer = Player.USER;
                playGame = false;
            }

            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (allField[i][j] != Field.EMPTY) {
                        System.out.println("Игра окончена. Ничья. ");
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        for (
                int i = 0;
                i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(allField[i][j].getValue());
            }
            System.out.println();
        }
        //endregion

        //region Определение победителя
        if (winPlayer == Player.COMP) {
            System.out.println("Игра окончена. Победил компьютер. ");
        } else if (winPlayer == Player.USER) {
            System.out.println("Игра окончена. Победил игрок. ");
        }
        //endregion
    }
}