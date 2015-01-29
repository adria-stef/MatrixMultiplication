package com.adii.matrix.multiplication.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.exceptions.MatrixIOException;
import com.adii.matrix.multiplication.matrix.Matrix;

public class IOUtils {
	private static final Logger LOGGER = Logger.getLogger(IOUtils.class.getName());

	// TODO handle exception
	public Matrix readMatrixFromFile(String filePath) throws IOException, InvalidInputException {
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		Matrix populatedMatrix = null;
		try {
			inputStream = new FileInputStream(filePath);
			dataInputStream = new DataInputStream(inputStream);

			int matrixRows = dataInputStream.readInt();
			int matrixColumns = dataInputStream.readInt();

			populatedMatrix = populateMatrixArray(dataInputStream, matrixRows, matrixColumns);
		} catch (FileNotFoundException e) {
			LOGGER.error("Cannot find file: " + filePath, e);
			throw new InvalidInputException();
		} catch (IOException e) {
			LOGGER.error("Read failed with file: " + filePath, e);
			throw new MatrixIOException();
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}

			if (null != dataInputStream) {
				dataInputStream.close();
			}
		}
		return populatedMatrix;
	}

	private Matrix populateMatrixArray(DataInputStream dataInputStream, int matrixRows, int matrixColumns) throws IOException {
		double[][] matrix = new double[matrixRows][matrixColumns];

		outer: while (true) {
			double number;
			for (int i = 0; i < matrixRows; i++) {
				for (int j = 0; j < matrixColumns; j++) {
					int count = dataInputStream.available();
					if (count > 0) {
						number = dataInputStream.readDouble();
						matrix[i][j] = number;
					} else {
						break outer;
					}
				}
			}
		}
		return new Matrix(matrix);
	}

	public void writeMatrixToFile(Matrix matrix, String filePath) throws IOException, InvalidInputException {

		DataOutputStream dataOutputStream = null;
		try {
			dataOutputStream = new DataOutputStream(new FileOutputStream(filePath));
			writeDimentions(dataOutputStream, matrix);
			writeMatrix(dataOutputStream, matrix);

		} catch (FileNotFoundException e) {
			LOGGER.error("Cannot find file: " + filePath, e);
			throw new InvalidInputException();
		} catch (IOException e) {
			LOGGER.error("Read failed with file: " + filePath, e);
			throw new MatrixIOException();
		} finally {
			if (null != dataOutputStream) {
				dataOutputStream.close();
			}
		}
	}

	private void writeDimentions(DataOutputStream dataOutputStream, Matrix matrix) throws IOException {
		dataOutputStream.writeInt(matrix.getRows());
		dataOutputStream.writeInt(matrix.getColumns());
	}

	private void writeMatrix(DataOutputStream dataOutputStream, Matrix matrix) throws IOException {
		double[][] doubleMatrixArray = matrix.getDoubleMatrixArray();
		for (int i = 0; i < matrix.getRows(); i++) {
			for (int j = 0; j < matrix.getColumns(); j++) {
				dataOutputStream.writeDouble(doubleMatrixArray[i][j]);
			}
		}
	}

}
