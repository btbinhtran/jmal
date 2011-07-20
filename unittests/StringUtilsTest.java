import com.jmal.util.StringUtils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class StringUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUnescapeXML() {
		final String expectedStr = "a<b>c\"d'e&";
		final String xmlStr = "a&lt;b&gt;c&quot;d&#039;e&amp;"; 
		
			
		assertEquals(expectedStr, StringUtils.unescapeXML(xmlStr));
	}
	
	@Test
	public void testUnescapeWeirdMALSynopsisXML1() {
		final String testStr = "The plot revolves around Kaoru Torigara, only son of a ramen shop owner, who is going to renovate his inherited ramen shop. He discovers that his shop has a guardian angel named Pretty Menma. Pretty Menma tells Kaoru that his dead father&amp;#039;s intention is making him succeed in the &amp;quot;Food King Wars&amp;quot;, a battle of restaurants around the world that is held every 4 years. Kaoru and Menma must help each other to go through the struggles of the &amp;quot;Food King Wars&amp;quot;.&lt;br /&gt;\n&lt;br /&gt;\n(Source: Wikipedia)";
		final String expStr = "The plot revolves around Kaoru Torigara, only son of a ramen shop owner, who is going to renovate his inherited ramen shop. He discovers that his shop has a guardian angel named Pretty Menma. Pretty Menma tells Kaoru that his dead father's intention is making him succeed in the \"Food King Wars\", a battle of restaurants around the world that is held every 4 years. Kaoru and Menma must help each other to go through the struggles of the \"Food King Wars\". (Source: Wikipedia)";
		final String actStr = StringUtils.cleanWeirdMALSynopsisXML(testStr);
		
		assertEquals(expStr, actStr);
	}
	
	@Test
	public void testUnescapeWeirdMALSynopsisXML2() {
		final String testStr = "The characters from previous .hack//G.U. Games and .hack//Roots receive an email from Ovan. He is requesting them to go to Hidden Forbidden Festival where is set up a mysterious summer festival. There they find an AIDA Chim Chim who wishes to peacefully co-exist with the other players of The World. It then transforms into the word &amp;quot;Returner&amp;quot;, so they assume it to mean that Ovan will return to The World.&lt;br /&gt;\n&lt;br /&gt;\n(Source: ANN)";
		final String expStr = "The characters from previous .hack//G.U. Games and .hack//Roots receive an email from Ovan. He is requesting them to go to Hidden Forbidden Festival where is set up a mysterious summer festival. There they find an AIDA Chim Chim who wishes to peacefully co-exist with the other players of The World. It then transforms into the word \"Returner\", so they assume it to mean that Ovan will return to The World. (Source: ANN)";
		final String actStr = StringUtils.cleanWeirdMALSynopsisXML(testStr);
				
		assertEquals(expStr, actStr);
	}
}
