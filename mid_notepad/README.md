# 期中实验报告——NotePad
### 实验步骤：

#### 1、导入基本框架

* 从Github上拷贝基本框架文件，根据自己可以在电脑上运行的项目修改以下文件

![image-20211128145548851](https://github.com/greattt777/AndroidLab/blob/master/LabImage/mid_notepad\1.png)

* app里的build.gradle

![image-20211128145707631](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\2.png)

* gradle-wrapper.properties

![image-20211128145921586](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\3.png)

* 项目的build.gradle

![image-20211128150202020](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\4.png)

* 修改完后，就可以进行编译，正常运行基本框架

#### 2、功能一、NoteList中显示条目增加时间戳显示

* 修改notelist_item.xml，增加用来显示时间的<TextView>，其中<ImageView>、另一个<TextView>和<LinearLayout>的修改为后续需要。

```xml
<?xml version="1.0" encoding="utf-8"?><!-- Copyright (C) 2010 The Android Open Source Project

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at
  
          http://www.apache.org/licenses/LICENSE-2.0
  
     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="100dp">

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginLeft="5dp"
        android:layout_marginTop="7dp"
        android:layout_marginRight="5dp"
        android:layout_marginBottom="3dp">

        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@drawable/item_selector"
            android:orientation="vertical">

            <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="35dp"
                android:orientation="horizontal"
                android:paddingTop="3dp"
                android:paddingBottom="3dp">

                <ImageView
                    android:layout_width="35dp"
                    android:layout_height="35dp"
                    android:paddingLeft="10dp"
                    android:src="@drawable/fileicon" />

                <TextView
                    android:id="@+id/title_view"
                    android:layout_width="wrap_content"
                    android:layout_height="match_parent"
                    android:gravity="center_vertical"
                    android:paddingLeft="10dp"
                    android:singleLine="true"
                    android:textAppearance="?android:attr/textAppearanceLarge"
                    android:textStyle="bold" />

            </LinearLayout>

            <TextView
                android:id="@+id/summary"
                android:layout_width="match_parent"
                android:layout_height="25dp"
                android:gravity="center_vertical"
                android:paddingLeft="12dp"
                android:paddingTop="3dp"
                android:paddingRight="12dp"
                android:singleLine="true" />

            <TextView
                android:id="@+id/time_view"
                android:layout_width="wrap_content"
                android:layout_height="30dp"
                android:gravity="center_vertical"
                android:paddingLeft="12sp" />

        </LinearLayout>

    </LinearLayout>

</LinearLayout>
```



* 在NoteList.class中增加“修改时间”和“笔记内容”（后续需要）的字符串

![image-20211128150926599](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\5.png)

* 在NoteList.class中获取数据的时候也增加“修改时间”和“笔记内容”（后续需要）的字符串和所需要的组件id

![image-20211128151229786](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\6.png)

* 但是此时时间显示的是一串数字，需要对其进行格式转化进行显示，在NoteList.class中添加“自定义时间的显示格式”的代码

![image-20211128151629628](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\7.png)

* 至此，就完成了在NoteList的每个条目显示时间的功能

![image-20211128152450773](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\8.png)

#### 3、功能二、添加笔记查询功能（根据标题查询）

* 修改list_options_menu.xml，其中id为search的item是该功能的关键

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <!--  This is our one standard application action (creating a new note). -->
    <item android:id="@+id/menu_add"
          android:icon="@drawable/create"
          android:title="@string/menu_add"
          android:alphabeticShortcut='a'
          android:showAsAction="always" />
    <!--  If there is currently data in the clipboard, this adds a PASTE menu item to the menu
          so that the user can paste in the data.. -->
    <item android:id="@+id/menu_paste"
          android:icon="@drawable/create"
          android:title="@string/menu_paste"
          android:alphabeticShortcut='p' />

    <item
        android:id="@+id/search"
        android:icon="@drawable/search"
        android:title="Search"
        android:actionViewClass="android.widget.SearchView"
        android:showAsAction="always" />
</menu>
```

* 在NoteList的onCreateOptionsMenu函数里添加搜索功能

```java
//搜索功能
        MenuItem mSearch = menu.findItem(R.id.search);
        SearchView mSearchView = (SearchView)mSearch.getActionView();
        mSearchView.setQueryHint("搜索");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                Cursor cursor = managedQuery(
                        getIntent().getData(),            // Use the default content URI for the provider.
                        PROJECTION,                       // Return the note ID and title for each note.
                        NotePad.Notes.COLUMN_NAME_TITLE+" like ? or "+NotePad.Notes.COLUMN_NAME_NOTE+" like ?", // No where clause, return all records.
                        new String[]{"%"+s+"%","%"+s+"%"}, // No where clause, therefore no where column values.
                        NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                );
                String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE,
                        NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
                        NotePad.Notes.COLUMN_NAME_NOTE} ;
                int[] viewIDs = { R.id.title_view, R.id.time_view, R.id.summary };//加入修改时间
                SimpleCursorAdapter adapter
                        = new SimpleCursorAdapter(
                        NotesList.this,                             // The Context for the ListView
                        R.layout.noteslist_item,          // Points to the XML for a list item
                        cursor,                           // The cursor to get items from
                        dataColumns,
                        viewIDs
                );
                // 自定义时间的显示格式
                SimpleCursorAdapter.ViewBinder viewBinder=new SimpleCursorAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Cursor cursor, int i)
                    {
                        if(cursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE)==i){

                            TextView textView = (TextView)view;
                            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.CHINA);
                            Date date=new Date(cursor.getLong(i));
                            String time=format.format(date);
                            textView.setText(time);
                            return true;
                        }
                        return false;
                    }
                };
                adapter.setViewBinder(viewBinder);
                setListAdapter(adapter);
                return false;
            }
        });
