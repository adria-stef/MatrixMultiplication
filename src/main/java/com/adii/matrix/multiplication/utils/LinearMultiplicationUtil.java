package com.adii.matrix.multiplication.utils;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;

public class LinearMultiplicationUtil extends MultiplicationUtil {

	@Override
	public Matrix multiply(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException {
		validateMatrixIsSet(matrixLeft);
		validateMatrixIsSet(matrixRight);
		double[][] leftArray = matrixLeft.getDoubleMatrixArray();
		double[][] rightArray = matrixRight.getDoubleMatrixArray();
		validateDimentions(matrixLeft, matrixRight);

		int resultRows = matrixLeft.getRows();
		int resultColumns = matrixRight.getColumns();
		int leftColumns = matrixLeft.getColumns();

		double[][] result = new double[resultRows][resultColumns];

		for (int i = 0; i < resultRows; i++) {
			for (int j = 0; j < resultColumns; j++) {
				for (int k = 0; k < leftColumns; k++) {
					result[i][j] += leftArray[i][k] * rightArray[k][j];
				}
			}
		}
		return new Matrix(result);
	}
}