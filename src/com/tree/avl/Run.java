package com.tree.avl;

public class Run
{
  /* If no arguments, run each test sequentially, but abort at the first failure.
     If the first argument is i (a number), just run test #i.
     If there is a second argument, any failure message is suppressed. The
       exit code indicates success or failure.
   */
  public static void main(String[] args) {
    Test[] suite = { new Empty(), new Equal(),
        new Sub2(), new Super2(),
        new Common(), new Disjoint()
    };

    if (args.length == 0) {
      for (int i = 0; i < suite.length; i++) {
        long start = System.currentTimeMillis();
        System.out.println("running test " + i);
        suite[i].test();
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("  This case uses " + time + " millisecond.");
      }
      System.out.println("finished");
    } else {
      int i = Integer.parseInt(args[0]);
      Test t = suite[i];
      if (args.length == 1) {
        t.test();
      } else {
        try {
          t.test();
        }
        catch (Exception e) {
          System.exit(1);
        }
      }
    }
  }

  public static class Fail extends RuntimeException
  {
    public Fail(boolean yours) {
      super("Incorrect answer: " + yours);
    }
  }

  public static void expect(boolean mine, boolean yours) {
    if (mine != yours) throw new Fail(yours);
  }

  public static interface Test
  {
    public void test();
  }

  // Shorthand for one of the Node constructors.
  private static <T> Node<T> mknode(Node<T> left, T a, Node<T> right) {
    return new Node<T>(left, a, right);
  }

  // Shorthand for another Node constructor.
  private static <T> Node<T> mkleaf(T a) {
    return new Node<T>(a);
  }

  // corner cases
  public static class Empty implements Test {
    public void test() {
      AVL<Integer> V;

      // both empty, true
      expect(true, AVL.equalSet(new AVL<Integer>(), new AVL<Integer>()));

      // 1st empty, 2nd not, false
      V = new AVL<Integer>();
      V.root = new Node<Integer>(null, 10);
      expect(false, AVL.equalSet(new AVL<Integer>(), V));

      // 2nd empty, 1st not, false
      V = new AVL<Integer>();
      V.root = new Node<Integer>(null, 10);
      expect(false, AVL.equalSet(V, new AVL<Integer>()));
    }
  }

  // Two equal sets. But only content equal; shape different.
  public static class Equal implements Test {
    public void test() {
      AVL<Integer> T = new AVL<Integer>();
      AVL<Integer> V = new AVL<Integer>();
      T.root = mknode(mknode(mknode(mknode(null,
          11,
          mkleaf(16)),
          19,
          mknode(mkleaf(20),
              22,
              mkleaf(25))),
          27,
          mknode(mkleaf(32),
              37,
              mknode(null,
                  44,
                  mkleaf(60)))),
          62,
          mknode(mknode(mkleaf(64),
              67,
              mknode(null,
                  70,
                  mkleaf(75))),
              78,
              mknode(mknode(mkleaf(79),
                  88,
                  mkleaf(90)),
                  92,
                  mknode(mkleaf(93),
                      96,
                      mkleaf(97)))));
      V.root = mknode(mknode(mknode(mknode(mkleaf(11),
          16,
          mkleaf(19)),
          20,
          mkleaf(22)),
          25,
          mknode(mkleaf(27),
              32,
              mkleaf(37))),
          44,
          mknode(mknode(mknode(mkleaf(60),
              62,
              mkleaf(64)),
              67,
              mknode(mkleaf(70),
                  75,
                  mkleaf(78))),
              79,
              mknode(mknode(mkleaf(88),
                  90,
                  null),
                  92,
                  mknode(mkleaf(93),
                      96,
                      mkleaf(97)))));
      expect(true, AVL.equalSet(T, V));
    }
  }

  // 1st tree is a proper subset of 2nd tree. Answer is "false".
  public static class Sub2 implements Test {
    public void test() {
      AVL<Integer> T, V;
      // 1st tree is missing just one key.
      T = new AVL<Integer>();
      V = new AVL<Integer>();
      T.root = mknode(mknode(mknode(mkleaf(15),
          16,
          mkleaf(18)),
          20,
          mknode(mknode(mkleaf(23),
              24,
              mkleaf(33)),
              34,
              mkleaf(35))),
          37,
          mknode(mknode(mknode(mkleaf(40),
              42,
              null),
              47,
              mknode(mkleaf(48),
                  49,
                  mkleaf(52))),
              61,
              mknode(mknode(mkleaf(64),
                  74,
                  mkleaf(75)),
                  77,
                  mknode(mkleaf(83),
                      84,
                      null))));
      V.root = mknode(mknode(mknode(mknode(null,
          15,
          mkleaf(16)),
          18,
          mknode(mkleaf(20),
              23,
              mkleaf(24))),
          33,
          mknode(mknode(mkleaf(34),
              35,
              mkleaf(37)),
              40,
              mknode(mkleaf(42),
                  47,
                  mkleaf(48)))),
          49,
          mknode(mknode(mknode(null,
              52,
              mkleaf(61)),
              64,
              mkleaf(74)),
              75,
              mknode(mkleaf(77),
                  83,
                  mknode(mkleaf(84),
                      93,
                      null))));
      expect(false, AVL.equalSet(T, V));
    }
  }

