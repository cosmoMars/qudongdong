package com.wonders.xlab.qudongdong.menu.create;


import com.wonders.xlab.qudongdong.menu.model.CommonButton;
import com.wonders.xlab.qudongdong.menu.model.ComplexButton;

public class MenuTest {
    public static void main(String[] args) {
        MenuTest menuTest = new MenuTest();
        String resString = menuTest.getMenuJosn();
        System.out.println(resString);
    }

    public String getMenuJosn() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("点击获得ID");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("国家卫计委");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("兄弟城市");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("各大医院");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn15 = new CommonButton();
        btn15.setName("市级单位");
        btn15.setType("click");
        btn15.setKey("15");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("快报");
        mainBtn1.setSub_button(new CommonButton[]{btn11, btn12, btn13, btn14, btn15});

        CommonButton btn21 = new CommonButton();
        btn21.setName("年报");
        btn21.setType("view");
        btn21.setKey("23");

        CommonButton btn22 = new CommonButton();
        btn22.setName("月报");
        btn22.setType("view");
        btn22.setKey("23");

        CommonButton btn23 = new CommonButton();
        btn23.setName("小册子");
        btn23.setType("click");
        btn23.setKey("23");

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("归档数据");
        mainBtn2.setSub_button(new CommonButton[]{btn21, btn22, btn23});

        CommonButton btn31 = new CommonButton();
        btn31.setName("医疗机构服务量");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("医疗机构服务效率");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("门急诊医疗费用");
        btn33.setType("click");
        btn33.setKey("33");

        CommonButton btn34 = new CommonButton();
        btn34.setName("综合分析");
        btn34.setType("click");
        btn34.setKey("34");


        CommonButton btn35 = new CommonButton();
        btn35.setName("住院医院费用");
        btn35.setType("click");
        btn35.setKey("35");

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("快速查询");
        mainBtn3.setSub_button(new CommonButton[]{btn31, btn32, btn33, btn34, btn35});

        StringBuffer bf = new StringBuffer();
        bf.append("{");
        bf.append("\"button\":[");

        bf.append("{");
        bf.append("\"name\":\"" + mainBtn1.getName() + "\",");
        bf.append("\"sub_button\":[");
        CommonButton[] buttons = (CommonButton[]) mainBtn1.getSub_button();
        for (CommonButton commonButton : buttons) {
            bf.append("{");
            if (commonButton.getKey() == null) {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"view\",");
                bf.append("\"url\":\"" + commonButton.getUrl() + "\"");
            } else {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"click\",");
                bf.append("\"key\":\"" + commonButton.getKey() + "\"");
            }
            bf.append("},");
        }
        bf.deleteCharAt(bf.length() - 1);
        bf.append("]");
        bf.append("},");

        bf.append("{");
        bf.append("\"name\":\"" + mainBtn2.getName() + "\",");
        bf.append("\"sub_button\":[");
        CommonButton[] buttons2 = (CommonButton[]) mainBtn2.getSub_button();
        for (CommonButton commonButton : buttons2) {
            bf.append("{");
            if (commonButton.getKey() == null) {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"view\",");
                bf.append("\"url\":\"" + commonButton.getUrl() + "\"");
            } else {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"click\",");
                bf.append("\"key\":\"" + commonButton.getKey() + "\"");
            }
            bf.append("},");
        }
        bf.deleteCharAt(bf.length() - 1);
        bf.append("]");
        bf.append("},");

        bf.append("{");
        bf.append("\"name\":\"" + mainBtn3.getName() + "\",");
        bf.append("\"sub_button\":[");
        CommonButton[] buttons3 = (CommonButton[]) mainBtn3.getSub_button();
        for (CommonButton commonButton : buttons3) {
            bf.append("{");
            if (commonButton.getKey() == null) {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"view\",");
                bf.append("\"url\":\"" + commonButton.getUrl() + "\"");
            } else {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"click\",");
                bf.append("\"key\":\"" + commonButton.getKey() + "\"");
            }
            bf.append("},");
        }
        bf.deleteCharAt(bf.length() - 1);
        bf.append("]");
        bf.append("}");

        bf.append("]");
        bf.append("}");
        return bf.toString();
    }
}
