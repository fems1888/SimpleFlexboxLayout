package com.example.flexlib;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * https://blog.csdn.net/My_TrueLove/article/details/70519234——有关枚举的详细讲解
 * @author Created by jackieyao on 2018/6/20 上午11:46.
 * 传统的那种final定义的方法也没啥不行，但是没有枚举优雅。传统的final方法在setJustifyContent（int justifyContent）这样是有风险的,
 * 参数是int那么就是说只要是int类型的参数那么在编译时就不会报错，没法保证该参数一定是在{0,1,2}这个集合中,使用常量不安全、可读性差。
 * 比如说别人提供了{3}这个参数，编译也是能通过的，这就不好了。但是如果用枚举的话方法的声明就可以使这样：
 * public void test(JustifyContent){} 这就限制了操作一定是那三种。就在编译时进行了类型的检测。
 * 但是传统的枚举很耗性能，毕竟官方是不推荐使用枚举的
 * @IntDef @Retention @Retention(RetentionPolicy.SOURCE) 表示告诉编译器，该注解是源代码级别的，只保留源码中，生成 class 文件的时候这个注解就被编译器自动去掉了。
 * 调用者如果再随便传入一个 int 值，虽然可以运行，但代码会爆红，lint 检查将会给与警告:Must be one of: JustifyContent.FLEX_START, JustifyContent.FLEX_END, JustifyContent.CENTER
 * 如此，保证了类型安全。但也只是警告，仍然可以运行，但总比没有警告强多了。
 * 其实这种用法在 Android 源码中屡见不鲜，比如 Resources 下的 getDrawable() 方法：
 * public Drawable getDrawable(@DrawableRes int id)
 * 使用时，我们一般会这么用：getResources().getDrawable(R.drawable.ic_launcher);
 * 虽然是 int 值，但是当我们这么用时：getResources().getDrawable(111);就会爆红并提示：Expected resource of type drawable
 *
 * 总之以后尽量不要使用枚举 优先选用注解代替枚举
 */
@IntDef({JustifyContent.FLEX_START,JustifyContent.FLEX_END,JustifyContent.CENTER})
@Retention(RetentionPolicy.SOURCE)//保留策略 RetentionPolicy.SOURCE 只在于源码即程序运行后，不会再 .class文件里，即编译时不考虑
public @interface JustifyContent {
    /** Flex items are packed toward the start line. */
    int FLEX_START = 0;

    /** Flex items are packed toward the end line. */
    int FLEX_END = 1;

    /** Flex items are centered along the flex line where the flex items belong. */
    int CENTER = 2;
}
