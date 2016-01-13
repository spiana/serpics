package com.serpics.initialsetup.service;

import com.serpics.initialsetup.ImportType;

public interface SystemSetupTask {
	
	public void execute(ImportType iType);
	
}
