package tool;

/**
 * User: dingtong.czy
 * Date: 13-4-2
 */
public class PropertyObject {

    private String type ;
    private String camelName;
    private String sqlName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCamelName() {
        return camelName;
    }

    public void setCamelName(String camelName) {
        this.camelName = camelName;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }
}
