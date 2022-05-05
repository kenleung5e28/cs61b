public class ArrayDequeTest {
  public static <T> boolean checkEqual(T expected, T actual) {
    if (!expected.equals(actual)) {
      System.out.println("got " + actual + ", but expected: " + expected);
      return false;
    }
    return true;
  }

  public static void printTestStatus(boolean passed) {
    if (passed) {
      System.out.println("Test passed!\n");
    } else {
      System.out.println("Test failed!\n");
    }
  }

  public static void addFirstSizeTest() {
    System.out.println("Running addFirst/size test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    ad.addFirst(10);
    ad.addFirst(20);
    ad.addFirst(30);

    boolean passed = checkEqual(3, ad.size());

    System.out.println("Printing out deque: ");
    ad.printDeque();

    printTestStatus(passed);
  }

  public static void addFirstRemoveFirstTest() {
    System.out.println("Running addFirst/removeFirst test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    boolean passed = checkEqual(true, ad.isEmpty());

    ad.addFirst(1);

    passed = checkEqual(false, ad.isEmpty()) && passed;

    ad.removeFirst();

    passed = checkEqual(true, ad.isEmpty()) && passed;

    printTestStatus(passed);
  }

  public static void main(String[] args) {
    System.out.println("Running tests.\n");
    addFirstSizeTest();
    addFirstRemoveFirstTest();
  }
}
