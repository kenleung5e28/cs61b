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

  public static void addFirstSizeGetTest() {
    System.out.println("Running addFirst/size/get test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    ad.addFirst(10);
    ad.addFirst(20);
    ad.addFirst(30);

    boolean passed = checkEqual(3, ad.size());
    passed = checkEqual(30, ad.get(0)) && passed;
    passed = checkEqual(20, ad.get(1)) && passed;
    passed = checkEqual(10, ad.get(2)) && passed;

    System.out.println("Printing out deque: ");
    ad.printDeque();

    printTestStatus(passed);
  }

  public static void isEmptyTest() {
    System.out.println("Running isEmpty test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    boolean passed = checkEqual(true, ad.isEmpty());

    ad.addFirst(1);

    passed = checkEqual(false, ad.isEmpty()) && passed;

    ad.removeFirst();

    passed = checkEqual(true, ad.isEmpty()) && passed;

    printTestStatus(passed);
  }

  public static void removeFirstTest() {
    System.out.println("Running removeFirst test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    ad.addFirst(10);
    ad.addFirst(20);
    ad.addFirst(30);
    int item = ad.removeFirst();

    boolean passed = checkEqual(2, ad.size());
    passed = checkEqual(30, item) && passed;
    passed = checkEqual(20, ad.get(0)) && passed;
    passed = checkEqual(10, ad.get(1)) && passed;

    System.out.println("Printing out deque: ");
    ad.printDeque();

    printTestStatus(passed);
  }

  public static void addLastTest() {
    System.out.println("Running addLast test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    ad.addLast(10);
    ad.addLast(20);
    ad.addLast(30);

    boolean passed = checkEqual(3, ad.size());
    passed = checkEqual(10, ad.get(0)) && passed;
    passed = checkEqual(20, ad.get(1)) && passed;
    passed = checkEqual(30, ad.get(2)) && passed;

    printTestStatus(passed);
  }

  public static void removeLastTest() {
    System.out.println("Running removeLast test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    ad.addLast(10);
    ad.addLast(20);
    ad.addLast(30);
    int item = ad.removeLast();

    boolean passed = checkEqual(2, ad.size());
    passed = checkEqual(30, item) && passed;
    passed = checkEqual(10, ad.get(0)) && passed;
    passed = checkEqual(20, ad.get(1)) && passed;

    printTestStatus(passed);
  }

  public static void addManyItemsTest() {
    System.out.println("Running adding many items test.");
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    for (int i = 1; i <= 100; i++) {
      ad.addLast(i);
    }

    boolean passed = checkEqual(100, ad.size());
    passed = checkEqual(1, ad.get(0)) && passed;
    passed = checkEqual(50, ad.get(49)) && passed;
    passed = checkEqual(100, ad.get(99)) && passed;

    System.out.println("Printing out deque: ");
    ad.printDeque();

    printTestStatus(passed);
  }

  public static void main(String[] args) {
    System.out.println("Running tests.\n");
    addFirstSizeGetTest();
    isEmptyTest();
    addLastTest();
    removeFirstTest();
    removeLastTest();
    addManyItemsTest();
  }
}
