package com.adii.matrix.multiplication.utils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;

public class ParallelMultiplicationUtil extends MultiplicationUtil {

	private static final int THRESHOLD = 100;
	private static Matrix resultMatrix = null;

	@Override
	public Matrix multiply(Matrix matrixLeft, Matrix matrixRight) throws InvalidInputException {
		return multiply(matrixLeft, matrixRight, Runtime.getRuntime().availableProcessors());
	}

	class MultiplicationAction extends RecursiveAction {

		private static final long serialVersionUID = 1L;
		private Matrix left;
		private Matrix right;
		private int start;
		private int end;

		MultiplicationAction(Matrix left, Matrix right, int start, int end) {
			this.left = left;
			this.right = right;
			this.start = start;
			this.end = end;
		}

		@Override
		protected void compute() {
			if ((end - start) <= THRESHOLD) {
				calculateFromStartToEnd();
			} else {

				int separator = (start + end) / 2;

				invokeAll(new MultiplicationAction[] { new MultiplicationAction(left, right, start, separator),

				new MultiplicationAction(left, right, separator, end) });

			}
		}

		private void calculateFromStartToEnd() {

			for (int i = start; i < end; i++) {
				for (int j = 0; j < right.getColumns(); j++) {
					double temp = 0.0;
					for (int k = 0; k < left.getColumns(); k++) {
						temp += left.getElement(i, k) * right.getElement(k, j);
					}
					resultMatrix.setElement(i, j, temp);
				}
			}
		}

	}

	public Matrix multiply(Matrix matrixLeft, Matrix matrixRight, int threadNumber) throws InvalidInputException {
		validateMatrixIsSet(matrixLeft);
		validateMatrixIsSet(matrixRight);

		int resultRows = matrixLeft.getRows();
		int resultColumns = matrixRight.getColumns();
		resultMatrix = new Matrix(new double[resultRows][resultColumns]);

		validateDimentions(matrixLeft, matrixRight);

		executeTask(matrixLeft, matrixRight, threadNumber);

		return resultMatrix;
	}

	private void executeTask(Matrix matrixLeft, Matrix matrixRight, int threadNumber) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(threadNumber);
		RecursiveAction task = new MultiplicationAction(matrixLeft, matrixRight, 0, matrixLeft.getRows());
		forkJoinPool.invoke(task);
	}

}
