package com.synerzip.dataGenerationTool.base.RequestUtility;



/**
 * @author supriyad
 *@since April 2020
 */
public class RequestHelper {

	
	int numRows;
	
	Columns[] column;

	public RequestHelper(int numRows, Columns[] column) {
		super();
		this.numRows = numRows;
		this.column = column;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public Columns[] getColumn() {
		return column;
	}

	public void setColumn(Columns[] column) {
		this.column = column;
	}
}
