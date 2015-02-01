package com.adii.matrix.multiplication.integration.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.adii.matrix.multiplication.commons.CommonUtil;
import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;
import com.adii.matrix.multiplication.utils.IOUtils;

public abstract class MultiplicationIntegrationTest {
	public static final String INFO = "[INFO] ";
	
	private IOUtils ioUtils = new IOUtils();
	private CommonUtil commonUtil = new CommonUtil();

	private static final Logger LOGGER = Logger.getLogger(MultiplicationIntegrationTest.class.getName());
	@Test
	public void test() throws IOException, InvalidInputException {
		for (int i = 1; i < 3; i++) {
			testExample(i);
		}
	}

	public void testExample(int testCaseNumber) throws IOException, InvalidInputException {
		Matrix matrixLeft = ioUtils.readMatrixFromFile(commonUtil.getRealPath("Ex" + testCaseNumber + "/left"));
		Matrix matrixRight = ioUtils.readMatrixFromFile(commonUtil.getRealPath("Ex" + testCaseNumber + "/right"));

		Matrix matrixActualResult = getExecutionTime(matrixLeft, matrixRight);
		ioUtils.writeMatrixToFile(matrixActualResult, commonUtil.getRealPath("Ex" + testCaseNumber + "/output"));
		verifyContentIsTheSame(commonUtil.getRealPath("Ex" + testCaseNumber + "/output"), commonUtil.getRealPath("Ex" + testCaseNumber + "/result"));
	}

	public abstract Matrix getExecutionTime(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException ;

	public void printMultiplicationDuration(long startTime, long endTime) {
		StringBuilder message = new StringBuilder();
		message.append(INFO).append("Multiplication took ");
		message.append((TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endTime
				- startTime))));
		message.append(" seconds");
		message.append(" (" + (endTime - startTime) + " milliseconds)");
		System.out.println(message.toString());
	}

	public void verifyContentIsTheSame(String filePath, String filePathResult) throws IOException {
		boolean contentEquals = FileUtils.contentEquals(new File(filePath), new File(filePathResult));
		StringBuilder message = new StringBuilder();
		message.append("Files ").append(getName(filePath)).append(" and ").append(getName(filePathResult)).append(" are equal: ")
				.append(contentEquals);
		System.out.println(INFO + message);
		LOGGER.info(message);
		assertTrue(message.toString(), contentEquals);
	}

	public String getName(String filePath) {
		return filePath.substring(filePath.lastIndexOf("/") + 1);
	}
	
}