```

* 至此，就完成了根据笔记的标题进行查询的功能

![image-20211128153511237](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\9.png)

![image-20211128153620900](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\10.png)

#### 4、功能三、UI美化

* 美化NoteList

  修改notelist_item.xml（代码已在实验步骤2中），其中对于<LinearLayout>的修改较为关键，设置id为summary的<TextView>的singleLine属性为true，让多余的文本内容以”。。。“进行展示

  在drawable文件夹下添加了item_selector.xml、fillet.xml、pressed.xml。还需要在values文件夹下添加colors.xml，这个只是一个用来选择颜色的配置而已，网上一搜就有，在这就不展示代码了

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:state_pressed="false"
        android:drawable="@drawable/fillet" />

    <item
        android:state_pressed="true"
        android:drawable="@drawable/pressed" />

</selector>
```

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <!-- rectangle表示为矩形 -->

    <!-- 填充的颜色 -->
    <solid android:color="@color/white" />

    <!-- 边框的颜色和粗细 -->
    <stroke
        android:width="3dp"
        android:color="@color/silver"
        />

    <!-- android:radius 圆角的半径 -->
    <corners
        android:radius="5dp"
        />

</shape>
```

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <!-- rectangle表示为矩形 -->

    <!-- 填充的颜色 -->
    <solid android:color="@color/lightsteelblue" />

    <!-- 边框的颜色和粗细 -->
    <stroke
        android:width="1dp"
        android:color="@color/silver"
        />

    <!-- android:radius 圆角的半径 -->
    <corners
        android:radius="3dp"
        />

</shape>
```

​	在list_options_menu.xml（代码已在实验步骤2中）中修改“新建”和“搜索”的图标

​	修改AndroidManifest.xml的theme与icon

![image-20211128155822433](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\12.png)

​	完成效果，点击时会变色

![image-20211128155339193](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\11.png)

* 美化NoteEditor

  在drawable文件夹下添加noteeditor.xml

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <!-- rectangle表示为矩形 -->

    <!-- 填充的颜色 -->
    <solid android:color="@color/white" />

    <!-- 边框的颜色和粗细 -->
    <stroke
        android:width="3dp"
        android:color="@color/lightblue"
        />

    <!-- android:radius 圆角的半径 -->
    <corners
        android:radius="3dp"
        />

