package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import cordinate.Cordinate;

public class Main {
	private static int board[][];
	private static int sizeOfBoard, startX,startY,count;
	private static ArrayList<Cordinate> totalMoves = new ArrayList<>();
	private static final Cordinate[] knightMoves = new Cordinate[] {
			new Cordinate(2, 1),
			new Cordinate(1, 2),
			new Cordinate(-1, 2),
			new Cordinate(-2, 1),
			new Cordinate(-2, -1), 
			new Cordinate(-1, -2), 
			new Cordinate(1, -2), 
			new Cordinate(2, -1), 
	};

	public static void main(String[] args) {
		//Getting the size of the board and the starting positions 
		getInfoInput();
		
		//Creating the board based on the input from user
		board = new int[sizeOfBoard][sizeOfBoard];
		
		System.out.println("");
		
		//Running the von dorfs algorithm, and checks of the positions has a solution
		if (solveKnightProblem(	startX, startY, 1)) {
			printBoard();
			//printFromTo();
		}

		else {
			System.out.println("Fra denne posisjonen er det ingen løsnning");
		}
	}

	public static boolean solveKnightProblem(int x, int y, int currentMove) {
		
		//Check so the algorithm ends on the last position
		if (currentMove == sizeOfBoard * sizeOfBoard) {
			board[y][x] = currentMove;
			totalMoves.add(new Cordinate(x, y));
			return true;
		}
		
		//Mark current move with the current number
		board[y][x] = currentMove;

		//Go through a list of possible moves from the current position
		for (Cordinate c : listOfAmountOfMoves(x, y)) {
			if (canMove(c.x(), c.y())) {
				//check so it wont bunch back and forward
				if (solveKnightProblem(c.x(), c.y(), currentMove + 1)) {
					
					//Adding the cordinate to a list 
					totalMoves.add(new Cordinate(x, y));
					return true;
				}
			}
		}
		
		//Back tracking
		board[y][x] = 0;
		return false;
		
	}

	public static ArrayList<Cordinate> listOfAmountOfMoves(int col, int row) {
		ArrayList<Cordinate> listOfMoves = new ArrayList<>();
		
		//Von dorfs algorithm
		for (Cordinate c : knightMoves) {
			count = 0;
			if (canMove((c.x() + col), (c.y() + row))) {

				for (Cordinate a : knightMoves) {
					if (canMove((a.x() + c.x() + col), (a.y() + c.y() + row))) {
						count++;
					}
				}
			}
			listOfMoves.add(new Cordinate((c.x() + col), (c.y() + row), count));
		}
			
			//Sort on total moves each position in the list have
			Collections.sort(listOfMoves, new Comparator<Cordinate>() {
			@Override
			public int compare(Cordinate c1, Cordinate c2) {

				return Integer.compare(c1.totalmoves(), c2.totalmoves());
			}
		});
		
		return listOfMoves;
	}

	public static boolean canMove(int x, int y) {
		return (0 <= y && y <= sizeOfBoard - 1 && 0 <= x && x <= sizeOfBoard - 1 && board[y][x] == 0);
	}

	private static void printBoard() {
		for (int i = 0; i < sizeOfBoard; i++) {
			for (int j = 0; j < sizeOfBoard; j++) {
				System.out.print(board[i][j] + "	");
			}
			System.out.println(" ");
		}

	}

	private static void printFromTo() {
		System.out.println("");
		for (int i = sizeOfBoard * sizeOfBoard - 1; i > 0; i--) {

			if (i == 0) {
				System.out.println("Trekk:" + (sizeOfBoard * sizeOfBoard - i));
				System.out.print("   " + "Fra:" + totalMoves.get(i + 1) + " Til:");
				System.out.println(totalMoves.get(i));
				System.out.println("");
			}

			else {
				if (i != sizeOfBoard * sizeOfBoard - 1) {
					System.out.println("Trekk:" + (sizeOfBoard * sizeOfBoard - 1 - i));
					System.out.print("   " + "Fra:" + totalMoves.get(i + 1) + " Til:");
					System.out.println(totalMoves.get(i));
					System.out.println("");
				}
			}
		}
	}
	
	private static void getInfoInput(){
		Scanner scanner = new Scanner(System.in);
		int max = 300;
		int min = 5;
		boolean correctInfo = false;
		
		System.out.println("Enter the size of the board from " + min + " to " + max + ":");
		
		do {
			try {
				
				sizeOfBoard = scanner.nextInt();
				
				if (sizeOfBoard >= min && sizeOfBoard <= max) {
					do{
						
						try {
							System.out.println("Enter start postion x ");
							startX = scanner.nextInt()-1;
							
							if(0 <= startX && startX <= sizeOfBoard -1){
								do{
									
									try {
										System.out.println("Enter start postion y ");
										startY = scanner.nextInt()-1;
										
										if(0 <= startY && startY <= sizeOfBoard -1){
											correctInfo=true;
											scanner.close();
										}
										
										else{
											System.out.println("You didn't enter a number inside the boundaries of the board:");
										}
										
										
									} catch (Exception e) {
										System.out.println("You know you need to type in a number now!");
										scanner.nextLine();
									}
								
								} while (!correctInfo);
							}
							
							else{
								System.out.println("You didn't enter a number inside the boundaries of the board:");
							}
							
							
						} catch (Exception e) {
							System.out.println("Please enter an Integer");
							scanner.nextLine();
						}
					
					} while (!correctInfo);
				} 
				else {
					
					System.out.println("You didn't enter a number between " + min + " and " + max);
					scanner.nextLine();
				} 
			} catch (Exception e) {
				System.out.println("Please enter an Integer ");
				scanner.nextLine();
			}
		} while (!correctInfo);
	}

}


