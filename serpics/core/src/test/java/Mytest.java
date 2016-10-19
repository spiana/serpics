/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
import org.apache.commons.codec.binary.Base64;



public class Mytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String p = "unoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinquesei";
		byte[] b=	Base64.encodeBase64(p.getBytes());
		String s  = Base64.encodeBase64String(p.getBytes());
		
		System.out.println(b);
		System.out.println(new String(b));
		System.out.println(new String (Base64.decodeBase64(s)));
		
	}

}
