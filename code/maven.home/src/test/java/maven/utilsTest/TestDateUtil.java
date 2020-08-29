package maven.utilsTest;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import util.DataUtil;
public class TestDateUtil {

	Logger log=Logger.getLogger(TestDateUtil.class);
	@Test
	public void Test1() {
		log.info(DataUtil.format(new Date(), DataUtil.formate1));
		log.info(DataUtil.format(new Date(), DataUtil.formate2));
		log.info(DataUtil.format(new Date(), DataUtil.formate3));
		log.info(DataUtil.format(new Date(), DataUtil.formate4));
		log.info(DataUtil.format(new Date(), DataUtil.formate5));
	}
}
