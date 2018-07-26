package com.gb.bind.adapter;
/*

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

*/

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gb.bind.BindModel;
import com.gb.bind.BindViewAnalysis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 项目名称: YOSHOP
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/7/25 9:16
 * @author tanping
 */
public class GBBaseBindAdapter<T extends MultiItemEntity,K extends BaseViewHolder>  extends BaseMultiItemQuickAdapter<T,K> {
    private HashMap<Integer, BindModel> methods;

    public GBBaseBindAdapter(List<T> data) {
        super(data);

        try {
            methods = BindViewAnalysis.parse(this);

            //配置资源
            Iterator<Integer> it = methods.keySet().iterator();
             while (it.hasNext()){
                 int type = it.next();
                 BindModel methodModel = methods.get(type);
                 addItemType(type,methodModel.layout);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void convert(K helper, T item) {
        if (methods==null){
            return;
        }
        int type = item.getItemType();
        BindModel methodModel = methods.get(type);
        if (methodModel!=null){
            try {
                methodModel.method.invoke(this,helper,item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
