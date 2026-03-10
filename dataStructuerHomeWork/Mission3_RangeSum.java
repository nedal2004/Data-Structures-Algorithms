import java.util.*;

/**
 * Mission 3: Drone Delivery Zone Sum (Range Sum Query in BST)
 * 
 * الشرح:
 * - لدينا BST ونريد مجموع كل القيم بين L و R
 * - نستخدم خاصية BST لتقليل عدد العقد التي نزورها
 * - إذا كانت العقدة أكبر من R، لا نبحث في الفرع الأيمن
 * - إذا كانت العقدة أصغر من L، لا نبحث في الفرع الأيسر
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

public class Mission3_RangeSum {
    
    /**
     * بناء BST من مصفوفة قيم (إدراج بالترتيب)
     * @param values القيم المراد إدراجها
     * @return جذر الشجرة
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
     * @param root جذر الشجرة الحالية
     * @param val القيمة المراد إدراجها
     * @return جذر الشجرة بعد الإدراج
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
     * حساب مجموع القيم بين L و R في BST
     * @param root جذر الشجرة
     * @param L الحد الأدنى
     * @param R الحد الأعلى
     * @return مجموع القيم في النطاق [L, R]
     */
    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        
        // إذا كانت القيمة الحالية ضمن النطاق، أضفها للمجموع
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }
        
        // إذا كانت القيمة الحالية أكبر من L، ابحث في الفرع الأيسر
        if (root.val > L) {
            sum += rangeSumBST(root.left, L, R);
        }
        
        // إذا كانت القيمة الحالية أصغر من R، ابحث في الفرع الأيمن
        if (root.val < R) {
            sum += rangeSumBST(root.right, L, R);
        }
        
        return sum;
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
        
        // قراءة النطاق [L, R]
        int L = scanner.nextInt();
        int R = scanner.nextInt();
        
        // بناء BST
        TreeNode root = buildBST(values);
        
        // حساب وطباعة المجموع
        int result = rangeSumBST(root, L, R);
        System.out.println(result);
        
        scanner.close();
    }
}

/*
مثال التشغيل:
المدخلات:
7
10 5 15 3 7 13 18
6 15

بناء BST (بالإدراج بالترتيب):
        10
       /  \
      5    15
     / \   / \
    3   7 13  18

القيم في النطاق [6, 15]:
- 3: لا (أصغر من 6)
- 5: لا (أصغر من 6)
- 7: نعم ✓
- 10: نعم ✓
- 13: نعم ✓
- 15: نعم ✓
- 18: لا (أكبر من 15)

المجموع: 7 + 10 + 13 + 15 = 45

المخرجات:
45

شرح الخوارزمية:
1. نبني BST بإدراج القيم واحدة تلو الأخرى
2. نستخدم Recursion للبحث في الشجرة
3. في كل عقدة:
   - إذا كانت ضمن [L, R]، نضيفها للمجموع
   - إذا كانت > L، نبحث في الفرع الأيسر (قد يحتوي قيم في النطاق)
   - إذا كانت < R، نبحث في الفرع الأيمن (قد يحتوي قيم في النطاق)
4. هذا أسرع من O(n) في الحالة المتوسطة لأننا نتجاهل فروع كاملة
*/
