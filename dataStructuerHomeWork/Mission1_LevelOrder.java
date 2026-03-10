import java.util.*;

/**
 * Mission 1: Emergency Broadcast Levels (Level Order Traversal)
 * 
 * الشرح:
 * - نقرأ الشجرة من مصفوفة level-order
 * - نطبع كل مستوى في سطر منفصل
 * - نستخدم Queue للطباعة مستوى بمستوى
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

public class Mission1_LevelOrder {
    
    /**
     * بناء الشجرة من مصفوفة level-order
     * @param arr المصفوفة التي تحتوي على قيم الشجرة (-1 يعني null)
     * @return جذر الشجرة
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
            
            // إضافة الابن الأيسر
            if (i < arr.length && arr[i] != -1) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;
            
            // إضافة الابن الأيمن
            if (i < arr.length && arr[i] != -1) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }
        
        return root;
    }
    
    /**
     * طباعة الشجرة مستوى بمستوى
     * @param root جذر الشجرة
     */
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // عدد العقد في المستوى الحالي
            System.out.print("Level " + level + ":");
            
            // طباعة جميع العقد في المستوى الحالي
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(" " + current.val);
                
                // إضافة الأبناء للقائمة
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            System.out.println();
            level++;
        }
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
        
        // طباعة النتيجة
        printLevelOrder(root);
        
        scanner.close();
    }
}

/*
مثال التشغيل:
المدخلات:
7
1 2 3 4 5 -1 7

المخرجات:
Level 0: 1
Level 1: 2 3
Level 2: 4 5 7

شرح الخوارزمية:
1. نبني الشجرة من المصفوفة باستخدام Queue
2. نستخدم BFS (Breadth-First Search) للطباعة مستوى بمستوى
3. في كل مستوى، نطبع جميع العقد ثم ننتقل للمستوى التالي
*/
