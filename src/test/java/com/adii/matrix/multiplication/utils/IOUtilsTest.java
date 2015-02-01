package com.adii.matrix.multiplication.utils;

import java.io.IOException;

import org.junit.Test;

import com.adii.matrix.multiplication.commons.CommonUtil;
import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;
import com.adii.matrix.multiplication.utils.IOUtils;

public class IOUtilsTest {
	private IOUtils ioUtils = new IOUtils();
	private CommonUtil commonUtil = new CommonUtil();

	private String leftRealPath = commonUtil.getRealPath("Ex1/left");
	private String somefileRealPath = commonUtil.getRealPath("somefile");
	private String testfileRealPath = commonUtil.getRealPath("testfile");
	private MultiplicationTestUtilHelper multiplicationTestUtilHelper = new MultiplicationTestUtilHelper();

	@Test
	public void testWriteRead() throws IOException, InvalidInputException {
		double[][] matrixArray = { { 0.5832938629143785, 2.0 }, { 3.0, 5.0 }, { 3.0, 5.0 } };
		Matrix matrix = new Matrix(matrixArray);
		ioUtils.writeMatrixToFile(matrix, testfileRealPath);

		multiplicationTestUtilHelper.verifyResult(matrix, ioUtils.readMatrixFromFile(testfileRealPath));
	}

	@Test
	public void testReadWriteRead() throws IOException, InvalidInputException {
		Matrix readMatrixFromFile = ioUtils.readMatrixFromFile(leftRealPath);
		ioUtils.writeMatrixToFile(readMatrixFromFile, somefileRealPath);
		multiplicationTestUtilHelper.verifyResult(ioUtils.readMatrixFromFile(somefileRealPath), readMatrixFromFile);
	}
	
}
