/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package services;

import java.util.Arrays;

public class FilePathTransferOperation {

	private String fileToRead;

	public FilePathTransferOperation() {
		}

	public FilePathTransferOperation(byte[] fileToRead) {
		this.fileToRead= Arrays.toString(fileToRead);
	}
	public String getFileToRead() {
		return String.valueOf(fileToRead);
	}
	public void setFileToRead(byte[] fileToRead) {
		this.fileToRead = Arrays.toString(fileToRead);
	}

	@Override
	public String toString() {
		return "FilePathTransferOperation{" +
			"fileToRead=" + Arrays.toString(new String[]{fileToRead}) +
			'}';
	}

}