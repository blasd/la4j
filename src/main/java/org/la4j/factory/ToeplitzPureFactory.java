package org.la4j.factory;

import java.util.Arrays;
import java.util.Random;

import org.la4j.LinearAlgebra;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.source.MatrixSource;
import org.la4j.matrix.structured.ToeplitzPureMatrix;

public class ToeplitzPureFactory extends BasicFactory {
	private static final long serialVersionUID = 1622102217414834030L;

	@Override
	public Matrix createMatrix() {
		return new ToeplitzPureMatrix();
	}

	@Override
	public Matrix createMatrix(int rows, int columns) {
		return new ToeplitzPureMatrix(rows, columns);
	}

	@Override
	public Matrix createMatrix(double[][] array) {
		int rows = array.length;
		int columns = array[0].length;

		if (rows == 0 || columns == 0) {
			return new ToeplitzPureMatrix(rows, columns);
		} else {
			double[] firstColumn = new double[rows];
			double[] firstRow = array[0];

			for (int i = 0; i < rows; i++) {
				firstColumn[i] = array[i][0];
			}

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (array[i][j] != ToeplitzPureMatrix.get(i, firstRow, j, firstColumn)) {
						// input is NOT a Toeplitz matrix: fallback on dense
						return LinearAlgebra.DENSE_FACTORY.createMatrix(array);
					}
				}
			}

			return new ToeplitzPureMatrix(this, firstColumn, firstRow);
		}
	}

	@Override
	public Matrix createMatrix(Matrix matrix) {
		int rows = matrix.rows();
		int columns = matrix.columns();

		if (rows == 0 || columns == 0) {
			return new ToeplitzPureMatrix(rows, columns);
		} else {
			double[] firstColumn = new double[rows];
			double[] firstRow = new double[columns];

			for (int i = 0; i < rows; i++) {
				firstColumn[i] = matrix.get(i, 0);
			}
			for (int i = 0; i < columns; i++) {
				firstRow[i] = matrix.get(0, i);
			}

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (matrix.get(i, j) != ToeplitzPureMatrix.get(i, firstRow, j, firstColumn)) {
						// input is NOT a Toeplitz matrix: fallback on dense
						return LinearAlgebra.DENSE_FACTORY.createMatrix(matrix);
					}
				}
			}

			return new ToeplitzPureMatrix(this, firstColumn, firstRow);
		}
	}

	@Override
	public Matrix createMatrix(MatrixSource source) {
		int rows = source.rows();
		int columns = source.columns();

		if (rows == 0 || columns == 0) {
			return new ToeplitzPureMatrix(rows, columns);
		} else {
			double[] firstColumn = new double[rows];
			double[] firstRow = new double[columns];

			for (int i = 0; i < rows; i++) {
				firstColumn[i] = source.get(i, 0);
			}
			for (int i = 0; i < columns; i++) {
				firstRow[i] = source.get(0, i);
			}

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (source.get(i, j) != ToeplitzPureMatrix.get(i, firstRow, j, firstColumn)) {
						// input is NOT a Toeplitz matrix: fallback on dense
						return LinearAlgebra.DENSE_FACTORY.createMatrix(source);
					}
				}
			}

			return new ToeplitzPureMatrix(this, firstColumn, firstRow);
		}
	}

	@Override
	public Matrix createConstantMatrix(int rows, int columns, double value) {
		double[] firstColumn = new double[rows];
		Arrays.fill(firstColumn, value);

		double[] firstRow = new double[columns];
		Arrays.fill(firstRow, value);

		return new ToeplitzPureMatrix(this, firstColumn, firstRow);
	}

	@Override
	public Matrix createRandomMatrix(int rows, int columns) {
		Random rnd = new Random();

		double[] firstColumn = new double[rows];
		for (int i = 0; i < rows; i++) {
			firstColumn[i] = rnd.nextDouble();
		}

		double[] firstRow = new double[columns];
		if (rows > 0 && columns > 0) {
			firstRow[0] = firstColumn[0];
		}
		for (int i = 1; i < columns; i++) {
			firstRow[i] = rnd.nextDouble();
		}

		return new ToeplitzPureMatrix(this, firstColumn, firstRow);
	}

	@Override
	public Matrix createRandomSymmetricMatrix(int size) {
		Random rnd = new Random();

		double[] firstColumn = new double[size];
		for (int i = 0; i < size; i++) {
			firstColumn[i] = rnd.nextDouble();
		}

		return new ToeplitzPureMatrix(this, firstColumn, firstColumn);
	}

	@Override
	public Matrix createSquareMatrix(int size) {
		return new ToeplitzPureMatrix(size, size);
	}

	@Override
	public Matrix createIdentityMatrix(int size) {
		double[] firstRowAndColumn = new double[size];

		firstRowAndColumn[0] = 1D;

		return new ToeplitzPureMatrix(this, firstRowAndColumn, firstRowAndColumn);
	}

	@Override
	public Matrix createBlockMatrix(Matrix a, Matrix b, Matrix c, Matrix d) {
		// TODO Auto-generated method stub
		return null;
	}

}
