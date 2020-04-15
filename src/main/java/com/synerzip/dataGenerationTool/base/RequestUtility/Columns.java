package com.synerzip.dataGenerationTool.base.RequestUtility;

/**
 * @author supriyad
 *@since April 2020
 */
public class Columns {

	
	String type;
	String title;
	String option;

	public Columns(String type, String title, String option) {
		super();
		this.type = type;
		this.title = title;
		this.option = option;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Columns() {
		super();
	}
	

	
	
}
