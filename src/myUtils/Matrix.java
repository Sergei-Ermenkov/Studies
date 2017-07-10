package myUtils;

import java.math.BigInteger;

/**
 * Набор методов для работы с матрицами
 *
 * @author Sergei Ermenkov
 */
public class Matrix {

    /**
     * Перемножение метриц
     *
     * @param A Матрица
     * @param B Матрица
     * @return A * B
     * @throws IllegalArgumentException если задан неверный формат матрицы
     */
    public static BigInteger[][] multiplicationMatrix(BigInteger[][] A, BigInteger[][] B) {
        final int aColumns = A[0].length;
        final int aRows = A.length;
        final int bColumns = B[0].length;
        final int bRows = B.length;
        if (aColumns != bRows) throw new IllegalArgumentException("Неверный размер матриц");
        BigInteger[][] C = new BigInteger[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = BigInteger.ZERO;
                for (int k = 0; k < bRows; k++) {
                    C[i][j] = C[i][j].add(A[i][k].multiply(B[k][j]));
                }
            }
        }
        return C;
    }

    /**
     * Перемножение метриц
     *
     * @param A Матрица
     * @param B Матрица
     * @return A * B
     * @throws IllegalArgumentException если задан неверный формат матрицы
     */
    public static long[][] multiplicationMatrix(long[][] A, long[][] B) {
        final int aColumns = A[0].length;
        final int aRows = A.length;
        final int bColumns = B[0].length;
        final int bRows = B.length;
        if (aColumns != bRows) throw new IllegalArgumentException("Неверный размер матриц");
        long[][] result = new long[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                result[i][j] = 0;
                for (int k = 0; k < bRows; k++) {
                    result[i][j] = result[i][j] + A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Возведение в степень бинарным методом за O(logn)
     * берем двоичное представление степени pow = mk*2^k + mk-1*2^k-1 + ... + m1*2 + m0
     *
     * @param A   матрица
     * @param pow степень
     * @return A ^ pow
     * @throws IllegalArgumentException если матрицы не квадратные
     */
    public static BigInteger[][] powMatrix(BigInteger[][] A, int pow) {
        if (A[0].length != A.length) throw new IllegalArgumentException("Матрица не квадратная");

        //Приравниваем результат к 1 (единичная матрица)
        //Присвоить результирующей матрице единичную матрицу.
        BigInteger[][] result = new BigInteger[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i == j) {
                    result[i][j] = BigInteger.ONE;
                    continue;
                }
                result[i][j] = BigInteger.ZERO;
            }
        }
        while (pow > 0) {

            //если степень не кратна 2-м
            if (pow % 2 == 1) result = multiplicationMatrix(result, A);
            A = multiplicationMatrix(A, A);
            pow /= 2;
        }
        return result;
    }

    /**
     * Возведение в степень бинарным методом за O(logn)
     * берем двоичное представление степени pow = mk*2^k + mk-1*2^k-1 + ... + m1*2 + m0
     *
     * @param A   матрица
     * @param pow степень
     * @return A ^ pow
     * @throws IllegalArgumentException если матрицы не квадратные
     */
    public static long[][] powMatrix(long[][] A, int pow) {
        if (A[0].length != A.length) throw new IllegalArgumentException("Матрица не квадратная");

        //Присвоить результирующей матрице единичную матрицу.
        long[][] result = new long[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            result[i][i] = 1;
        }
        while (pow > 0) {

            //если степень не кратна 2-м
            if (pow % 2 == 1) result = multiplicationMatrix(result, A);
            A = multiplicationMatrix(A, A);
            pow /= 2;
        }
        return result;
    }

    /**
     * Возведение в степень бинарным методом матриц за O(logn) с помощю рекурсии
     * <p>
     * берем двоичное представление степени pow = mk*2^k + mk-1*2^k-1 + ... + m1*2 + m0
     *
     * @param A   мтрица
     * @param pow степень
     * @return A ^ pow
     * @throws IllegalArgumentException если матрицы не квадратные
     */
    public static long[][] fibMatrixRecurs(long[][] A, int pow) {
        if (A[0].length != A.length) throw new IllegalArgumentException("Матрица не квадратная");
        long[][] result;
        if (pow == 1) return A;

        //если степень четная
        if (pow % 2 == 0) {
            result = fibMatrixRecurs(A, pow / 2);
            return multiplicationMatrix(result, result);

            //если степень не четная
        } else {
            result = fibMatrixRecurs(A, (pow - 1) / 2);
            return multiplicationMatrix(multiplicationMatrix(result, result), A);
        }
    }
}