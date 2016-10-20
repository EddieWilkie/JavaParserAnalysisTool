import java.util.Scanner;

public class Application {
	//main method
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		DequeADT<Object> t1 = new NodeDeque<>();
		boolean quit = false;
		String choice = "";
		String item;
		Application.displayMenu();
		
		if(true){
			System.out.println("Hello");
		}
		while (!quit) {
			choice = input.next();
			switch (choice) {
			case "af":
				System.out.println("Enter the string to be pushed: ");
				item = input.next();
				t1.addFirst(item);
				break;
			case "al":
				System.out.println("Enter the string to be pushed: ");
				item = input.next();
				t1.addLast(item);
				break;

			case "gf":
				try {
					System.out.println("Item is: " + t1.getFirst());
				} catch (EmptyDequeException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "gl":
				try {
					System.out.println("Item is: " + t1.getLast());
				} catch (EmptyDequeException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "rf":
				try {
					System.out.println("Item removed is: " + t1.removeFirst());
				} catch (EmptyDequeException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "rl":
				try {
					System.out.println("Item removed is: " + t1.removeLast());
				} catch (EmptyDequeException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "e":
				if (t1.isEmpty())
					System.out.println("Deque is empty");
				else
					System.out.println("Deque is not empty");
				break;

			case "s":
				System.out.println("Size is: " + t1.size());
				break;
			case "q":
				quit = true;
				break;

			case "i":
				Application.displayMenu();
				break;
			default:
				System.out
						.println("Invalid choice insert i to view instructions");
			}
		}
	}
	//Display Menu!!!
	private static void displayMenu() {
		System.out.println("Select");
		System.out.println(" af to push to the front of the Deque");
		System.out.println(" al to push to the back of the Deque");
		System.out.println(" gf to view item on front of Deque");
		System.out.println(" gl to view item on back of Deque");
		System.out.println(" rf to remove item from the front of the Deque");
		System.out.println(" rl to remove item from the back of the Deque");
		System.out.println(" e to see if Deque is empty");
		System.out.println(" s for number of items on the Deque");
		System.out.println(" q to quit");
		System.out.println(" i to repeat this list");
		System.out
				.println("____________________________________________________");
	}
}
