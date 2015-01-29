package com.adii.matrix.multiplication.integration.tests;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;
import com.adii.matrix.multiplication.utils.LinearMultiplicationUtil;
import com.adii.matrix.multiplication.utils.MultiplicationUtil;

public class LinearMultiplicationIntegrationTest extends MultiplicationIntegrationTest {

	private MultiplicationUtil linearMultiplicationUtil = new LinearMultiplicationUtil();

	@Override
	public Matrix getExecutionTime(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException {
		long startTime = System.currentTimeMillis();

		Matrix matrixActualResult = linearMultiplicationUtil.multiply(matrixLeft, matrixRight);

		long endTime = System.currentTimeMillis();

		printMultiplicationDuration(startTime, endTime);
		return matrixActualResult;
	}

}