  // 1st tree is a proper superset of 2nd tree. Answer is "false".
  public static class Super2 implements Test {
    public void test() {
      AVL<Integer> T, V;

      // 2nd tree is missing just one key.
      T = new AVL<Integer>();
      V = new AVL<Integer>();
      T.root = mknode(mknode(mknode(mkleaf(15),
          16,
          mkleaf(18)),
          20,
          mknode(mknode(mkleaf(23),
              24,
              mkleaf(33)),
              34,
              mkleaf(35))),
          37,
          mknode(mknode(mknode(mkleaf(40),
              42,
              null),
              47,
              mknode(mkleaf(48),
                  49,
                  mkleaf(52))),
              61,
              mknode(mknode(mkleaf(64),
                  74,
                  mkleaf(75)),
                  77,
                  mknode(mkleaf(83),
                      84,
                      null))));
      V.root = mknode(mknode(mknode(mknode(null,
          15,
          mkleaf(16)),
          18,
          mknode(mkleaf(20),
              23,
              mkleaf(24))),
          33,
          mknode(mknode(mkleaf(34),
              35,
              mkleaf(37)),
              40,
              mknode(mkleaf(42),
                  47,
                  mkleaf(48)))),
          49,
          mknode(mknode(mknode(null,
              52,
              mkleaf(61)),
              64,
              mkleaf(74)),
              75,
              mknode(mkleaf(77),
                  83,
                  mknode(mkleaf(84),
                      93,
                      null))));
      expect(false, AVL.equalSet(V, T));
    }
  }

  // The two trees have common keys but also different keys. False.
  public static class Common implements Test {
    public void test() {
      AVL<Integer> T, V;
      T = new AVL<Integer>();
      V = new AVL<Integer>();
      T.root = mknode(mknode(mknode(mknode(null,
          19,
          mkleaf(21)),
          23,
          mkleaf(24)),
          27,
          mknode(mknode(mkleaf(32),
              34,
              mkleaf(37)),
              43,
              mkleaf(44))),
          47,
          mknode(mknode(mknode(mkleaf(51),
              52,
              mkleaf(54)),
              58,
              mknode(mkleaf(62),
                  63,
                  mkleaf(65))),
              68,
              mknode(mkleaf(78),
                  84,
                  mknode(mkleaf(86),
                      96,
                      mkleaf(97)))));
      V.root = mknode(mknode(mknode(mknode(mkleaf(14),
          19,
          null),
          21,
          mknode(mkleaf(23),
              24,
              mkleaf(32))),
          34,
          mknode(mknode(null,
              37,
              mkleaf(38)),
              40,
              mknode(mkleaf(41),
                  43,
                  null))),
          47,
          mknode(mknode(mknode(null,
              51,
              mkleaf(52)),
              54,
              mknode(null,
                  58,
                  mkleaf(59))),
              62,
              mknode(mknode(null,
                  63,
                  mkleaf(65)),
                  68,
                  mknode(mkleaf(84),
                      96,
                      mkleaf(97)))));
      expect(false, AVL.equalSet(T, V));
    }
  }

  // The two trees are disjoint. Nothing in common. False.
  public static class Disjoint implements Test {
    public void test() {
      AVL<Integer> T, V;
      T = new AVL<Integer>();
      V = new AVL<Integer>();
      T.root = mknode(mknode(mknode(mkleaf(7),
          9,
          mkleaf(10)),
          11,
          mknode(mkleaf(12),
              13,
              mkleaf(14))),
          18,
          mknode(mknode(mknode(mkleaf(20),
              21,
              null),
              22,
              mknode(mkleaf(23),
                  24,
                  null)),
              29,
              mknode(mknode(null,
                  30,
                  mkleaf(32)),
                  34,
                  mknode(mkleaf(37),
                      39,
                      mkleaf(40)))));
      V.root = mknode(mknode(mknode(mknode(null,
          1,
          mkleaf(2)),
          3,
          mknode(mkleaf(4),
              5,
              null)),
          6,
          mknode(mkleaf(8),
              15,
              mknode(mkleaf(16),
                  17,
                  null))),
          19,
          mknode(mknode(mkleaf(25),
              26,
              mknode(null,
                  27,
                  mkleaf(28))),
              31,
              mknode(mkleaf(33),
                  35,
                  mknode(null,
                      36,
                      mkleaf(38)))));
      expect(false, AVL.equalSet(T, V));
    }
  }
}
