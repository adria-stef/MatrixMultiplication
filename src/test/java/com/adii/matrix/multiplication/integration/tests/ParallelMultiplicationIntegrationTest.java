package com.adii.matrix.multiplication.integration.tests;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;
import com.adii.matrix.multiplication.utils.ParallelMultiplicationUtil;

public class ParallelMultiplicationIntegrationTest extends MultiplicationIntegrationTest {

	private ParallelMultiplicationUtil parallelMultiplicationUtil = new ParallelMultiplicationUtil();
	private static final int THREADS = 8;

	@Override
	public Matrix getExecutionTime(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException {
		long startTime = System.currentTimeMillis();

		Matrix matrixActualResult = parallelMultiplicationUtil.multiply(matrixLeft, matrixRight, THREADS);

		long endTime = System.currentTimeMillis();

		printMultiplicationDuration(startTime, endTime);
		return matrixActualResult;
	}

}
