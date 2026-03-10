import java.util.*;

/**
 * Mission 2: Forest Gate Check (Validate BST Property)
 * 
 * الشرح:
 * - نتحقق هل الشجرة BST صحيح أم لا
 * - BST: كل عقدة في الفرع الأيسر أصغر منها، وكل عقدة في الفرع الأيمن أكبر منها
 * - نستخدم طريقة Recursive مع تمرير min و max لكل عقدة
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

public class Mission2_ValidateBST {
    
    /**
     * بناء الشجرة من مصفوفة level-order
     */
    public static TreeNode buildTree(int[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == -1) {
            return null;
        }
        
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();
            
            // الابن الأيسر
            if (i < arr.length && arr[i] != -1) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;
            
            // الابن الأيمن
            if (i < arr.length && arr[i] != -1) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }
        
        return root;
    }
    
    /**
     * التحقق من صحة BST
     * @param root جذر الشجرة
     * @return true إذا كانت BST صحيح، false خلاف ذلك
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    /**
     * دالة مساعدة للتحقق من BST
     * @param node العقدة الحالية
     * @param min أصغر قيمة مسموحة
     * @param max أكبر قيمة مسموحة
     * @return true إذا كانت العقدة وأبناؤها صحيحين
     */
    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        // حالة الإيقاف: وصلنا لـ null
        if (node == null) {
            return true;
        }
        
        // تحقق: هل القيمة الحالية ضمن النطاق المسموح؟
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        // تحقق من الفرع الأيسر: كل القيم يجب أن تكون أصغر من node.val
        // تحقق من الفرع الأيمن: كل القيم يجب أن تكون أكبر من node.val
        return isValidBSTHelper(node.left, min, node.val) && 
               isValidBSTHelper(node.right, node.val, max);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // قراءة المدخلات
        int n = scanner.nextInt();
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        // بناء الشجرة
        TreeNode root = buildTree(arr);
        
        // التحقق وطباعة النتيجة
        if (isValidBST(root)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        
        scanner.close();
    }
}

/*
مثال التشغيل:
المدخلات:
7
5 1 7 -1 -1 6 8

الشجرة:
      5
     / \
    1   7
       / \
      6   8

المخرجات:
YES

شرح الخوارزمية:
1. نستخدم Recursion للتحقق من كل عقدة
2. كل عقدة يجب أن تكون ضمن نطاق [min, max]
3. الفرع الأيسر: [min, node.val)
4. الفرع الأيمن: (node.val, max]
5. إذا أي عقدة خارج النطاق، الشجرة ليست BST

مثال على الفشل:
      5
     / \
    1   7
       / \
      8   6  <- خطأ! 8 > 7 لكن 8 يجب أن يكون < 5 (لأنه في الفرع الأيسر من الشجرة الكبرى)
*/
