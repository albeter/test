import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dingtong.czy
 * @since 13-4-28
 */
public class DiffrentAuctions {

    public static void main(String[] args) throws IOException {
        int page = 10;
        long categoryId = 10600000000L;
        URL url = null;
        InputStream is = null;
        System.out.print("SELECT * FROM vip_item_online WHERE category_id="+categoryId+" AND is_deleted='n' AND item_id not IN (");
        try {
            int count=0;
            for(int i =1 ; i<=page;i++){
//                url = new URL("http://vip.taobao.com/list/category_auctions.htm?first_category_id="+categoryId+"&page="+i);
                url = new URL("http://vip.taobao.com/items/exchange_items.htm?spm=a1z1d.2211081.0.48.HfnBUq&exchange_level=1"+"&page="+i);
                is = url.openStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
//                    Pattern pattern = Pattern.compile("item\\.htm\\?id=(.+)\" target");
                    Pattern pattern = Pattern.compile("\"del\">(.+)\\.");
                    Matcher matcher = pattern.matcher(line);
                    if(matcher.find()){
                        System.out.print(matcher.group(1) + ",");
                        Integer integer = Integer.valueOf(matcher.group(1));
                        if(integer>300){
                            count++;
                        }
                    }
                }
            }
            System.out.print(")");
            System.out.println(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            is.close();
        }

    }


}
