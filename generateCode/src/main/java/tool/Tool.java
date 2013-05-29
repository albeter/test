package tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: dingtong.czy
 * Date: 13-4-1
 */
public class Tool {

    public String firstLetterUpper(String daoName) {
        return daoName.substring(0, 1).toUpperCase() + daoName.substring(1);
    }

    public String getLong(){
        Random r = new Random();
        return r.nextInt(999999)+"L";
    }

    public String getInteger(){
        Random r = new Random();
        return r.nextInt(999999)+"";
    }

    public static String firstLetterUpperCase(String daoName) {
        return daoName.substring(0, 1).toUpperCase() + daoName.substring(1);
    }

    public static String getSqlName(String camelName) {
      
        char[] chars = camelName.toCharArray();
        List<Integer> upperCaseIndex = new ArrayList<Integer>();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                upperCaseIndex.add(i);
            }
        }

        if (upperCaseIndex.isEmpty()) {
           return camelName;
        }

        List<String> subStrs = new ArrayList<String>();
        for (int index = 0; index < upperCaseIndex.size(); index++) {
            int j;
            if (index == 0) {
                j = 0;
            } else {
                j = upperCaseIndex.get(index - 1);
            }
            subStrs.add(camelName.substring(j, upperCaseIndex.get(index)));
        }
            subStrs.add(camelName.substring(upperCaseIndex.get(upperCaseIndex.size() - 1)));

        String result= "";
        for(int i=0 ;i<subStrs.size();i++){
            if(i==0){
                result=subStrs.get(i);
            }else {
                result+="_"+subStrs.get(i).toLowerCase();
            }

        }
        return result;
    }

    public static List<PropertyObject> getPropertyObjects(String properties){
        List<PropertyObject> objects = new ArrayList<PropertyObject>();
        String[] strings = properties.split(";");
        for (String str:strings){
            String[] strings1 = str.split(",");
            PropertyObject tmpObject = new PropertyObject();
            tmpObject.setType(strings1[0]);
            tmpObject.setCamelName(strings1[1]);
            tmpObject.setSqlName(Tool.getSqlName(tmpObject.getCamelName()));
            objects.add(tmpObject);
        }
        return objects;
    }

    public static void main(String[] args) {
//        Tool tool = new Tool();
//        System.out.println(Tool.getSqlName("awardOnlineGroup"));
        Tool tool = new Tool();
        tool.getLong();
        for(int i = 0;i<10;i++)
        System.out.println(tool.getLong());
    }
}
