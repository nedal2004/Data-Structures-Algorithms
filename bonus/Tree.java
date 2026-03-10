import java.util.Scanner;

public class Tree {

    static Scanner input = new Scanner(System.in);

    // ================= NODE =================
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // ================= MAIN MENU =================
    public static void main(String[] args) {

        int choice;

        do {
            printMenu();
            choice = input.nextInt();

            switch (choice) {
                case 1 -> mission1();
                case 2 -> mission2();
                case 3 -> mission3();
                case 4 -> mission4();
                case 5 -> mission5();
                case 6 -> System.out.println("\nProgram terminated successfully.");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);
    }

    static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("        TREE PROJECT");
        System.out.println("==============================");
        System.out.println("1. Level Order Traversal");
        System.out.println("2. Validate BST");
        System.out.println("3. Range Sum in BST");
        System.out.println("4. Lowest Common Ancestor");
        System.out.println("5. Build Tree from Traversals");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

    // ================= MISSION 1 =================
    static void mission1() {
        System.out.println("\n--- Mission 1: Level Order Traversal ---");

        int n = readSize();
        int[] arr = readArray(n);

        TreeNode root = buildTree(arr);

        System.out.println("\nLevel Order Output:");
        levelOrder(root);
    }

    static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedQueue<>();
        q.enqueue(root);

        int i = 1;

        while (i < arr.length) {
            TreeNode current = q.dequeue();
            if (current == null) break;

            if (i < arr.length && arr[i] != -1) {
                current.left = new TreeNode(arr[i]);
                q.enqueue(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                current.right = new TreeNode(arr[i]);
                q.enqueue(current.right);
            }
            i++;
        }

        return root;
    }

    static void levelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode> q = new LinkedQueue<>();
        q.enqueue(root);
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            System.out.print("Level " + level + " -> ");

            for (int i = 0; i < size; i++) {
                TreeNode node = q.dequeue();
                System.out.print(node.val + " ");

                if (node.left != null) q.enqueue(node.left);
                if (node.right != null) q.enqueue(node.right);
            }
            System.out.println();
            level++;
        }
    }

    // ================= MISSION 2 =================
    static void mission2() {
        System.out.println("\n--- Mission 2: Validate BST ---");

        int n = readSize();
        int[] arr = readArray(n);

        TreeNode root = buildTree(arr);

        System.out.println("\nResult:");
        System.out.println(isBST(root) ? "YES - Valid BST" : "NO - Not a BST");
    }

    static boolean isBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean check(TreeNode node, long min, long max) {
        if (node == null) return true;
//  تتاكد من ان min اقل من ال node والماكس اعلى منها
        if (node.val <= min || node.val >= max)
            return false;

        return check(node.left, min, node.val) &&
               check(node.right, node.val, max);
    }

    // ================= MISSION 3 =================
    static void mission3() {
        System.out.println("\n--- Mission 3: Range Sum in BST ---");

        int n = readSize();
        TreeNode root = null;

        System.out.println("Enter values to insert into BST:");
        for (int i = 0; i < n; i++)
            root = insert(root, input.nextInt());

        System.out.print("Enter L: ");
        int L = input.nextInt();

        System.out.print("Enter R: ");
        int R = input.nextInt();

        int result = rangeSum(root, L, R);

        System.out.println("\nTotal Sum in Range [" + L + ", " + R + "] = " + result);
    }

    static TreeNode insert(TreeNode root, int v) {
        if (root == null) return new TreeNode(v);

        if (v < root.val)
            root.left = insert(root.left, v);
        else
            root.right = insert(root.right, v);

        return root;
    }

    static int rangeSum(TreeNode root, int L, int R) {
        if (root == null) return 0;

        if (root.val < L)
            return rangeSum(root.right, L, R);

        if (root.val > R)
            return rangeSum(root.left, L, R);

        return root.val +
               rangeSum(root.left, L, R) +
               rangeSum(root.right, L, R);
    }

    // ================= MISSION 4 =================
    static void mission4() {
        System.out.println("\n--- Mission 4: Lowest Common Ancestor ---");

        int n = readSize();
        TreeNode root = null;

        System.out.println("Enter values to insert into BST:");
        for (int i = 0; i < n; i++)
            root = insert(root, input.nextInt());

        System.out.print("Enter first value (p): ");
        int p = input.nextInt();

        System.out.print("Enter second value (q): ");
        int q = input.nextInt();

        TreeNode result = lowestCommonAncestor(root, p, q);

        if (result != null)
            System.out.println("\nLCA = " + result.val);
        else
            System.out.println("Values not found in tree.");
    }

    static TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) return null;
// اذا كانت القيمتين اصغر من قيمة الroot يعني ان السلف موجود في الجهة اليسار 
        if (p < root.val && q < root.val)
            return lowestCommonAncestor(root.left, p, q);
// اذا كانت القيمتين اكبر من قيمة الroot يعني ان السلف موجود في الجهة اليمين 
        if (p > root.val && q > root.val)
            return lowestCommonAncestor(root.right, p, q);

        return root;
    }

    // ================= MISSION 5 =================
    static void mission5() {
        System.out.println("\n--- Mission 5: Build Tree from Traversals ---");

        int n = readSize();

        int[] pre = new int[n];
        int[] in = new int[n];

        System.out.println("Enter Preorder:");
        for (int i = 0; i < n; i++)
            pre[i] = input.nextInt();

        System.out.println("Enter Inorder:");
        for (int i = 0; i < n; i++)
            in[i] = input.nextInt();

        TreeNode root = build(pre, 0, n - 1, in, 0, n - 1);

        System.out.println("\nPostorder Output:");
        postorder(root);
        System.out.println();
    }

    static TreeNode build(int[] pre, int ps, int pe,
                          int[] in, int is, int ie) {

        if (ps > pe || is > ie)
            return null;

        TreeNode root = new TreeNode(pre[ps]);

        int idx = -1;
        for (int i = is; i <= ie; i++) {
            if (in[i] == pre[ps]) {
                idx = i;
                break;
            }
        }

        if (idx == -1)
            return null;

        int leftSize = idx - is;

        root.left = build(pre, ps + 1, ps + leftSize,
                          in, is, idx - 1);

        root.right = build(pre, ps + leftSize + 1, pe,
                           in, idx + 1, ie);

        return root;
    }

    static void postorder(TreeNode root) {
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    // ================= UTILITIES =================
    static int readSize() {
        System.out.print("Enter number of elements: ");
        return input.nextInt();
    }

    static int[] readArray(int n) {
        int[] arr = new int[n];
        System.out.println("Enter values:");
        for (int i = 0; i < n; i++)
            arr[i] = input.nextInt();
        return arr;
    }
}
