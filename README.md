简介
====
* 简单的FlexboxLayout,自动换行、排版,不受横竖屏影响。
* 目前只是第一版本，后续会继续完善
* ![](http://g.recordit.co/TyIC2okoVQ.gif)![](http://g.recordit.co/nYpoJLD5VJ.gif)


项目链接 [SimpleFlexboxLayout](https://github.com/fems1888/SimpleFlexboxLayout)
===
* 如果你感觉到对你有帮助，欢迎star
* 如果你感觉对代码有疑惑，或者需要修改的地方，欢迎issue

主要特性
===
* 简单,轻量

使用方法
===
XML布局
--

* 新建XML布局
```Java
<ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.example.flexlib.FlexboxLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:Flex_Margin_Left="40"
            app:Flex_Margin_Top="30>

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:background="@drawable/shape_color_primary"
                android:padding="10dp"
                android:text="安卓"
                android:textSize="15dp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:background="@drawable/shape_color_primary"
                android:padding="10dp"
                android:text="ios"
                android:textSize="15dp" />

            ...
        </com.example.flexlib.FlexboxLayout>
    </ScrollView>
```


DownLoad
=====
Step 1. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```java
dependencies {
	        implementation 'com.github.fems1888:SimpleFlexboxLayout:1.0.0'
	}
```

   
   




