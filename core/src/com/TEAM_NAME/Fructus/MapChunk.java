package com.TEAM_NAME.Fructus;

public class MapChunk {
	//Size of map
		public static final int mapWidth = 250;
		public static final int mapHeight = 250;

		//Map of stage
		public static int[][] map = new int[mapWidth][mapHeight];

		//Temp Map Array
		public static int[][] tempMap = new int[mapWidth][mapHeight];

		//value of wall is 1
		public final static int wall = 1;
		//value of floor is 0
		public final static int floor = 0;

		//probability to place a wall into grid
		public static double fillProb = .40;

		//Thresholds, pertains to how Cellular Automata algorithms work
		public static int r1_cutoff = 5;
		public static int r2_cutoff = 2;
		
		//FUNCTION TO CREATE MAP
		public void makeMap()
		{
			initMap();
			//Calls Generation many times to make map more cavern like
			for(int i = 0; i < 30; i++)
			{
				generation();
			}
		}
		
		//Initializes values in map
		public static void initMap()
		{
			int col;
			int row;
			int bot = mapHeight - 1;
			int right = mapWidth - 1;

			//This for loop will populate the map with random walls
			//Keeps the outter most edges as walls
			//Scans through each row
			for(row=1; row < bot; row++)
			{
				//Scans through each column
				for (col=1; col < right; col++) 
				{
					//Chooses to place a wall or floor
					map[col][row] = randPick();
				}
			}

			//Creates walls for left and right side
			for (col=0; col < mapWidth; col++) 
			{
				map[col][0] = wall;
				map[col][right] = wall;
			}

			//Creates walls for top and bottom side
			for (row=0; row < mapHeight ; row++) 
			{
				map[0][row] = wall;
				map[bot][row] = wall;
			}

			//Initializes Temp Map with all walls
			for(row=0; row < mapHeight; row++)
			{
				//Scans through each column
				for (col=1; col < mapWidth; col++) 
				{
					tempMap[col][row] = wall;
				}
			}

		}

		//The Cellular Automata Algorithm
		public static void generation()
		{
			//Mostly Temp Variables for for loops
			int row;
			int col;
			int ii;
			int jj;
			int bot = mapHeight - 1;
			int right = mapWidth - 1;

			//Looks at the inner part of the array, not the sides/edges
			//Iterates through each row
			for (row=1; row < bot; row++) 
			{
				//Iterates through each column
				for (col=1; col < right; col++) 
				{
					//Adjacent Wall counters
					//Counts how many walls are around a certain wall
					int adjcount1 = 0;
					//Counts how many walls are within the vicinity of a certain wall
					int adjcount2 = 0;

					//A loop that will count how many adjacent walls there are
					for (ii=-1; ii <= 1; ii++) 
					{
						for (jj=-1; jj <= 1; jj++) 
						{
							if((map[row+ii][col+jj]) != floor)
							{
								adjcount1++;
							}
						}
					}

					//Loop that checks to see whether or not a wall too isolated or to clear a floor path
					//Checks a 5x5 area around wall, not counting the corners, edges, or outside of array
					for(ii = row-2; ii <= row+2 ; ii++)
					{
						for(jj = col-2; jj <= col+2; jj++)
						{
							//Checks if area that is being scanned, a corner
							if((Math.abs(ii-row) == 2) && (Math.abs(jj-col) == 2))
								{continue;}
							//Checks if area that is being scanned, a edge or outside of array
							if((ii>=mapHeight)||(jj>=mapWidth)||(ii<0)||(jj<0))
								{continue;}
							if(map[ii][jj] != floor)
								{adjcount2++;}
						}
					}

					//Changes the array values if thresholds are met
					if((adjcount1 >= r1_cutoff) || (adjcount2 <= r2_cutoff))
					{
						tempMap[row][col] = wall;
					}
					else
					{
						tempMap[row][col] = floor;
					}
				}
			}

			//All changes made to map that is stored on tempMap will be copied to map
			//Scans through each row
			for(row=1; row < bot; row++)
			{
				//Scans through each column
				for (col=1; col < right; col++) 
				{
					//Copies changes
					map[col][row] = tempMap[col][row];
				}
			}
		}

		//Randomly choose whether or not to place a wall
		public static int randPick()
		{
			
			if(Math.random() < fillProb)
			{

				return wall;
			}
			else
			{
				return floor;
			}
		}
}
