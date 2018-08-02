# tp_bindview
tp_bindview

```java
compile 'gb.tp.bindview:gb_bindview:1.0.3'

```

利用CymChad 的adapter 为基础 提供 一套注解的多类型匹配规则
```java
public @interface BindItem {
    int  type() default 0;
    @LayoutRes int layout() default 0;
}
``` 

# 根据对应的type自动绑定layout
```java
 @BindItem(type =0,layout = R.layout.item_one)
    public void one(BaseViewHolder helper,DemoBean bean) throws Exception {
        helper.setText(R.id.textView,bean.text);
 }
``` 

 # 完整的代码
```java
/**
 * 项目名称: YOSHOP
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/7/25 14:15
 */
public class DemoAdapter extends GBBaseBindAdapter<DemoBean,BaseViewHolder> {
    public DemoAdapter(List<DemoBean> data) {
        super(data);
    }

    @BindItem(layout = R.layout.item_one)
    public void one(BaseViewHolder helper,DemoBean bean) throws Exception {
        helper.setText(R.id.textView,bean.text);
    }

}
```

# 针对多类型的 adapter 可以简化代码 并提高 代码可读性。
# 自定义viewbind 控件，支持自动扩展annotations类型
 例如：
 ```java
 @Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ViewBindConverter
public @interface PriceViewBind {
    @IdRes int id() default 0;
    Class  converter() default CommConverter.class;
}


public class CommConverter {
    @PriceViewBind
    public static void gbBind(TextView view, float data) {

        view.setText("price : "+data);
        Log.d("XX",""+data);

    }
}

```
#  使用view bind
```java
 @PriceViewBind(id=R.id.textView1)
    public float price = 2.3f;
```
 @PriceViewBind  使用@PriceViewBind 后会自动将对于的 值和 view 去调用CommConverter转换器方法，自动复制
 
 # 可以自定定义类似PriceViewBind 自己需要的类型的扩展器
 
 
 