</shape>
```

​	在NoteEditor的布局文件note_editor.xml中设置<View>的背景

![image-20211128160410494](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\13.png)

​	修改editor_options_menu.xml，其中id为menu_speech的item为后续需要

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/menu_save"
          android:icon="@drawable/save"
          android:alphabeticShortcut='s'
          android:title="@string/menu_save"
          android:showAsAction="ifRoom|withText" />
    <item android:id="@+id/menu_revert"
          android:icon="@drawable/ic_menu_revert"
          android:title="@string/menu_revert" />
    <item android:id="@+id/menu_delete"
          android:icon="@drawable/delete"
          android:title="@string/menu_delete"
          android:showAsAction="ifRoom|withText" />
    <item android:id="@+id/menu_speech"
          android:title="voice input"/>
</menu>
```

​	完成效果

![image-20211128161712192](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\14.png)

* 美化TitleEditor

  修改AndroidManifest.xml的theme

![image-20211128161912084](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\15.png)

​	修改TitleEditor的布局文件title_editor.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:background="#A5000000">

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="wrap_content"
        android:layout_height="200dp"
        android:orientation="vertical"
        android:paddingLeft="6dp"
        android:paddingRight="6dp"
        android:paddingBottom="3dp"
        android:background="@drawable/edittitle">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="160dp">

            <EditText android:id="@+id/title"
                android:layout_marginTop="2dp"
                android:layout_marginBottom="15dp"
                android:layout_width="wrap_content"
                android:ems="25"
                android:textColor="@color/black"
                android:layout_height="wrap_content"
                android:autoText="true"
                android:capitalize="sentences"
                android:scrollHorizontally="true" />

        </LinearLayout>

        <Button android:id="@+id/ok"
            android:layout_width="70dp"
            android:layout_height="30dp"
            android:layout_gravity="right"
            android:background="@drawable/okbutton"
            android:textColor="@color/black"
            android:text="@string/button_ok"
            android:layout_marginRight="5dp"
            android:onClick="onClickOk" />

    </LinearLayout>

</LinearLayout>

```

​	在drawable文件夹下添加edittitle.xml、okbutton.xml

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <!-- rectangle表示为矩形 -->

    <!-- 填充的颜色 -->
    <solid android:color="@color/white" />

    <!-- 边框的颜色和粗细 -->
    <stroke
        android:width="5dp"
        android:color="@color/slateblue"
        />

    <!-- android:radius 圆角的半径 -->
    <corners
        android:radius="7dp"
        />

</shape>
```

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <!-- rectangle表示为矩形 -->

    <!-- 填充的颜色 -->
    <solid android:color="@color/lightgreen" />

    <!-- 边框的颜色和粗细 -->
    <stroke
        android:width="1dp"
        android:color="@color/black"
        />

    <!-- android:radius 圆角的半径 -->
    <corners
        android:radius="3dp"
        />

</shape>
```

​	完成效果

![image-20211128162811547](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\16.png)

#### 5、功能四、用语音识别来书写笔记

* 在百度智能云的语音技术创建一个应用

![image-20211128163155323](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\17.png)

* 注意创建应用时的语音包名要和你要集成的包名一样，我这里就是填com.example.android.notepad

![image-20211128163341487](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\18.png)

* 创建完后就会看到一下数据，等下要用到

![image-20211128163556399](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\19.png)

* 领取免费的语音识别资源，领取”短语音识别-中文普通话“，由于我已领取，进入到里面无此选项，所以不截进入领取界面的截图

![image-20211128163728721](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\20.png)

* 下载SDK

![image-20211128164122538](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\21.png)

* 插入下载的SDK中的core模块，由于我已经导入，所以会有感叹号

  file->new->import module

![image-20211128164438778](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\22.png)

* 加载导入的core模块文件

  右击app，点击open module settings

![image-20211128164649033](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\23.png)

​	进行如下操作，进入到页面后，选择core，确定即可，由于我已经添加，里面已无

![image-20211128164858189](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\24.png)

* 修改core模块的AndroidManifest.xml里的三个红框里的值为刚才在百度智能云创建项目后的数据

![image-20211128171332841](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\25.png)

* 在整个项目下添加gradle.properties

```properties
android.useAndroidX=true
android.enableJetifier=true
```

* 在com.example.android.notepad包下创建一个名为SpeechRecognition的空activity，修改SpeechRecognition和其布局文件activity_speech_recognition.xml。基础识别功能的实现参考core模块下的ActivityMiniRecog进行实现，基本流程为：1、申请权限  2、初始化EventManager对象 设置自定义监听并注册自己的输出事件类  3、开始发送事件  4、自定义输出事件来实现回调结果处理。在这里我还添加了一个“完成”按钮，将语音识别的文本传回给NoteEditor，完成文本内容通过语音输入。

```java
package com.example.android.notepad;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// 继承百度内部的监听
public class SpeechRecognition extends Activity implements EventListener {

