package com.ozkansari.populationsim;
/**
 * SimulationConstants.java
 * 
 */

/**
 * @author Ozkan SARI
 *
 *	Constants that will be used in Simulation
 */
public class SimulationConstants {
	
	public static int GRID_DIMENSION = 15;
	public static boolean  INITIALIZE_POPULATION_MANUALLY = true;
	public static boolean RANDOM_INITIALIZE_POPULATION = true;
	public static boolean SINGLE_STEP_GENERATION = true;

	public static boolean firstGeneration = true;
	
	public static int cellSize = 0;
	public static int x0_screen = 0;
	public static int y0_screen = 0;
	
	public static String helpString = new String(

			"Populations, is a game-like simulation software.\n" +
			"There is an N x N grid representing a region\n" + 
			"where each cell is either empty or contains a\n" +
			"living individual. You start with an initial\n" + 
			"population, and at each generation, apply two\n" +
			"basic rules to create the next generation. Rules\n" +
			" are simple enough: a) Every cell containing 2 or\n" +
			"3 alive neighbors will live in the next generation\n" +
			"(if it was empty, this means a birth; if there was\n" +
			"a living individual in that cell, then it simply\n" +
			"survives). b) A cell containing less than 2 or more\n" +
			"than 3 neighbors will be empty in the next generation.\n" +
			" A cell has 8 neighbors.\n"

	);
	
	public void setGRID_DIMENSION(int grid_dimension) {
		GRID_DIMENSION = grid_dimension;
	}

	public void setINITIALIZE_POPULATION_MANUALLY(
			boolean initialize_population_manually) {
		INITIALIZE_POPULATION_MANUALLY = initialize_population_manually;
	}

	public void setRANDOM_INITIALIZE_POPULATION(
			boolean random_initialize_population) {
		RANDOM_INITIALIZE_POPULATION = random_initialize_population;
	}

	public void setSINGLE_STEP_GENERATION(boolean single_step_generation) {
		SINGLE_STEP_GENERATION = single_step_generation;
	}

	public static boolean isFirstGeneration() {
		return firstGeneration;
	}

	public static void setFirstGeneration(boolean firstGenVal) {
		firstGeneration = firstGenVal;
	}

	public static int setCellSize(int cellSizeVal) {
		cellSize = cellSizeVal;
		return cellSizeVal;
	}

	public static int getCellSize() {
		return cellSize;
	}

	public static int getX0_screen() {
		return x0_screen;
	}

	public static int setX0_screen(int x0_screen) {
		SimulationConstants.x0_screen = x0_screen;
		return x0_screen;
	}

	public static int getY0_screen() {
		return y0_screen;
	}

	public static int setY0_screen(int y0_screen) {
		SimulationConstants.y0_screen = y0_screen;
		return y0_screen;
	}
}
