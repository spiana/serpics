import org.junit.Assert;
import org.junit.Test;

import com.serpics.base.Multilingual;
import com.serpics.base.data.model.MultilingualString;


public class Mytest {

	@Test
	public void test(){
		
		MultilingualString a = new MultilingualString();
		
		Assert.assertFalse(a.getClass().isAssignableFrom(Multilingual.class));
		Assert.assertTrue(Multilingual.class.isAssignableFrom(a.getClass()));
	}
}
