# Tree Warden Missions - دليل الاستخدام 🌳

## نظرة عامة
هذا المشروع يحتوي على حلول لـ 5 مهمات في مادة Data Structures and Algorithms Lab
جميع الحلول مكتوبة بلغة Java مع شرح تفصيلي لكل خوارزمية.

---

## المهمات الخمس

### Mission 1: Level Order Traversal
**الملف:** `Mission1_LevelOrder.java`

**الوصف:** طباعة الشجرة مستوى بمستوى

**كيفية التشغيل:**
```bash
javac Mission1_LevelOrder.java
java Mission1_LevelOrder
```

**مثال المدخلات:**
```
7
1 2 3 4 5 -1 7
```

**مثال المخرجات:**
```
Level 0: 1
Level 1: 2 3
Level 2: 4 5 7
```

---

### Mission 2: Validate BST
**الملف:** `Mission2_ValidateBST.java`

**الوصف:** التحقق من صحة Binary Search Tree

**كيفية التشغيل:**
```bash
javac Mission2_ValidateBST.java
java Mission2_ValidateBST
```

**مثال المدخلات:**
```
7
5 1 7 -1 -1 6 8
```

**مثال المخرجات:**
```
YES
```

---

### Mission 3: Range Sum Query in BST
**الملف:** `Mission3_RangeSum.java`

**الوصف:** حساب مجموع القيم في نطاق معين داخل BST

**كيفية التشغيل:**
```bash
javac Mission3_RangeSum.java
java Mission3_RangeSum
```

**مثال المدخلات:**
```
7
10 5 15 3 7 13 18
6 15
```

**مثال المخرجات:**
```
45
```

**الشرح:** القيم بين 6 و 15 هي: {7, 10, 13, 15} → المجموع = 45

---

### Mission 4: Lowest Common Ancestor
**الملف:** `Mission4_LowestCommonAncestor.java`

**الوصف:** إيجاد أقرب سلف مشترك لعقدتين في BST

**كيفية التشغيل:**
```bash
javac Mission4_LowestCommonAncestor.java
java Mission4_LowestCommonAncestor
```

**مثال المدخلات:**
```
7
6 2 8 0 4 7 9
2 8
```

**مثال المخرجات:**
```
6
```

---

### Mission 5: Build Tree from Traversals
**الملف:** `Mission5_BuildTree.java`

**الوصف:** إعادة بناء الشجرة من Preorder و Inorder، وطباعة Postorder

**كيفية التشغيل:**
```bash
javac Mission5_BuildTree.java
java Mission5_BuildTree
```

**مثال المدخلات:**
```
5
3 9 20 15 7
9 3 15 20 7
```

**مثال المخرجات:**
```
9 15 7 20 3
```

---

## ملاحظات مهمة للدراسة 📚

### 1. Tree Node Structure
```java
class TreeNode {
    int val;           // القيمة
    TreeNode left;     // الابن الأيسر
    TreeNode right;    // الابن الأيمن
}
```

### 2. أنواع الـ Traversal:
- **Preorder:** Root → Left → Right
- **Inorder:** Left → Root → Right
- **Postorder:** Left → Right → Root
- **Level Order:** مستوى بمستوى (باستخدام Queue)

### 3. خصائص BST:
- كل عقدة في الفرع الأيسر **أصغر** من الجذر
- كل عقدة في الفرع الأيمن **أكبر** من الجذر
- لا توجد قيم مكررة

### 4. Time Complexity:
- **Level Order:** O(n)
- **Validate BST:** O(n)
- **Range Sum:** O(n) في أسوأ حالة، O(log n + k) في المتوسط
- **LCA:** O(h) حيث h = ارتفاع الشجرة
- **Build Tree:** O(n)

---

## نصائح للدراسة 💡

1. **افهم المفاهيم الأساسية:**
   - ما الفرق بين Binary Tree و BST؟
   - كيف يعمل Recursion؟
   - ما هو Queue وكيف نستخدمه؟

2. **ارسم الأمثلة:**
   - ارسم الشجرة على ورقة
   - تابع الخوارزمية خطوة بخطوة
   - هذا يساعد جداً في الفهم!

3. **جرب أمثلة مختلفة:**
   - غير المدخلات
   - جرب حالات خاصة (شجرة فارغة، عقدة واحدة، إلخ)

4. **اكتب الكود بنفسك:**
   - لا تنسخ فقط
   - حاول كتابة الكود من الذاكرة
   - هذا يثبت المعلومات

5. **راجع التعقيد الزمني:**
   - افهم لماذا كل خوارزمية لها Time Complexity معينة
   - هذا مهم جداً في الامتحانات!

---

## الأسئلة الشائعة ❓

**س: لماذا نستخدم Queue في Level Order؟**
ج: لأن Queue تعمل بنظام FIFO (First In First Out)، مما يضمن معالجة العقد بترتيب مستوياتها.

**س: ما الفرق بين -1 و null في المدخلات؟**
ج: -1 يستخدم في المصفوفة للإشارة لعقدة غير موجودة، بينما null نستخدمه في الكود للإشارة لمؤشر فارغ.

**س: لماذا نستخدم HashMap في Mission 5؟**
ج: لتسريع البحث عن موقع العنصر في Inorder من O(n) إلى O(1).

**س: هل يمكن حل هذه المهمات بطرق أخرى؟**
ج: نعم! لكل مسألة عدة حلول. الحلول المقدمة هي الأكثر كفاءة وشيوعاً.

---

## للتواصل والدعم
- إذا واجهت أي مشكلة في فهم الكود
- إذا وجدت خطأ (bug)
- إذا احتجت شرح إضافي لأي جزء

يمكنك مراجعة:
- التعليقات داخل كل ملف Java
- الأمثلة الموضحة
- رسم الأشجار على ورقة ومتابعة الخوارزمية

---

## ملاحظة نهائية ⭐
هذا المشروع مصمم لمساعدتك على **الفهم والتعلم**، وليس فقط للحصول على الدرجة.
حاول فهم كل سطر من الكود، وستجد أن مادة Data Structures ممتعة جداً! 🚀

**بالتوفيق في مشروعك! 🌟**
