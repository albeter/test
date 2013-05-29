import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: dingtong.czy
 * Date: 12-11-30
 */
public class GenerateCode {
    public static void main(String[] args) {
        Velocity.init();
        Template template = Velocity.getTemplate("src\\main\\java\\codeTemplate.vm");
        VelocityContext context = new VelocityContext();
        //

        String returnType = "List<AwardOnlineGroupDO>";

        String methodNameCompleted = "selectDOs";
        String params = "AwardOnlineGroupQuery query,BrandHeadEnum exceptBrandHead";

        String daoName ="awardOnlineGroup";

       //-----------------fix previous code
        Map<String,String>  managerPrefixName = new HashMap<String, String>();
        managerPrefixName.put("update","modify");
        managerPrefixName.put("select","find");

        String methodType ="";
        String methodName ="";
        for(String str:managerPrefixName.keySet()){
            boolean isStart = methodNameCompleted.startsWith(str);
            if(isStart){
                methodType = str;
                methodName = methodNameCompleted.replaceAll(str,"");
            }
        }
        context.put("returnType",returnType);
        context.put("methodName",methodName);

        List<String> paramNames = new ArrayList<String>();

        String[] strings = params.split(",");
        for (String str:strings){
            String[] aParam = str.split(" ");
            paramNames.add(aParam[1]);
        }
        context.put("paramNames", paramNames);
        context.put("params",params);

        context.put("managerPrefixName",managerPrefixName.get(methodType));

        String  MethodType = firstLetterUpperCase(methodType);
        context.put("methodType",methodType);
        context.put("MethodType",MethodType);

        String DaoName = firstLetterUpperCase(daoName);
        context.put("daoName",daoName);
        context.put("DaoName",DaoName);

        StringWriter writer = new StringWriter();
        template.merge(context,writer);
        System.out.println(writer.toString());
//        System.out.println(System.getProperty("user.dir"));
    }

    private static String firstLetterUpperCase(String daoName) {
        return daoName.substring(0, 1).toUpperCase() + daoName.substring(1);
    }
}