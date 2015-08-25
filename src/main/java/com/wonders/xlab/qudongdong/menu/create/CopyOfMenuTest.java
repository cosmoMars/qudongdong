package com.wonders.xlab.qudongdong.menu.create;

import com.wonders.xlab.qudongdong.menu.model.CommonButton;
import com.wonders.xlab.qudongdong.menu.model.ComplexButton;

public class CopyOfMenuTest {
	public static void main(String[] args) {
		CopyOfMenuTest menuTest = new CopyOfMenuTest();
		String resString = menuTest.getMenuJosn();
//		String resString = menuTest.getFWHMenuJosn();
		System.out.println(resString);
	}
	
	public String getMenuJosn(){
	    CommonButton btn11 = new CommonButton();
        btn11.setName("疫苗助手");  
        btn11.setType("click");
        btn11.setKey("11");
        
		ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("应用工场");  
        mainBtn1.setSub_button(new CommonButton[] { btn11});  
        
        CommonButton btn21 = new CommonButton();  
        btn21.setName("宝宝健康");  
        btn21.setType("click");
        btn21.setKey("21");
        
        CommonButton btn22 = new CommonButton();  
        btn22.setName("健身与美");  
        btn22.setType("click");
        btn22.setKey("22");
//        btn22.setUrl("http://101.231.124.13/WeiXinTest/MedicineServlet?type=medicineHome");

        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("知识之树");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22});  
        
        CommonButton btn31 = new CommonButton();  
        btn31.setName("体验之窗");  
        btn31.setType("click");
        btn31.setKey("31");
//        btn31.setUrl("http://101.231.124.13/WeiXinTest/ksjz.jsp");
        
//        ComplexButton mainBtn3 = new ComplexButton();  
//        mainBtn3.setName("新闻中心");  
//        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33, btn34, btn35});  
		
		StringBuffer bf= new StringBuffer();
		bf.append("{");
		bf.append("\"button\":[");
		
			bf.append("{");
			bf.append("\"name\":\""+mainBtn1.getName()+"\",");
			bf.append("\"sub_button\":[");
			CommonButton[] buttons = (CommonButton[]) mainBtn1.getSub_button();
			for (CommonButton commonButton : buttons) {
				bf.append("{");
				if(commonButton.getKey()==null){
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"view\",");
					bf.append("\"url\":\""+commonButton.getUrl()+"\"");
				}else{
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"click\",");
					bf.append("\"key\":\""+commonButton.getKey()+"\"");
				}
				bf.append("},");
			}
			bf.deleteCharAt(bf.length()-1);
			bf.append("]");
			bf.append("},");
			
			bf.append("{");
			bf.append("\"name\":\""+mainBtn2.getName()+"\",");
			bf.append("\"sub_button\":[");
			CommonButton[] buttons2 = (CommonButton[]) mainBtn2.getSub_button();
			for (CommonButton commonButton : buttons2) {
				bf.append("{");
				if(commonButton.getKey()==null){
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"view\",");
					bf.append("\"url\":\""+commonButton.getUrl()+"\"");
				}else{
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"click\",");
					bf.append("\"key\":\""+commonButton.getKey()+"\"");
				}
				bf.append("},");
			}
			bf.deleteCharAt(bf.length()-1);
			bf.append("]");
			bf.append("},");
		
			bf.append("{");
			bf.append(" \"type\":\"click\",");
			bf.append("\"name\":\""+btn31.getName()+"\",");
			bf.append("\"key\":\""+btn31.getKey()+"\"");
			bf.append("}");
		bf.append("]");
		bf.append("}");
		return bf.toString();
	}
	public String getFWHMenuJosn(){
	    CommonButton btn11 = new CommonButton();  
        btn11.setName("我的预约");  
        btn11.setType("click");
        btn11.setKey("11");
 /***       
		ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("应用工场");  
        mainBtn1.setSub_button(new CommonButton[] { btn11});  
        
        CommonButton btn21 = new CommonButton();  
        btn21.setName("宝宝健康");  
        btn21.setType("click");
        btn21.setKey("21");
        
        CommonButton btn22 = new CommonButton();  
        btn22.setName("健身与美");  
        btn22.setType("click");
        btn22.setKey("22");
//        btn22.setUrl("http://101.231.124.13/WeiXinTest/MedicineServlet?type=medicineHome");

        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("知识之树");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22});  
        
        CommonButton btn31 = new CommonButton();  
        btn31.setName("体验之窗");  
        btn31.setType("click");
        btn31.setKey("31");
//        btn31.setUrl("http://101.231.124.13/WeiXinTest/ksjz.jsp");
        
//        ComplexButton mainBtn3 = new ComplexButton();  
//        mainBtn3.setName("新闻中心");  
//        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33, btn34, btn35});  
**/		
		StringBuffer bf= new StringBuffer();
		bf.append("{");
		bf.append("\"button\":[");
		/***
			bf.append("{");
			bf.append("\"name\":\""+mainBtn1.getName()+"\",");
			bf.append("\"sub_button\":[");
			CommonButton[] buttons = (CommonButton[]) mainBtn1.getSub_button();
			for (CommonButton commonButton : buttons) {
				bf.append("{");
				if(commonButton.getKey()==null){
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"view\",");
					bf.append("\"url\":\""+commonButton.getUrl()+"\"");
				}else{
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"click\",");
					bf.append("\"key\":\""+commonButton.getKey()+"\"");
				}
				bf.append("},");
			}
			bf.deleteCharAt(bf.length()-1);
			bf.append("]");
			bf.append("},");
			
			bf.append("{");
			bf.append("\"name\":\""+mainBtn2.getName()+"\",");
			bf.append("\"sub_button\":[");
			CommonButton[] buttons2 = (CommonButton[]) mainBtn2.getSub_button();
			for (CommonButton commonButton : buttons2) {
				bf.append("{");
				if(commonButton.getKey()==null){
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"view\",");
					bf.append("\"url\":\""+commonButton.getUrl()+"\"");
				}else{
					bf.append("\"name\":\""+commonButton.getName()+"\",");
					bf.append(" \"type\":\"click\",");
					bf.append("\"key\":\""+commonButton.getKey()+"\"");
				}
				bf.append("},");
			}
			bf.deleteCharAt(bf.length()-1);
			bf.append("]");
			bf.append("},");
		***/
			bf.append("{");
			bf.append(" \"type\":\"click\",");
			bf.append("\"name\":\""+btn11.getName()+"\",");
			bf.append("\"key\":\""+btn11.getKey()+"\"");
			bf.append("}");
		bf.append("]");
		bf.append("}");
		return bf.toString();
	}
}
