package js.resources;

public class FindByXpath {
	public static final String findElementByXpath="var getElementByXpath=function(path) {" + 
			"  return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" + 
			"}";
}
