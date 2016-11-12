package com.ryan.checked;

import java.util.List;

/**
 * Created by Ryan on 2016/11/10.
 */
interface IPunchedInModel {

     void insertData(final CheckedBean mCheckedBean);

     List<CheckedBean> queryData();


}
