package com.serpics.initialsetup;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;

import com.serpics.core.EngineFactory;
import com.serpics.initialsetup.service.SystemSetupService;

/**
 * It's a small program, it perform the operation import. This program is called from plugin maven.
 * Input arguments accepted indicate which type of data [ALL, SAMPLE, PROJECT] to be imported.
 *  
 * @author alessandro.marasco@tinvention.net
 *
 */
public class Application {

	public static void main(String[] args) {
		
		ImportType importType= ImportType.ALL;
		
		System.out.println("Parametri Ricevuti: "+(args!=null ? Arrays.toString(args) : "NESSUNO"));
		
		if(args!=null){
			if(ImportType.PROJECT.name().equalsIgnoreCase(args[0])){
				importType = ImportType.PROJECT;
			}
			if(ImportType.SAMPLE.name().equalsIgnoreCase(args[0])){
				importType = ImportType.SAMPLE;
			}
		}
		System.out.println("ImportType: "+importType);
		EngineFactory.init();
		ApplicationContext ctx =  EngineFactory.getCurrentApplicationContext();
		SystemSetupService systemSetupService = ctx.getBean("systemSetupService", SystemSetupService.class);
		
		systemSetupService.doSystemSetupTasks(importType);

	}

}
