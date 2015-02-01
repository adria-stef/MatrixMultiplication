package com.adii.matrix.multiplication.matrix;

import java.util.Arrays;

public class Matrix {

	double[][] doubleMatrixArray;
	int rows;
	int columns;

	public Matrix(double[][] doubleMatrix) {
		this.doubleMatrixArray = doubleMatrix;
		this.rows = doubleMatrix.length;
		this.columns = doubleMatrix[0].length;
	}

	public Matrix(int rows, int columns) {
		this.doubleMatrixArray = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}

	public double[][] getDoubleMatrixArray() {
		return doubleMatrixArray;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public double getElement(int i, int j) {
		return doubleMatrixArray[i][j];
	}

	public void setElement(int i, int j, double element) {
		doubleMatrixArray[i][j] = element;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columns;
		result = prime * result + Arrays.hashCode(doubleMatrixArray);
		result = prime * result + rows;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Matrix other = (Matrix) obj;
		if (columns != other.columns) {
			return false;
		}
		if (rows != other.rows) {
			return false;
		}
		if (!Arrays.deepEquals(doubleMatrixArray, other.doubleMatrixArray)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				stringBuilder.append(doubleMatrixArray[i][j]).append(" ");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

}
