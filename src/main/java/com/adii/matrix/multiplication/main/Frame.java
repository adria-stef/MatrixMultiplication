package com.adii.matrix.multiplication.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.adii.matrix.multiplication.exceptions.InvalidInputException;
import com.adii.matrix.multiplication.matrix.Matrix;
import com.adii.matrix.multiplication.utils.IOUtils;
import com.adii.matrix.multiplication.utils.LinearMultiplicationUtil;
import com.adii.matrix.multiplication.utils.MultiplicationUtil;
import com.adii.matrix.multiplication.utils.ParallelMultiplicationUtil;

public class Frame extends JPanel {

	private static final long serialVersionUID = -124211894146587995L;

	private static JFrame frame;
	private static JTextField textFieldLeftMatrixPath;
	private static JTextField textFieldRightMatrixPath;
	private static JTextField textResult;
	private static Color color;

	private static IOUtils ioUtils = new IOUtils();
	private static MultiplicationUtil linearMultiplicationUtil = new LinearMultiplicationUtil();
	private static ParallelMultiplicationUtil parallelMultiplicationUtil = new ParallelMultiplicationUtil();

	private static Matrix matrixLeft;
	private static Matrix matrixRight;

	public Frame() {
		super(new BorderLayout());

		color = new Color(238, 180, 180);

		JPanel labelPanel = new JPanel(new GridLayout(3, 1));
		add(labelPanel, BorderLayout.WEST);
		labelPanel.setBackground(color);

		JPanel fieldPanel = new JPanel(new GridLayout(3, 1));
		add(fieldPanel, BorderLayout.CENTER);
		fieldPanel.setBackground(color);

		JLabel labelLeftMatrixPath = new JLabel("Enter left matrix file source: ");
		textFieldLeftMatrixPath = new JTextField();

		JLabel labelRightMatrixPath = new JLabel("Enter right matrix file source: ");
		textFieldRightMatrixPath = new JTextField();

		JLabel labelResultMatrixPath = new JLabel("The output is in the following file: ");
		textResult = new JTextField();

		labelPanel.add(labelLeftMatrixPath);
		labelPanel.add(labelRightMatrixPath);
		labelPanel.add(labelResultMatrixPath);

		fieldPanel.add(textFieldLeftMatrixPath);
		fieldPanel.add(textFieldRightMatrixPath);
		fieldPanel.add(textResult);
	}

	public static JFrame createFrame() {
		final Frame myFrame = new Frame();

		JButton linear = new JButton("Linear multiply");

		linear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validateFileds();
				getMatrices();
				multiplyLinear();
				returnOutputFileName();
			}
		});

		JButton parallel = new JButton("Parallel multiply");

		parallel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validateFileds();
				getMatrices();
				multiplyParallel();
				returnOutputFileName();
			}
		});

		JButton newFrameButton = new JButton("Again!");

		newFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				frame = createFrame();
			}
		});

		frame = new JFrame();
		frame.setTitle("Matrix Multiplicatior");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container frameContentPane = frame.getContentPane();
		frameContentPane.add(myFrame, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(color);
		buttonPanel.add(linear);
		buttonPanel.add(parallel);
		buttonPanel.add(newFrameButton);
		frameContentPane.add(buttonPanel, BorderLayout.SOUTH);
		frameContentPane.setBackground(color);
		Dimension size = new Dimension(500, 115);
		frameContentPane.setPreferredSize(size);

		frame.pack();
		frame.setVisible(true);
		return frame;
	}

	private static void getMatrices() {
		try {
			matrixLeft = ioUtils.readMatrixFromFile(textFieldLeftMatrixPath.getText());
			matrixRight = ioUtils.readMatrixFromFile(textFieldRightMatrixPath.getText());
		} catch (InvalidInputException e) {
			handleException("Please provide valid source.");
		} catch (IOException e) {
			handleException("There has been a problem in reading or writing in files. Please check your files.");
		}
	}

	private static void multiplyLinear() {
		try {
			Matrix matrixActualResult = linearMultiplicationUtil.multiply(matrixLeft, matrixRight);
			ioUtils.writeMatrixToFile(matrixActualResult, generateOutputFileName());
		} catch (InvalidInputException invalidInputException) {
			handleException("Multiplication is not possible. The number of columns of the left matrix must be equal to the number of rows of the right matrix.");
		} catch (IOException ioException) {
			handleException("There has been a problem in reading or writing in files. Please check your files.");
		}
	}

	private static void multiplyParallel() {
		try {
			int cores = Runtime.getRuntime().availableProcessors();
			Matrix matrixActualResult = parallelMultiplicationUtil.multiply(matrixLeft, matrixRight, cores);
			ioUtils.writeMatrixToFile(matrixActualResult, generateOutputFileName());
		} catch (InvalidInputException invalidInputException) {
			handleException("Multiplication is not possible. The number of columns of the left matrix must be equal to the number of rows of the right matrix.");
		} catch (IOException ioException) {
			handleException("There has been a problem in reading or writing in files. Please check your files.");
		}
	}

	private static void returnOutputFileName() {
		textResult.setText(generateOutputFileName());
	}

	private static void validateFileds() {
		String leftMatrixPath = textFieldLeftMatrixPath.getText();
		String rightMatrixPath = textFieldRightMatrixPath.getText();
		if (!arePathsValid(leftMatrixPath, rightMatrixPath)) {
			handleException("Please provide valid file path.");
		}
	}

	private static void handleException(String message) {
		frame.dispose();
		JOptionPane.showMessageDialog(frame, message, "Invalid input", JOptionPane.OK_OPTION);
		frame = createFrame();
	}

	private static boolean arePathsValid(String leftMatrixPath, String rightMatrixPath) {
		if (isPathValid(leftMatrixPath) && isPathValid(rightMatrixPath)) {
			return true;
		}
		return false;
	}

	private static boolean isPathValid(String path) {
		return (!path.equals(null)) && (!path.equals("")) && (!path.equals(" ")) && isFilenameValid(path);
	}

	private static boolean isFilenameValid(String path) {
		File file = new File(path);
		try {
			file.getCanonicalPath();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private static String generateOutputFileName() {
		return getPath(textFieldLeftMatrixPath.getText()) + System.lineSeparator().trim() + "output";
	}

	private static String getPath(String text) {
		if (text.contains("/")) {
			return text.substring(0, text.lastIndexOf("/") + 1).trim();
		}
		if (text.contains("\\")) {
			return text.substring(0, text.lastIndexOf("\\") + 1).trim();
		}
		return text;
	}
}