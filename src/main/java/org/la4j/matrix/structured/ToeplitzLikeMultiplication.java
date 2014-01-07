package org.la4j.matrix.structured;

// http://www4.ncsu.edu/~kaltofen/bibliography/94/Ka94_issac.pdf
public class ToeplitzLikeMultiplication {
	// See Kaltofen 94, Proposition 2
	public ToeplitzLikeMatrix mulWithGenerated(ToeplitzLikeMatrix left, ToeplitzLikeMatrix right) {
		ToeplitzLikeMatrix output = new ToeplitzLikeMatrix(left.rows(), right.columns());

		// Phi+(G).H
		output.pushGenerators(left.getY(), left.getTZ().multiply(right));

		// Z.G.Ztr.Phi+(H)
		output.pushGenerators(new ShiftDisplacementMatrix<T>(this.mulThisWithMatrix(new ShiftDisplacementMatrix<T>(right.getY(), -1, 0)), 1, 0),
				right.getTZ());
		// TODO: more to push

		return output;
	}
}
