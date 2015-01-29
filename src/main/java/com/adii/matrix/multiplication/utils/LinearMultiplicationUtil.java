package com.adii.matrix.multiplication.utils;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;

public class LinearMultiplicationUtil extends MultiplicationUtil {

	@Override
	public Matrix multiply(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException {
		validateMatrixIsSet(matrixLeft);
		validateMatrixIsSet(matrixRight);
		// TODO name variables
		 double[][] leftArray = matrixLeft.getDoubleMatrixArray();
		 double[][] rightArray = matrixRight.getDoubleMatrixArray();
		validateDimentions(matrixLeft, matrixRight);

		int resultRows = matrixLeft.getRows();
		int resultColumns = matrixRight.getColumns();
		int leftColumns = matrixLeft.getColumns();

		 double[][] result = new double[resultRows][resultColumns];
//		Matrix result = new Matrix(resultRows, resultColumns);

		for (int i = 0; i < resultRows; i++) {
			for (int j = 0; j < resultColumns; j++) {
//				double element = 0.0;
				for (int k = 0; k < leftColumns; k++) {
//					 TODO Matrix OBJECT
					 result[i][j] += leftArray[i][k] * rightArray[k][j];
//					element += matrixLeft.getElement(i, k) * matrixRight.getElement(k, j);
				}
//				result.setElement(i, j, element);
			}
		}
		return new Matrix(result);
//		return result;
	}
}