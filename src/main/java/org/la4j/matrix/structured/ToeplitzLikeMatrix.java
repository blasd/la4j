package org.la4j.matrix.structured;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.la4j.LinearAlgebra;
import org.la4j.factory.Factory;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.dense.AbstractBasicMatrix;

public class ToeplitzLikeMatrix extends AbstractBasicMatrix {

	private Matrix generatingColumns;
	private Matrix transposedGeneratingRows;

	public Matrix getY() {
		return generatingColumns;
	}

	public Matrix getTZ() {
		return transposedGeneratingRows;
	}

	public ToeplitzLikeMatrix() {
		this(0, 0);
	}

	public ToeplitzLikeMatrix(int i, int j) {
		this(LinearAlgebra.TOEPLITZ_FACTORY, i, j);
	}

	public ToeplitzLikeMatrix(Factory factory, int rows, int columns) {
		super(factory, rows, columns);

		generatingColumns = LinearAlgebra.DENSE_FACTORY.createMatrix(0, rows);
		transposedGeneratingRows = LinearAlgebra.DENSE_FACTORY.createMatrix(0, columns);
	}

	@Override
	public double[][] toArray() {
		return null;
	}

	@Override
	public double get(int i, int j) {
		// This is very slow for a single get
		return 0D;
	}

	@Override
	public void set(int i, int j, double value) {
		throw new RuntimeException("Can not set a value in a " + this.getClass());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	public void pushGenerators(Matrix y, Matrix transposedZ) {
		generatingColumns = LinearAlgebra.DENSE_FACTORY.createBlockMatrix(generatingColumns, y,
				LinearAlgebra.DENSE_FACTORY.createMatrix(generatingColumns.rows(), 0), LinearAlgebra.DENSE_FACTORY.createMatrix(y.rows(), 0));
		transposedGeneratingRows = LinearAlgebra.DENSE_FACTORY.createBlockMatrix(transposedGeneratingRows, transposedZ,
				LinearAlgebra.DENSE_FACTORY.createMatrix(transposedGeneratingRows.rows(), 0),
				LinearAlgebra.DENSE_FACTORY.createMatrix(transposedZ.rows(), 0));
	}
}
