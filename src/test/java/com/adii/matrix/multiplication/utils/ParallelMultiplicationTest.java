package com.adii.matrix.multiplication.utils;

import org.junit.Test;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;

public class ParallelMultiplicationTest extends MultiplicationTestUtilHelper {

	ParallelMultiplicationUtil parallelMultiplicationUtil = new ParallelMultiplicationUtil();

	@Test
	public void testParallelMultiplication() throws InvalidInputException {
		printBeginningOfTestMethod("testParallelMultiplication");
		Matrix matrixActualResult = parallelMultiplicationUtil.multiply(matrixLeft, matrixRight, 5);
		verifyResult(matrixExpectedResult, matrixActualResult);
	}

	@Test
	public void testParallelMultiplicationTwo() throws InvalidInputException {
		printBeginningOfTestMethod("testParallelMultiplicationTwo");
		Matrix matrixActualResult = parallelMultiplicationUtil.multiply(matrixLeftTwo, matrixRightTwo, 6);
		verifyResult(matrixExpectedResultTwo, matrixActualResult);
	}

	@Test
	public void testParallelMultiplicationThree() throws InvalidInputException {
		printBeginningOfTestMethod("testParallelMultiplicationThree");
		Matrix matrixActualResult = parallelMultiplicationUtil.multiply(matrixLeftTwo, matrixRight, 7);
		verifyResult(matrixExpectedResultThree, matrixActualResult);
	}

	@Test(expected = InvalidInputException.class)
	public void testParallelMultiplication_InvalidInputException() throws InvalidInputException {
		printBeginningOfTestMethod("testParallelMultiplication_InvalidInputException");
		parallelMultiplicationUtil.multiply(matrixLeft, matrixLeftTwo, 8);
	}
}
