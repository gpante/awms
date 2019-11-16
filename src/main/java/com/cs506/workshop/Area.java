package com.cs506.workshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Areas as taken from:
 * https://docs.google.com/forms/d/e/1FAIpQLSdmNnKSfayuSrwktKUhcnA2jGXDY-3HArMdQ0krsEQ4SzO55A/viewform
 * @author AJ
 */
public abstract class Area implements Serializable {
	
	public String name;
	
	public abstract String getName();
	
	public abstract int getValue();
	
	public Area() {
		this.name = getName();
	}
	
	public static final class Communication extends Area {

		@Override
		public String getName() {
			return "Communication";
		}

		@Override
		public int getValue() {
			return 0x1;
		}
		
	}
	
	public static final class Trust extends Area {

		@Override
		public String getName() {
			return "Trust";
		}

		@Override
		public int getValue() {
			return 0x2;
		}
		
	}
	
	public static final class ConflictResolution extends Area {

		@Override
		public String getName() {
			return "Conflict Resolution";
		}

		@Override
		public int getValue() {
			return 0x4;
		}
		
	}
	
	public static final class ProblemSolving extends Area {

		@Override
		public String getName() {
			return "Problem Solving/Decision Making";
		}

		@Override
		public int getValue() {
			return 0x8;
		}
		
	}
	
	public static final class VisionAndMission extends Area {

		@Override
		public String getName() {
			return "Vision and Mission";
		}

		@Override
		public int getValue() {
			return 0x10;
		}
		
	}
	
	public static final class GoalSetting extends Area {

		@Override
		public String getName() {
			return "Goal Setting";
		}

		@Override
		public int getValue() {
			return 0x20;
		}
		
	}
	
	public static final class CommunityBuilding extends Area {

		@Override
		public String getName() {
			return "Community Building";
		}

		@Override
		public int getValue() {
			return 0x40;
		}
		
	}
	
	public static final class Resiliency extends Area {

		@Override
		public String getName() {
			return "Resiliency";
		}

		@Override
		public int getValue() {
			return 0x80;
		}
		
	}
	
	public static final List<Area> getAreas() {
		List<Area> list = new ArrayList<Area>();
		list.add(new Communication());
		list.add(new Trust());
		list.add(new ConflictResolution());
		list.add(new ProblemSolving());
		list.add(new VisionAndMission());
		list.add(new GoalSetting());
		list.add(new CommunityBuilding());
		list.add(new Resiliency());
		return list;
	}
	
	public static final List<Area> getAreasFromValue(int value) {
		List<Area> list = new ArrayList<Area>();
		Communication c = new Communication();
		if ((value&c.getValue()) == c.getValue())
		list.add(c);
		Trust t = new Trust();
		if ((value&t.getValue()) == t.getValue())
		list.add(t);
		ConflictResolution cr = new ConflictResolution();
		if ((value&cr.getValue()) == cr.getValue())
		list.add(cr);
		ProblemSolving ps = new ProblemSolving();
		if ((value&ps.getValue()) == ps.getValue())
		list.add(ps);
		VisionAndMission vam = new VisionAndMission();
		if ((value&vam.getValue()) == vam.getValue())
		list.add(vam);
		GoalSetting gs = new GoalSetting();
		if ((value&gs.getValue()) == gs.getValue())
		list.add(gs);
		CommunityBuilding cb = new CommunityBuilding();
		if ((value&cb.getValue()) == cb.getValue())
		list.add(cb);
		Resiliency r = new Resiliency();
		if ((value&r.getValue()) == r.getValue())
		list.add(r);
		return list;
	}
	
}
