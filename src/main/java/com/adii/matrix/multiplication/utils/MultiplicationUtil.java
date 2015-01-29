package com.adii.matrix.multiplication.utils;

import org.apache.log4j.Logger;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;

public abstract class MultiplicationUtil {

	private static final Logger LOGGER = Logger.getLogger(MultiplicationUtil.class.getName());

	public abstract Matrix multiply(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException;

	protected void validateDimentions(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException {
		int leftColumns = matrixLeft.getColumns();
		int rightRows = matrixRight.getRows();

		if (leftColumns != rightRows) {
			LOGGER.error("Multiplication is not possible. The number of columns of the left matrix, currently [" + matrixLeft
					+ "] must be equal to the number of rows of the right matrix, currently [" + matrixRight + "].");
			throw new InvalidInputException();
		}
	}

	protected void validateMatrixIsSet(Matrix matrix) {
		if (null == matrix) {
			throw new IllegalArgumentException("Multiplication cannot be evaluated. Please provide proper data.");
		}
	}

}