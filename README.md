# tp_bindview
tp_bindview
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