    protected EditText Result;
    protected Button btn;
    protected Button save;
    // 设置事件管理器
    private EventManager asr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognition);

        // 动态申请权限
        initPermission();

        // 基于sdk集成1.1 初始化EventManager对象 设置自定义监听
        asr = EventManagerFactory.create(this, "asr");
        // 基于sdk集成1.3 注册自己的输出事件类
        asr.registerListener(this);

        Result = findViewById(R.id.speechinput);
        btn = findViewById(R.id.beginspeech);
        save = findViewById(R.id.finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"开始识别",Toast.LENGTH_SHORT);
                toast.show();
                start();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = Result.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("input",input);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /**
     * 基于SDK集成2.2 发送开始事件
     * 点击开始按钮
     */
    private void start(){
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        // 基于SDK集成2.1 设置识别参数
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
        String json = null; // 可以替换成自己的json
        json = new JSONObject(params).toString(); // 这里可以替换成你需要测试的json
        // 基于SDK集成2.2 发送start开始事件
        asr.send(SpeechConstant.ASR_START, json, null, 0, 0);
    }

    /*
     *基于sdk集成1.2 自定义输出事件类
     *EventListener 回调方法
     */
    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        if (SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL.equals(name)){
            // 识别相关的结果都在这里CALLBACK_EVENT_ASR_PARTIAL
            try {
                // 结果类型result_type(临时结果partial_result，最终结果final_result)
                // best_result最佳结果参数
                //在2.2熟悉源码的2.2.1设置识别/唤醒输入参数中可查看原始Json
                JSONObject object = new JSONObject(params);
                String result = object.getString("best_result");
                String resultType = object.getString("result_type");
                // 判断识别是否结束
                if ("final_result".equals(resultType)){
                    Result.setText(result);
                    Toast toast = Toast.makeText(getApplicationContext(),"识别已结束",Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * android 6.0 以上需要动态申请权限
     * (以下可直接从ActivityMiniRecog类中复制)
     */
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SpeechRecognition"
    android:background="@drawable/noteeditor">

    <EditText
        android:id="@+id/speechinput"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="25"
        android:hint="点击按钮进行语音识别"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.249" />

    <Button
        android:id="@+id/beginspeech"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/okbutton"
        android:text="开始识别"
        android:textSize="20sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.501"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.541" />

    <Button
        android:id="@+id/finish"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="完  成"
        android:textSize="20sp"
        android:background="@drawable/okbutton"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.501"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.679" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

​	在NoteEditor的onOptionsItemSelected函数里添加跳转到语音识别的代码

![image-20211128173631387](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\26.png)

​	在NoteEditor里添加onActivityResult函数来实现语音识别后的数据接收处理

![image-20211128173932503](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\27.png)

​	在SpeechRecognition里的”save“按钮的点击事件用来传数据给NoteEditor

![image-20211128174321906](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\28.png)

* 完成效果

  在语音识别的时候，电脑的右下角会有一个麦克风的图标出现，结束后会消失

  点击这里进入语音识别

![image-20211128174649565](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\29.png)

​	进入后，点击开始识别按钮即可进行语音识别

![image-20211128174822148](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\30.png)

![image-20211128174911291](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\31.png)

![image-20211128175021238](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\32.png)

​	点击完成按钮，即可把什么意思？添加到笔记内容里

![image-20211128175214499](D:\Project\AndroidStudioProject\Lab\LabImage\mid_notepad\33.png)

* 至此，该项目已全部完成
