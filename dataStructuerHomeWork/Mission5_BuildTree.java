import java.util.*;

/**
 * Mission 5: Archaeology Reconstruction (Build Tree from Traversals)
 * 
 * الشرح:
 * - لدينا Preorder و Inorder traversal
 * - نريد إعادة بناء الشجرة وطباعة Postorder
 * 
 * Preorder: Root → Left → Right
 * Inorder: Left → Root → Right
 * Postorder: Left → Right → Root
 * 
 * الفكرة:
 * - أول عنصر في Preorder هو الجذر
 * - نبحث عن الجذر في Inorder لتقسيم الشجرة لفرع أيسر وأيمن
 * - نكرر العملية بشكل تعاودي
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

public class Mission5_BuildTree {
    
    // HashMap لتسريع البحث عن موقع العنصر في Inorder
    private static HashMap<Integer, Integer> inorderIndexMap;
    private static int preorderIndex;
    
    /**
     * بناء الشجرة من Preorder و Inorder
     * @param preorder مصفوفة Preorder
     * @param inorder مصفوفة Inorder
     * @return جذر الشجرة
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderIndexMap = new HashMap<>();
        preorderIndex = 0;
        
        // إنشاء HashMap لتسريع البحث في Inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }
    
    /**
     * دالة مساعدة لبناء الشجرة
     * @param preorder مصفوفة Preorder
     * @param inStart بداية الفرع الحالي في Inorder
     * @param inEnd نهاية الفرع الحالي في Inorder
     * @return جذر الفرع الحالي
     */
    private static TreeNode buildTreeHelper(int[] preorder, int inStart, int inEnd) {
        // حالة الإيقاف
        if (inStart > inEnd) {
            return null;
        }
        
        // أول عنصر في Preorder هو الجذر
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        
        // إذا لم يكن هناك أبناء، نرجع الجذر
        if (inStart == inEnd) {
            return root;
        }
        
        // ابحث عن موقع الجذر في Inorder
        int inorderRootIndex = inorderIndexMap.get(rootValue);
        
        // بناء الفرع الأيسر (كل العناصر قبل الجذر في Inorder)
        root.left = buildTreeHelper(preorder, inStart, inorderRootIndex - 1);
        
        // بناء الفرع الأيمن (كل العناصر بعد الجذر في Inorder)
        root.right = buildTreeHelper(preorder, inorderRootIndex + 1, inEnd);
        
        return root;
    }
    
    /**
     * طباعة Postorder traversal
     * @param root جذر الشجرة
     * @param result قائمة لتخزين النتيجة
     */
    public static void postorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        
        // Left → Right → Root
        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);
        result.add(root.val);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // قراءة عدد العناصر
        int n = scanner.nextInt();
        
        // قراءة Preorder
        int[] preorder = new int[n];
        for (int i = 0; i < n; i++) {
            preorder[i] = scanner.nextInt();
        }
        
        // قراءة Inorder
        int[] inorder = new int[n];
        for (int i = 0; i < n; i++) {
            inorder[i] = scanner.nextInt();
        }
        
        // بناء الشجرة
        TreeNode root = buildTree(preorder, inorder);
        
        // الحصول على Postorder
        List<Integer> postorder = new ArrayList<>();
        postorderTraversal(root, postorder);
        
        // طباعة النتيجة
        for (int i = 0; i < postorder.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(postorder.get(i));
        }
        System.out.println();
        
        scanner.close();
    }
}

/*
مثال التشغيل:
المدخلات:
5
3 9 20 15 7
9 3 15 20 7

خطوات البناء:
1. Preorder: [3, 9, 20, 15, 7]
   Inorder:  [9, 3, 15, 20, 7]
   
   - أول عنصر في Preorder = 3 (الجذر)
   - نبحث عن 3 في Inorder → موقعه: index 1
   - الفرع الأيسر: [9] (index 0 إلى 0)
   - الفرع الأيمن: [15, 20, 7] (index 2 إلى 4)

2. بناء الفرع الأيسر:
   Preorder: [9]
   Inorder:  [9]
   - 9 هو ورقة (leaf)

3. بناء الفرع الأيمن:
   Preorder: [20, 15, 7]
   Inorder:  [15, 20, 7]
   
   - الجذر = 20
   - الفرع الأيسر: [15]
   - الفرع الأيمن: [7]

الشجرة النهائية:
    3
   / \
  9   20
     /  \
    15   7

Postorder: Left → Right → Root
- زيارة 9
- زيارة 15
- زيارة 7
- زيارة 20
- زيارة 3

المخرجات:
9 15 7 20 3

شرح الخوارزمية:
1. نستخدم HashMap لتسريع البحث في Inorder (O(1) بدلاً من O(n))
2. في كل خطوة:
   - نأخذ أول عنصر من Preorder كجذر
   - نبحث عنه في Inorder لتقسيم الشجرة
   - نبني الفرع الأيسر بشكل تعاودي
   - نبني الفرع الأيمن بشكل تعاودي
3. بعد بناء الشجرة، نعمل Postorder traversal
4. Time Complexity: O(n)
5. Space Complexity: O(n)
*/
