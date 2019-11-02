package com.cs506.workshop;

import org.apache.wicket.util.io.IClusterable;

public enum WorkshopType implements IClusterable {
	
	HIGH_ROPES_COURSE (0),
	LOW_ROPES_COURSE (1),
	GROUND (2),
	UNSURE (3);
	
	private final int type;
	private final String name;
	
	private WorkshopType(int type) {
		this.type = type;
		switch(type) {
		case 0:
			this.name = "High Ropes Course (Open April 1st-November 1st)";
			break;
		case 1:
			this.name = "Low Ropes Course (Open April 1st-November 1st)";
			break;
		case 2:
			this.name = "Ground/Teambuilding Workshop";
			break;
		case 3:
			this.name = "Unsure";
			break;
			default:
				throw new IllegalArgumentException("Unknown type: " + type);
		}
	}

}
