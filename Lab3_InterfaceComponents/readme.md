## 实验3_Android界面组件——实验报告

### 一、Android Listview的用法

* 在主布局里创建一个<Listview>。
* 创建一个simple_item布局，该布局是用来显示<Listview>中的一个个item的显示效果。

* 在MainActivity里定义字符串数组和图片id数组，并将这些数据放到一个List<Map<String, Object>>中。

* 在MainActivity里用上面创建的东西来创建SimpleAdapter，然后在通过SimpleAdapter来设置Listview的Adapter。

* 在MainActivity里通过Listview的setOnItemClickListener方法来实现通过点击每一个item显示Toast信息。

* 关键代码：

  ```java
  public class MainActivity extends AppCompatActivity {
  
      private String[] name = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
      private int[] touxiang = new int[]{R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog,
                                          R.drawable.cat, R.drawable.elephant};
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          List<Map<String, Object>> listItems = new ArrayList<>();
          for(int i = 0; i < name.length; i++){
              Map<String, Object> listItem = new HashMap<>();
              listItem.put("name", name[i]);
              listItem.put("touxiang", touxiang[i]);
              listItems.add(listItem);
          }
          SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.simple_item,
                  new String[]{"name", "touxiang"}, new int[]{R.id.name, R.id.touxiang});
          ListView listview = findViewById(R.id.lv);
          listview.setAdapter(simpleAdapter);
  
          listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  
                  Toast.makeText(MainActivity.this, name[position], Toast.LENGTH_SHORT).show();
              }
          });
      }
  }
  ```

* 效果图片：

  ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab3/1.png)

  

### 二、创建自定义布局的AlertDialog

* 在主布局里创建一个Button，用来点击出现对话框。

* 创建dialog_view布局，来显示对话框的主要界面。

* 在MainActivity里设置上面创建的Button的监听，在监听里面通过AlertDialog.Builder来设置view和按钮，并显示。

* 关键代码：

  ```java
  public class MainActivity2 extends AppCompatActivity {
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main2);
          Button button = findViewById(R.id.btn);
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  View dialogview =getLayoutInflater().inflate(R.layout.dialog_view, null);
  
                  AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                  builder.setView(dialogview)
                  .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(MainActivity2.this, "点击了登录按钮", Toast.LENGTH_SHORT).show();
                      }
                  })
                  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(MainActivity2.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                      }
                  })
                  .create()
                  .show();
              }
          });
      }
  }
  ```

  * dialog_view.xml：

  ``` xml
  <?xml version="1.0" encoding="utf-8"?>
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="vertical">
  
      <ImageView
          android:id="@+id/iv"
          android:layout_width="310dp"
          android:layout_height="65dp"
          android:src="@drawable/header_logo"/>
  
      <EditText
          android:id="@+id/et1"
          android:padding="10dp"
          android:hint="@string/username"
          android:layout_width="310dp"
          android:layout_height="50dp"/>
  
      <EditText
          android:padding="10dp"
          android:id="@+id/rt2"
          android:inputType="textPassword"
          android:hint="@string/password"
          android:layout_width="310dp"
          android:layout_height="50dp"/>
  
  </LinearLayout>
  ```

  效果图片：

  ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab3/2.png)

  

### 三、使用XML定义菜单

* 在MainActivity里为字体大小和字体颜色定义好id。

* 在MainActivity里通过重写onCreateOptionsMenu方法来创建菜单，在onCreateOptionsMenu里通过menu.addSubMenu来创建具有子菜单的菜单项，通过menu.add来直接创建普通的菜单项。

* 在MainActivity里通过重写onOptionsItemSelected方法来处理点击菜单项事件。是通过获取点击的菜单项的id后进行匹配，然后在进行对应的操作。

* 关键代码：

  ``` java
  public class MainActivity3 extends AppCompatActivity {
  
      private static final int FONT_10 = 0x111;
      private static final int FONT_16 = 0x112;
      private static final int FONT_20 = 0x113;
      private static final int ORDINARY = 0x114;
      private static final int FONT_RED = 0x115;
      private static final int FONT_BLACK = 0x116;
      private TextView textView;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main3);
          textView = findViewById(R.id.tv);
      }
  
      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
          SubMenu fontMenu = menu.addSubMenu("字体大小");
          fontMenu.setHeaderTitle("选择字体大小");
          fontMenu.add(0, FONT_10, 0, "10号字体");
          fontMenu.add(0, FONT_16, 0, "16号字体");
          fontMenu.add(0, FONT_20, 0, "20号字体");
  
          menu.add(0, ORDINARY, 0, "普通菜单项");
  
          SubMenu colorMenu = menu.addSubMenu("字体颜色");
          colorMenu.setHeaderTitle("选择字体颜色");
          colorMenu.add(0, FONT_RED, 0, "红色");
          colorMenu.add(0, FONT_BLACK, 0, "黑色");
          return super.onCreateOptionsMenu(menu);
      }
  
      @Override
      public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()){
              case FONT_10:
                  textView.setTextSize(10 * 2);
                  break;
              case FONT_16:
                  textView.setTextSize(16 * 2);
                  break;
              case FONT_20:
                  textView.setTextSize(20 * 2);
                  break;
              case ORDINARY:
                  Toast.makeText(MainActivity3.this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
                  break;
              case FONT_RED:
                  textView.setTextColor(Color.RED);
                  break;
              case FONT_BLACK:
                  textView.setTextColor(Color.BLACK);
                  break;
  
          }
          return true;
      }
  }
  ```

