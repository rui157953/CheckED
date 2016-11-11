package com.ryan.checked;

import java.util.List;

/**
 * Created by Ryan on 2016/11/11.
 */
public interface IPunchedInView {

    CheckedBean punchedIn();
    void showRecord(List<CheckedBean> checkedBeans);

}
