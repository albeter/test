package ibatis;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import tool.PropertyObject;
import tool.Tool;

import java.io.*;
import java.util.*;

/**
 * User: dingtong.czy
 * Date: 13-4-1
 */
public class IbatisCode {

    public static String baseResouce = "src\\main\\java\\ibatis";

    public static void main(String[] args) throws IOException {
//        String tableName = "vipPromotion";
//        String properties = "Long,id;Long,onlineId;Integer,brandId;Long,categoryId;String,priceDetail;Date,gmtOrder;Date,startTime;Date,endTime;Date,actualEndTime;Integer,sellerCount;Date,gmtCreate;Date,gmtModified;Integer,sponsorAwardId;String,isDisplay";

        String tableName = "vipLottery";
        String properties = "Long,id;Long,sellerId;Long,awardId;String,awardName;String,awardDetailUrl;String,awardPicUrl;String,rate;Integer,totalCount;String,isDeleted;String,isDisplay;Date,startTime;Date,endTime;Integer,leftCount;Long,legacyCost;Long,activityId;Long,activityDetailId;Date,gmtCreate;Date,gmtModified";
//        String tableName = "vipSignRecords";
//        String properties = "Long,id;Long,sellerId;String,sellerNick;Date,gmtCreate;Date,gmtModified;Integer,brandId;String,brandName;Date,signedTime;Date,sponsorTime;Date,startTime;Date,endTime;Date,actualEndTime;Integer,status;String,reason;String,createUser;String,modifyUser";


        //----------------------严格按照以下的形式填充下面三个参数-start-----//
     /*   String tableName = "vipSponsor";
        String properties = "";*/
        String user = "dingtong.czy";
        //----------------------填充三个参数-end------//



        Properties p = new Properties();
        p.setProperty(Velocity.INPUT_ENCODING, "GBK");
        p.setProperty(Velocity.OUTPUT_ENCODING, "GBK");

        Velocity.init(p);
        VelocityContext context = new VelocityContext();

        List<PropertyObject> propertyObjects = Tool.getPropertyObjects(properties);
        context.put("propertyObjects", propertyObjects);
        context.put("tableName", Tool.firstLetterUpperCase(tableName));
        context.put("lowTableName",tableName);
        context.put("createTime",new Date());
        context.put("user",user);
        File f = new File(".///src///generateCode///" + tableName);
        f.mkdir();



        generateDO(context, tableName);
        generateSqlMap(tableName, context, propertyObjects);
        generateInterfaceDAO(tableName, context);
        generateIbatisDAO(tableName, context);
        generateQuery(tableName, context);
        generateTest(tableName, context);
    }

    private static void generateTest(String tableName, VelocityContext context) throws IOException {
        Template template = Velocity.getTemplate("src\\main\\java\\ibatis\\test.vm");
        context.put("Tool",new Tool());

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        FileOutputStream output = new FileOutputStream(".///src///generateCode///" + tableName + "///" + Tool.firstLetterUpperCase(tableName) + "DAOTest.java");
        output.write(writer.toString().getBytes(), 0, writer.getBuffer().length());
    }

    private static void generateQuery(String tableName, VelocityContext context) throws IOException {
        Template template = Velocity.getTemplate("src\\main\\java\\ibatis\\query.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        FileOutputStream output = new FileOutputStream(".///src///generateCode///" + tableName + "///" + Tool.firstLetterUpperCase(tableName) + "Query.java");
        output.write(writer.toString().getBytes(), 0, writer.getBuffer().length());
    }

    private static void generateIbatisDAO(String tableName, VelocityContext context) throws IOException {
        Template template = Velocity.getTemplate("src\\main\\java\\ibatis\\ibatisDAO.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        FileOutputStream output = new FileOutputStream(".///src///generateCode///" + tableName + "///Ibatis" + Tool.firstLetterUpperCase(tableName) + "DAO.java");
        output.write(writer.toString().getBytes(), 0, writer.getBuffer().length());
    }

    private static void generateInterfaceDAO(String tableName, VelocityContext context) throws IOException {
        Template template = Velocity.getTemplate("src\\main\\java\\ibatis\\interfaceDAO.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        FileOutputStream output = new FileOutputStream(".///src///generateCode///" + tableName + "///" + Tool.firstLetterUpperCase(tableName) + "DAO.java");
//        output.write(writer.toString().getBytes(), 0, writer.getBuffer().length());
        PrintStream ps = new PrintStream(output, true, "GBK");//这里我们就可以设置编码了
        ps.print(writer.toString());
        ps.flush();
        ps.close();
        output.close();
    }

    private static void generateSqlMap(String tableName, VelocityContext context, List<PropertyObject> propertyObjects) throws IOException {
        String allcolumns = "";
        String insertStr = "";
        for (int i = 0; i < propertyObjects.size(); i++) {
            if (propertyObjects.size() == 1) {
                allcolumns = propertyObjects.get(0).getSqlName();
                insertStr = "#" + propertyObjects.get(0).getSqlName() + "#";
                break;
            }
            allcolumns += (i == propertyObjects.size() - 1) ? propertyObjects.get(i).getSqlName() : propertyObjects.get(i).getSqlName() + ",";
            if (i == propertyObjects.size() - 1) {
                insertStr += "gmtCreate".equals(propertyObjects.get(i).getCamelName())||"gmtModified".equals(propertyObjects.get(i).getCamelName()) ? "now()" : "#" + propertyObjects.get(i).getCamelName() + "#";
            } else {
                insertStr += "gmtCreate".equals(propertyObjects.get(i).getCamelName())||"gmtModified".equals(propertyObjects.get(i).getCamelName()) ? "now()," : "#" + propertyObjects.get(i).getCamelName() + "#" + ",";
            }
        }

        context.put("allcolumns", allcolumns);
        context.put("insertStr", insertStr);
        context.put("sqlTableName", Tool.getSqlName(tableName));
        Template template = Velocity.getTemplate("src\\main\\java\\ibatis\\sqlMap.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        FileOutputStream output=new FileOutputStream(".///src///generateCode///"+tableName+"///"+Tool.firstLetterUpperCase(tableName)+"SqlMap.xml");
        output.write(writer.toString().getBytes(),0,writer.getBuffer().length());
    }

    private static void generateDO(VelocityContext context, String tableName) throws IOException {
        Template template = Velocity.getTemplate("src\\main\\java\\ibatis\\do.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        FileOutputStream output = new FileOutputStream(".///src///generateCode///" + tableName + "///" + Tool.firstLetterUpperCase(tableName) + "DO.java");
        output.write(writer.toString().getBytes(), 0, writer.getBuffer().length());
    }


}