* 效果图片：

  * 菜单界面：

    ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab3/3.界面.png)

  * 设置20号字体：

    ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab3/3.设置20号字体.png)

  * 设置红色字体：

    ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab3/3.设置红色字体.png)

  * 点击普通菜单项：

    ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab3/3.点击普通菜单项.png)

    

### 四、创建上下文操作模式（ActionMode）的上下文菜单

* 在主布局里创建一个<Listview>。

* 创建一个list_item布局，该布局是用来显示<Listview>中的一个个item的显示效果。

* 创建一个继承BaseAdapter的MyAdapter类，在MyAdapter类中通过重写getCount、getItem、getItemId、getView将传入的数据设置到对应的view上。（这里可以在MyAdapter类里创建一个ViewHolder类来提高效率）

* 在MainActivity里用所需要的数据创建MyAdapter对象，再将所创建的MyAdapter对象设置到Listview中，就能得到一个基本的视图。

* 创建actionbar_view布局，用来显示已选中item的数量。

* 创建menu.xml菜单，用来显示进入ActionMode后的actionbar的视图。

* 在MainActivity里通过Listview的setChoiceMode方法设置为可以多选的模式，通过Listview的setMultiChoiceModeListener方法来重写AbsListView.MultiChoiceModeListener里的方法来实现长按进入ActionMode，并可以进行选择多项item。

* 关键代码：

  ```java
  public class MainActivity4 extends AppCompatActivity {
  
      private String[] order = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "ten"};
      TextView count;
      View actionBarView;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main4);
  
          ListView listView = findViewById(R.id.lv);
          MyAdapter myAdapter = new MyAdapter(Arrays.asList(order), this, listView);
          listView.setAdapter(myAdapter);
  
          listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
          listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
              @Override
              public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                  updateSelectedCount();
                  mode.invalidate();
                  myAdapter.notifyDataSetChanged();
              }
  
              @Override
              public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                  mode.getMenuInflater().inflate(R.menu.menu, menu);
                  if (actionBarView == null){
                      actionBarView = LayoutInflater.from(MainActivity4.this).inflate(R.layout.actionbar_view, null);
                      count = (TextView) actionBarView.findViewById(R.id.count);
                  }
                  mode.setCustomView(actionBarView);
                  return true;
              }
  
              @Override
              public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                  return false;
              }
  
              @Override
              public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                  return false;
              }
  
              @Override
              public void onDestroyActionMode(ActionMode mode) {
                  listView.clearChoices();
              }
  
              public void updateSelectedCount(){
                  int selectedCount = listView.getCheckedItemCount();
                  count.setText(selectedCount + " ");
              }
          });
      }
  }
  ```
  ```java
  public class MyAdapter extends BaseAdapter {
  
      private List<String> data;
      private Context context;
      private ListView listView;
  
      public MyAdapter(List<String> data, Context context, ListView listView){
          this.data = data;
          this.context = context;
          this.listView = listView;
      }
  
      @Override
      public int getCount() {
          return data == null ? 0 : data.size();
      }
  
      @Override
      public Object getItem(int position) {
          return data.get(position);
      }
  
      @Override
      public long getItemId(int position) {
          return position;
      }
  
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder viewHolder;
          if(convertView == null){
              viewHolder = new ViewHolder();
              convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
              viewHolder.iv = convertView.findViewById(R.id.iv);
              viewHolder.tv = convertView.findViewById(R.id.tv);
              convertView.setTag(viewHolder);
          }else{
              viewHolder = (ViewHolder) convertView.getTag();
          }
          viewHolder.iv.setImageResource(R.drawable.cat);
          viewHolder.tv.setText(data.get(position));
          if(listView.isItemChecked(position)){
              convertView.setBackgroundColor(Color.parseColor("#00BCD4"));
          }else{
              convertView.setBackgroundColor(Color.TRANSPARENT);
          }
          return convertView;
      }
  
      private final class ViewHolder{
          ImageView iv;
          TextView tv;
      }
  }
  ```

  * actionbar_view.xml

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="horizontal">
  
      <TextView
          android:id="@+id/count"
          android:textSize="20sp"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content" />
  
      <TextView
          android:textSize="20sp"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="@string/selected" />
  
  </LinearLayout>
  ```

  * menu.xml

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">
  
      <item
          android:id="@+id/delete"
          android:icon="@drawable/ic_baseline_delete_24"
          android:title="@string/mode1"
          app:showAsAction="always"/>
  
  </menu>
  ```

* 效果图片：

  ![vavtor](https://github.com/greattt777/AndroidLab/blob/master/LabImage/Lab3/4.png)