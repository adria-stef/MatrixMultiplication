package com.adii.matrix.multiplication.utils;

import static org.junit.Assert.assertEquals;

import com.adii.matrix.multiplication.matrix.Matrix;

public class MultiplicationTestUtilHelper {
	// TODO name matrix

	public double[][] doubleMatrixLeft = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
	public Matrix matrixLeft = new Matrix(doubleMatrixLeft);

	public double[][] doubleMatrixRight = { { 10, 11, 12 }, { 13, 14, 15 }, { 16, 17, 18 } };
	public Matrix matrixRight = new Matrix(doubleMatrixRight);

	public double[][] doubleMatrixResult = { { 84, 90, 96 }, { 201, 216, 231 }, { 318, 342, 366 } };
	public Matrix matrixExpectedResult = new Matrix(doubleMatrixResult);

	public double[][] doubleMatrixLeftTwo = { { 1, 2, 3 }, { 4, 5, 6 } };
	public Matrix matrixLeftTwo = new Matrix(doubleMatrixLeftTwo);

	public double[][] doubleMatrixRightTwo = { { 7, 8 }, { 9, 10 }, { 11, 12 } };
	public Matrix matrixRightTwo = new Matrix(doubleMatrixRightTwo);

	public double[][] doubleMatrixResultTwo = { { 58, 64 }, { 139, 154 } };
	public Matrix matrixExpectedResultTwo = new Matrix(doubleMatrixResultTwo);

	public double[][] doubleMatrixResultThree = { { 84, 90, 96 }, { 201, 216, 231 } };
	public Matrix matrixExpectedResultThree = new Matrix(doubleMatrixResultThree);

	public void verifyResult(Matrix matrixExpectedResult, Matrix matrixActualResult) {
		assertEquals("Matrix are not equal.", matrixExpectedResult, matrixActualResult);
	}

	public void printBeginningOfTestMethod(String methodName) {
		System.out.println();
		System.out.println("[INFO] Executing " + methodName);
	}
}
