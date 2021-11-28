## 实验2_Android界面布局——实验报告

### 一、线性布局

* 用<LinearLayout>创建线性布局。
* 在网上找了一个drawable资源，给<Textview>加了一个带边框的background。
* 在<LinearLayout>里嵌套<LinearLayout>，然后通过<LinearLayout>里的padding和<Textview>里的layout_margin控制距离，配合layout_weight调整<Textview>的占比，就可完成所需要的效果。

 * 实现的效果：

   ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab2/1.png)

 * 关键代码：第一行的效果代码

   ```xml
   <LinearLayout
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:orientation="horizontal"
           android:padding="1dp">
   
           <TextView
               android:layout_width="0dp"
               android:layout_height="30dp"
               android:gravity="center"
               android:layout_weight="1"
               android:text="@string/oneone"
               android:textSize="15sp"
               android:background="@drawable/setbar_bg"
               android:textColor="@color/black"
               android:layout_margin="1dp"/>
   
           <TextView
               android:layout_width="0dp"
               android:layout_height="30dp"
               android:gravity="center"
               android:layout_weight="1.5"
               android:text="@string/onetwo"
               android:textSize="15sp"
               android:background="@drawable/setbar_bg"
               android:textColor="@color/black"
               android:layout_margin="1dp"/>
   
           <TextView
               android:layout_width="0dp"
               android:layout_height="30dp"
               android:gravity="center"
               android:layout_weight="1"
               android:text="@string/onethree"
               android:textSize="15sp"
               android:background="@drawable/setbar_bg"
               android:textColor="@color/black"
               android:layout_margin="1dp"/>
   
           <TextView
               android:layout_width="0dp"
               android:layout_height="30dp"
               android:gravity="center"
               android:layout_weight="1"
               android:text="@string/onefour"
               android:textSize="15sp"
               android:background="@drawable/setbar_bg"
               android:textColor="@color/black"
               android:layout_margin="1dp"/>
   
       </LinearLayout>
   ```

   

### 二、约束布局

* 用默认的布局进行操作，默认为约束布局。
* input框和结果框通过代码展现出样子，然后通过Design模式来调动位置。
* 数字按钮及运算按钮的实现：在Design模式下，先从左上角拖出4个<Textview>，调整好大小与输入内容，并将Vertical Bias都调到合适的同一值，然后将这4个<Textview>都以parent为约束，但是相邻的两个之间要形成约束，并将这4个<Textview>的上下左右margin都调成0，最后选中这4个<Textview>，右键，移动到Chains选项，选择Create Horizontal Chain，这4个<Textview>就会自动排成间距一样的一排，剩下的3排也按照此步骤。

 * 实现的效果：

   ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab2/2.png)

 * 关键代码：数字和运算按钮的第一行的效果代码

   ```xml
   <TextView
           android:id="@+id/textView3"
           android:layout_width="74dp"
           android:layout_height="40dp"
           android:gravity="center"
           android:text="@string/seven"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toStartOf="@+id/textView4"
           app:layout_constraintHorizontal_bias="0.5"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toTopOf="parent"
           app:layout_constraintVertical_bias="0.35000002"
           android:background="#D8D8D8"/>
   
       <TextView
           android:id="@+id/textView4"
           android:layout_width="74dp"
           android:layout_height="40dp"
           android:gravity="center"
           android:text="@string/eight"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toStartOf="@+id/textView5"
           app:layout_constraintHorizontal_bias="0.5"
           app:layout_constraintStart_toEndOf="@+id/textView3"
           app:layout_constraintTop_toTopOf="parent"
           app:layout_constraintVertical_bias="0.35"
           android:background="#D8D8D8"/>
   
       <TextView
           android:id="@+id/textView5"
           android:layout_width="74dp"
           android:layout_height="40dp"
           android:gravity="center"
           android:text="@string/nine"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toStartOf="@+id/textView6"
           app:layout_constraintHorizontal_bias="0.5"
           app:layout_constraintStart_toEndOf="@+id/textView4"
           app:layout_constraintTop_toTopOf="parent"
           app:layout_constraintVertical_bias="0.35"
           android:background="#D8D8D8"/>
   
       <TextView
           android:id="@+id/textView6"
           android:layout_width="74dp"
           android:layout_height="40dp"
           android:gravity="center"
           android:text="@string/chuhao"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintHorizontal_bias="0.5"
           app:layout_constraintStart_toEndOf="@+id/textView5"
           app:layout_constraintTop_toTopOf="parent"
           app:layout_constraintVertical_bias="0.35"
           android:background="#D8D8D8"/>
   ```

   

### 三、表格布局

* 用<TableLayout>创建表格布局。
* 在<TableLayout>里使用<TableRow>，再在<TableRow>里创建<Textview>来实现效果。
* 使用layout_height = 3dp的<Textview>来实现分隔线，对每个<TableRow>进行分隔。

 * 实现的效果：

   ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab2/3.png)

 * 关键代码：第三行的效果代码

   ```xml
   <TableRow>
   
           <TextView
               android:layout_width="30dp"
               android:layout_height="wrap_content"
               android:text=""
               android:textSize="30sp" />
   
           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="@string/saveas"
               android:textSize="30sp"/>
   
           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="@string/ctrlshifts"
               android:textSize="30sp"
               android:layout_gravity="right"/>
   
       </TableRow>
   ```

   
