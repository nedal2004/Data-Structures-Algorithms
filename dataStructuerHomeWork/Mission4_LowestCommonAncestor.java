import java.util.*;

/**
 * Mission 4: Nearest Shared Supervisor (Lowest Common Ancestor in BST)
 * 
 * الشرح:
 * - نبحث عن أقرب سلف مشترك (LCA) لعقدتين p و q في BST
 * - نستخدم خاصية BST لإيجاد LCA بسرعة
 * - إذا كانت p و q في جهتين مختلفتين من الجذر، فالجذر هو LCA
 * - إذا كانتا في نفس الجهة، نبحث في تلك الجهة
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Mission4_LowestCommonAncestor {
    
    /**
     * بناء BST من مصفوفة قيم (إدراج بالترتيب)
     */
    public static TreeNode buildBST(int[] values) {
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }
        return root;
    }
    
    /**
     * إدراج قيمة في BST
     */
    private static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        
        return root;
    }
    
    /**
     * إيجاد أقرب سلف مشترك (LCA) في BST
     * @param root جذر الشجرة
     * @param p القيمة الأولى
     * @param q القيمة الثانية
     * @return قيمة LCA
     */
    public static int lowestCommonAncestor(TreeNode root, int p, int q) {
        // تأكد أن p هي الأصغر
        if (p > q) {
            int temp = p;
            p = q;
            q = temp;
        }
        
        return findLCA(root, p, q);
    }
    
    /**
     * دالة مساعدة لإيجاد LCA
     */
    private static int findLCA(TreeNode node, int p, int q) {
        if (node == null) {
            return -1; // خطأ: لم نجد العقدة
        }
        
        // الحالة 1: p و q كلاهما في الفرع الأيسر
        if (node.val > q) {
            return findLCA(node.left, p, q);
        }
        
        // الحالة 2: p و q كلاهما في الفرع الأيمن
        if (node.val < p) {
            return findLCA(node.right, p, q);
        }
        
        // الحالة 3: p و q في جهتين مختلفتين (أو إحداهما هي الجذر)
        // إذن العقدة الحالية هي LCA
        return node.val;
    }
    
    /**
     * طريقة بديلة: Iterative (بدون Recursion)
     */
    public static int lowestCommonAncestorIterative(TreeNode root, int p, int q) {
        // تأكد أن p هي الأصغر
        if (p > q) {
            int temp = p;
            p = q;
            q = temp;
        }
        
        TreeNode current = root;
        
        while (current != null) {
            // كلاهما في الفرع الأيسر
            if (current.val > q) {
                current = current.left;
            }
            // كلاهما في الفرع الأيمن
            else if (current.val < p) {
                current = current.right;
            }
            // وجدنا LCA
            else {
                return current.val;
            }
        }
        
        return -1; // خطأ
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // قراءة عدد القيم
        int n = scanner.nextInt();
        int[] values = new int[n];
        
        // قراءة القيم
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }
        
        // قراءة p و q
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        
        // بناء BST
        TreeNode root = buildBST(values);
        
        // إيجاد وطباعة LCA
        int lca = lowestCommonAncestor(root, p, q);
        System.out.println(lca);
        
        scanner.close();
    }
}

/*
مثال التشغيل:
المدخلات:
7
6 2 8 0 4 7 9
2 8

بناء BST:
        6
       / \
      2   8
     / \  / \
    0  4 7   9

البحث عن LCA(2, 8):
- نبدأ من 6
- 2 < 6 < 8 (2 في اليسار و 8 في اليمين)
- إذن 6 هو LCA ✓

المخرجات:
6

مثال آخر:
إذا بحثنا عن LCA(0, 4):
- نبدأ من 6
- 0 < 6 و 4 < 6 (كلاهما في اليسار)
- ننتقل لـ 2
- 0 < 2 < 4 (0 في اليسار و 4 في اليمين)
- إذن 2 هو LCA ✓

شرح الخوارزمية:
1. نبني BST بإدراج القيم بالترتيب
2. نبحث عن LCA:
   - إذا كانت p و q أصغر من الجذر → ابحث في اليسار
   - إذا كانت p و q أكبر من الجذر → ابحث في اليمين
   - وإلا، الجذر الحالي هو LCA
3. Time Complexity: O(h) حيث h هو ارتفاع الشجرة
4. Space Complexity: O(1) للطريقة Iterative، O(h) للطريقة Recursive
*/
