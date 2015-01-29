package com.adii.matrix.multiplication.utils;

import org.junit.Test;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;

public class LinearMultiplicationUtilTest extends MultiplicationTestUtilHelper {
	MultiplicationUtil linearMultiplicationUtil = new LinearMultiplicationUtil();

	@Test
	public void testLinearMultiplication() throws InvalidInputException {
		printBeginningOfTestMethod("testLinearMultiplication");
		Matrix matrixActualResult = linearMultiplicationUtil.multiply(matrixLeft, matrixRight);
		verifyResult(matrixExpectedResult, matrixActualResult);
	}

	@Test
	public void testLinearMultiplicationTwo() throws InvalidInputException {
		printBeginningOfTestMethod("testLinearMultiplicationTwo");
		Matrix matrixActualResult = linearMultiplicationUtil.multiply(matrixLeftTwo, matrixRightTwo);
		verifyResult(matrixExpectedResultTwo, matrixActualResult);
	}

	@Test
	public void testLinearMultiplicationThree() throws InvalidInputException {
		printBeginningOfTestMethod("testLinearMultiplicationThree");
		Matrix matrixActualResult = linearMultiplicationUtil.multiply(matrixLeftTwo, matrixRight);
		verifyResult(matrixExpectedResultThree, matrixActualResult);
	}

	@Test(expected = InvalidInputException.class)
	public void testLinearMultiplication_InvalidInputException() throws InvalidInputException {
		printBeginningOfTestMethod("testLinearMultiplication_InvalidInputException");
		linearMultiplicationUtil.multiply(matrixLeft, matrixLeftTwo);
	}

}
