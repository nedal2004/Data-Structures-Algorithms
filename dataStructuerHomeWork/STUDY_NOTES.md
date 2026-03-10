# ملاحظات دراسية مهمة - Data Structures 📖

## 1. مفاهيم أساسية في الأشجار الثنائية

### ما هي الشجرة الثنائية (Binary Tree)؟
- هيكل بيانات يتكون من عقد (nodes)
- كل عقدة لها **على الأكثر** ابنين (left & right)
- يوجد جذر واحد (root) في الأعلى
- العقد التي ليس لها أبناء تسمى أوراق (leaves)

### المصطلحات المهمة:
```
        1           ← Root (الجذر)
       / \
      2   3         ← Internal Nodes (عقد داخلية)
     / \   \
    4   5   7       ← Leaves (أوراق - ليس لها أبناء)
```

- **Parent (الأب):** العقدة التي فوق عقدة معينة
  - مثال: 2 هو parent لـ 4 و 5

- **Child (الابن):** العقدة التي تحت عقدة معينة
  - مثال: 4 و 5 هما children لـ 2

- **Sibling (الأخوة):** عقد لها نفس الأب
  - مثال: 4 و 5 siblings

- **Level (المستوى):** المسافة من الجذر
  - Root في level 0
  - أبناء Root في level 1
  - وهكذا...

- **Height (الارتفاع):** أطول مسار من الجذر إلى ورقة
  - في المثال أعلاه: height = 2

- **Depth (العمق):** المسافة من الجذر إلى عقدة معينة
  - depth(4) = 2

---

## 2. Binary Search Tree (BST)

### القاعدة الأساسية:
```
        10
       /  \
      5    15      ← كل ما في اليسار < 10
     / \   / \        كل ما في اليمين > 10
    3   7 13  18
```

**شرط BST:**
- قيمة كل عقدة **أكبر** من جميع العقد في فرعها الأيسر
- قيمة كل عقدة **أصغر** من جميع العقد في فرعها الأيمن
- لا توجد قيم مكررة

### لماذا نستخدم BST؟
- البحث سريع: O(log n) في المتوسط
- الإدراج سريع: O(log n) في المتوسط
- الحذف سريع: O(log n) في المتوسط

### أسوأ حالة لـ BST:
```
    1          ← هذا BST لكنه مثل Linked List!
     \            Time Complexity = O(n)
      2
       \
        3
         \
          4
```

---

## 3. أنواع الـ Tree Traversal

### أ) Preorder (Root → Left → Right)
```java
void preorder(TreeNode node) {
    if (node == null) return;
    System.out.print(node.val + " ");  // 1. زيارة الجذر
    preorder(node.left);                // 2. الفرع الأيسر
    preorder(node.right);               // 3. الفرع الأيمن
}
```
**استخدامات:**
- نسخ الشجرة
- حفظ الشجرة في ملف
- التعبيرات الرياضية (Prefix notation)

### ب) Inorder (Left → Root → Right)
```java
void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);                 // 1. الفرع الأيسر
    System.out.print(node.val + " ");   // 2. زيارة الجذر
    inorder(node.right);                // 3. الفرع الأيمن
}
```
**استخدامات:**
- طباعة BST بترتيب تصاعدي
- التعبيرات الرياضية (Infix notation)

**مثال:**
```
BST:    10
       /  \
      5    15
     
Inorder: 5, 10, 15  ← مرتب! ✓
```

### ج) Postorder (Left → Right → Root)
```java
void postorder(TreeNode node) {
    if (node == null) return;
    postorder(node.left);               // 1. الفرع الأيسر
    postorder(node.right);              // 2. الفرع الأيمن
    System.out.print(node.val + " ");   // 3. زيارة الجذر
}
```
**استخدامات:**
- حذف الشجرة (نحذف الأوراق أولاً)
- حساب حجم الشجرة
- التعبيرات الرياضية (Postfix notation)

### د) Level Order (مستوى بمستوى)
```java
void levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.print(node.val + " ");
        
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```
**استخدامات:**
- إيجاد أقصر مسار
- طباعة الشجرة مستوى بمستوى
- BFS (Breadth-First Search)

---

## 4. الفرق بين DFS و BFS

### DFS (Depth-First Search):
- يستخدم **Stack** (أو Recursion)
- يذهب للعمق أولاً
- أنواعه: Preorder, Inorder, Postorder

### BFS (Breadth-First Search):
- يستخدم **Queue**
- يزور المستوى كاملاً قبل الانتقال للمستوى التالي
- مثاله: Level Order Traversal

---

## 5. Recursion (التعاودية)

### البنية الأساسية:
```java
returnType functionName(parameters) {
    // 1. Base Case (حالة الإيقاف)
    if (baseCondition) {
        return baseValue;
    }
    
    // 2. Recursive Case (الاستدعاء التعاودي)
    return functionName(smallerProblem);
}
```

### مثال: حساب ارتفاع الشجرة
```java
int height(TreeNode node) {
    // Base case
    if (node == null) {
        return 0;
    }
    
    // Recursive case
    int leftHeight = height(node.left);
    int rightHeight = height(node.right);
    
    return 1 + Math.max(leftHeight, rightHeight);
}
```

### نصائح مهمة:
1. **لازم يكون فيه Base Case** وإلا ستحصل على Stack Overflow
2. **المشكلة يجب أن تصغر** في كل استدعاء
3. **الثقة بالـ Recursion:** افترض أن الدالة تعمل للمشاكل الأصغر

