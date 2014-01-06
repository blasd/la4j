package org.la4j.matrix.structured;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.la4j.LinearAlgebra;
import org.la4j.factory.Factory;
import org.la4j.matrix.dense.AbstractBasicMatrix;

public class ToeplitzMatrix extends AbstractBasicMatrix {

	private double[] firstColumn;
	private double[] firstRow;

	public ToeplitzMatrix() {
		this(0, 0);
	}

	public ToeplitzMatrix(int i, int j) {
		this(LinearAlgebra.TOEPLITZ_FACTORY, i, j);
	}

	public ToeplitzMatrix(Factory factory, int rows, int columns) {
		super(factory, rows, columns);

		firstColumn = new double[rows];
		firstRow = new double[columns];
	}

	public ToeplitzMatrix(Factory factory, double[] firstColumn, double[] firstRow) {
		super(factory, firstColumn.length, firstRow.length);
		this.firstColumn = firstColumn;
		this.firstRow = firstRow;
	}

	@Override
	public double[][] toArray() {
		return null;
	}

	@Override
	public double get(int i, int j) {
		return get(i, firstColumn, j, firstRow);
	}

	public static double get(int i, double[] firstRow, int j, double[] firstColumn) {
		if (i < j) {
			return firstRow[j - i];
		} else {
			return firstColumn[i - j];
		}
	}

	@Override
	public void set(int i, int j, double value) {
		if (i < j) {
			firstRow[j - i] = value;
		} else {
			firstColumn[i - j] = value;
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
