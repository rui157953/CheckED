package com.ryan.checked.db;

import android.provider.BaseColumns;

public interface CheckedBaseColumns extends BaseColumns {
	
	// 这个是每个Provider的标识，在Manifest中使用  
    public static final String AUTHORITY = "com.android.desk.settings";
    public static final String PARAMETER_NOTIFY = "notify";
	
	public static final String MODIFIED = "modified";
	
}