/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package services;

public class FilePathTransferOperation {

	private String fileToRead;
	public FilePathTransferOperation() {
		}

	public FilePathTransferOperation(String fileToRead) {
		this.fileToRead=fileToRead;
	}

	public String getFileToRead() {
		return fileToRead;
	}

	public String setFileToRead(String fileToRead) {
		this.fileToRead = fileToRead;
		return fileToRead;
	}

	@Override
	public String toString() {
		return fileToRead;
	}

}