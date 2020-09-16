package maven.test;

import org.junit.Test;

import io.restassured.RestAssured;

/**
 * 静态资源导入
 */
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;

/*import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;*/
import org.apache.log4j.Logger;

public class TestBaidu {
	Logger log=Logger.getLogger(TestBaidu.class);
	@Test
	public void testGethttp() {
		log.info(given().get("http://www.baidu.com").then().statusCode(200));
	}
	
	@Test
	public void testGethttpversion1() {
		given()
		.log().all()
		.get("http://www.baidu.com").then().log().all().statusCode(200);
	}	
	/**
	 *  http://localhost:8080/lotto
	 *  
	 *  hasItems(23, 54)包括
	 *  equalTo等于
	 *  equalTo 和 hasItems 是 Hamcrest matchers的方法，所以需要静态导入 org.hamcrest.Matchers
	 */
   
}