---

## 6. Queue في Java

```java
// إنشاء Queue
Queue<Integer> queue = new LinkedList<>();

// إضافة عنصر
queue.offer(5);      // أو queue.add(5)

// إزالة وإرجاع أول عنصر
int first = queue.poll();  // يرجع null إذا فارغة
// أو
int first = queue.remove(); // يرمي Exception إذا فارغة

// رؤية أول عنصر بدون إزالة
int peek = queue.peek();

// التحقق من الفراغ
boolean empty = queue.isEmpty();

// الحجم
int size = queue.size();
```

### مثال على استخدام Queue:
```java
Queue<Integer> q = new LinkedList<>();
q.offer(1);
q.offer(2);
q.offer(3);

while (!q.isEmpty()) {
    System.out.println(q.poll());
}
// Output: 1, 2, 3  ← FIFO (First In First Out)
```

---

## 7. HashMap في Java

```java
// إنشاء HashMap
HashMap<Integer, Integer> map = new HashMap<>();

// إضافة عنصر
map.put(5, 10);      // Key: 5, Value: 10

// الحصول على قيمة
int value = map.get(5);  // يرجع 10

// التحقق من وجود Key
boolean exists = map.containsKey(5);  // true

// التحقق من وجود Value
boolean exists = map.containsValue(10); // true

// إزالة عنصر
map.remove(5);

// الحجم
int size = map.size();
```

### لماذا نستخدم HashMap؟
- البحث سريع جداً: O(1) في المتوسط
- بدلاً من البحث في مصفوفة O(n)، نستخدم HashMap O(1)

---

## 8. Time Complexity (التعقيد الزمني)

### الترتيب من الأسرع للأبطأ:
```
O(1)        ← ثابت (الأسرع!)
O(log n)    ← لوغاريتمي (BST بحث)
O(n)        ← خطي (loop واحد)
O(n log n)  ← (Sorting algorithms)
O(n²)       ← تربيعي (nested loops)
O(2ⁿ)       ← أسي (الأبطأ!)
```

### أمثلة:
```java
// O(1) - Constant
int x = arr[0];

// O(log n) - Logarithmic
// BST search, Binary search

// O(n) - Linear
for (int i = 0; i < n; i++) {
    System.out.println(i);
}

// O(n log n) - Merge Sort, Quick Sort

// O(n²) - Quadratic
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        System.out.println(i + j);
    }
}
```

---

## 9. Space Complexity (التعقيد المكاني)

### أمثلة:
```java
// O(1) - مساحة ثابتة
int sum = 0;
for (int i = 0; i < n; i++) {
    sum += i;
}

// O(n) - مساحة خطية
int[] arr = new int[n];

// O(h) - ارتفاع الشجرة (Recursion stack)
int height(TreeNode node) {
    if (node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
}
```

---

## 10. نصائح للامتحانات 🎯

### 1. ارسم دائماً:
- ارسم الشجرة على ورقة
- تابع الخوارزمية خطوة بخطوة
- لا تحل ذهنياً فقط!

### 2. تحقق من الحالات الخاصة:
- شجرة فارغة (null)
- شجرة بعقدة واحدة
- شجرة غير متوازنة

### 3. اكتب الـ Base Case أولاً:
```java
if (node == null) {
    return /* something */;
}
```

### 4. افهم الفرق بين:
- Binary Tree و BST
- DFS و BFS
- Preorder و Postorder و Inorder

### 5. تدرب على:
- كتابة الكود من الذاكرة
- شرح الخوارزمية بكلماتك الخاصة
- حساب Time Complexity

---

## 11. أخطاء شائعة وكيفية تجنبها ⚠️

### ❌ خطأ 1: نسيان Base Case
```java
// خطأ! سيحصل Stack Overflow
int height(TreeNode node) {
    return 1 + Math.max(height(node.left), height(node.right));
}

// ✓ صحيح
int height(TreeNode node) {
    if (node == null) return 0;  // Base Case
    return 1 + Math.max(height(node.left), height(node.right));
}
```

### ❌ خطأ 2: نسيان التحقق من null
```java
// خطأ! قد يحصل NullPointerException
if (node.left.val > 5) { ... }

// ✓ صحيح
if (node.left != null && node.left.val > 5) { ... }
```

### ❌ خطأ 3: الخلط بين left و right في BST
```java
// خطأ في BST insert
if (val < node.val) {
    node.right = insert(node.right, val);  // خطأ!
}

// ✓ صحيح
if (val < node.val) {
    node.left = insert(node.left, val);
}
```

---

## 12. مراجعة سريعة قبل الامتحان ⚡

### يجب أن تعرف:
✅ كيف تبني شجرة من مصفوفة  
✅ كيف تعمل Level Order Traversal  
✅ كيف تتحقق من BST  
✅ كيف تبحث في BST  
✅ كيف تدرج في BST  
✅ ما هو LCA وكيف تجده  
✅ كيف تبني شجرة من Preorder + Inorder  
✅ الفرق بين DFS و BFS  
✅ متى تستخدم Queue ومتى تستخدم Recursion  
✅ Time Complexity لكل خوارزمية  

---

## بالتوفيق! 🌟
تذكر: الممارسة والرسم هما المفتاح للفهم! 🚀
