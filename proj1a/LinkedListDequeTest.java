/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	public static boolean checkGet(int expected, int actual) {
		if (expected != actual) {
			System.out.println("get() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	public static boolean checkGetRecursive(int expected, int actual) {
		if (expected != actual) {
			System.out.println("getRecursive() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	public static boolean checkEqual(int expected, int actual) {
		if (expected != actual) {
			System.out.println("got " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		LinkedListDeque<String> lld1 = new LinkedListDeque<>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
	}

	public static void addGetTest() {
		System.out.println("Running add/get test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

		lld1.addFirst(10);
		lld1.addLast(20);
		boolean passed = checkSize(2, lld1.size());
		passed = checkGet(20, lld1.get(1)) && passed;

		printTestStatus(passed);
	}

	public static void getRemoveLastRemoveFirstTest() {
		System.out.println("Running get/removeLast/removeFirst test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

		lld1.addFirst(1);
		lld1.addFirst(2);
		lld1.addFirst(3);
		int lastItem = lld1.removeLast();

		boolean passed = checkSize(2, lld1.size());
		passed = checkGet(2, lld1.get(1)) && passed;
		passed = checkEqual(1, lastItem) && passed;

		int firstItem = lld1.removeFirst();
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEqual(3, firstItem) && passed;

		printTestStatus(passed);
	}

	public static void getRecursiveTest() {
		System.out.println("Running getRecursive test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

		lld1.addLast(1);
		lld1.addFirst(2);
		boolean passed = checkGetRecursive(2, lld1.getRecursive(0));
		passed = checkGetRecursive(1, lld1.getRecursive(1)) && passed;

		printTestStatus(passed);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		addGetTest();
		getRemoveLastRemoveFirstTest();
		getRecursiveTest();
	}
} 