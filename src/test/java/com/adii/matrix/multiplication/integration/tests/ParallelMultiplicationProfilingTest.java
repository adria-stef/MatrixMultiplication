package com.adii.matrix.multiplication.integration.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.adii.matrix.multiplication.commons.CommonUtil;
import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;
import com.adii.matrix.multiplication.utils.IOUtils;
import com.adii.matrix.multiplication.utils.ParallelMultiplicationUtil;

public class ParallelMultiplicationProfilingTest {

	private static final String INFO = "[INFO] ";
	private ParallelMultiplicationUtil parallelMultiplicationUtil = new ParallelMultiplicationUtil();
	private static int cores = Runtime.getRuntime().availableProcessors();
	private static IOUtils ioUtils = new IOUtils();
	private static CommonUtil commonUtil = new CommonUtil();

	private static Matrix matrixLeft;
	private static Matrix matrixRight;
	private static Matrix matrixResult;
	private static Matrix matrixActualResult;

	private long count = 0L;
	private double averageForOneThread = 0.0;

	private static final Logger LOGGER = Logger.getLogger(ParallelMultiplicationProfilingTest.class.getName());

	@BeforeClass
	public static void before() throws IOException, InvalidInputException {
		matrixLeft = ioUtils.readMatrixFromFile(commonUtil.getRealPath("Ex1/left"));
		matrixRight = ioUtils.readMatrixFromFile(commonUtil.getRealPath("Ex1/right"));
		matrixResult = ioUtils.readMatrixFromFile(commonUtil.getRealPath("Ex1/result"));
	}

	@Test
	public void test() throws IOException, InvalidInputException {
		for (int threads = 1; threads < cores * 2; threads++) {
			for (int i = 0; i < 10; i++) {
				testExample(1, threads);
			}
			long average = (count / 10);
			if(threads==1){
				averageForOneThread = (double)average;
			}
			System.out.println(INFO + "Average time for ten executions and " + threads + " threads in milliseconds is: " + average);
			DecimalFormat df = new DecimalFormat("#.####");
			String message = "Boost for " + threads + " threads : " + df.format((double) (averageForOneThread / average));
			count = 0L;
			LOGGER.info(message);
			System.out.println(INFO + message);
		}
	}

	public void testExample(int testCaseNumber, int threads) throws IOException, InvalidInputException {
		long executionTime = getExecutionTime(matrixLeft, matrixRight, threads);
		verifyResultIsTheValid(matrixActualResult);
		count += executionTime;
	}

	public long getExecutionTime(Matrix matrixLeft, Matrix matrixRight, int threads) throws InvalidInputException {
		long startTime = System.currentTimeMillis();

		matrixActualResult = parallelMultiplicationUtil.multiply(matrixLeft, matrixRight, threads);

		long endTime = System.currentTimeMillis();

		printMultiplicationDuration(startTime, endTime);
		return (endTime - startTime);
	}

	public void printMultiplicationDuration(long startTime, long endTime) {
		StringBuilder message = new StringBuilder();
		message.append(INFO).append("Multiplication took ");
		message.append((TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endTime
				- startTime))));
		message.append(" seconds");
		message.append(" (" + (endTime - startTime) + " milliseconds)");
		System.out.println(message.toString());
	}

	public void verifyResultIsTheValid(Matrix actualResult) throws IOException {
		boolean contentEquals = matrixResult.equals(actualResult);
		System.out.println(INFO + "Matrix is valid :" + contentEquals);
		assertTrue("Matrix is not valid", contentEquals);
	}

	public String getName(String filePath) {
		return filePath.substring(filePath.lastIndexOf("/") + 1);
	}

}
