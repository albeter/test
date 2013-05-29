
import com.taobao.matrix.ddal.engine.DAOException;

/**
* User: dingtong.czy
* Date: Mon May 20 09:51:39 CST 2013
*/
public interface VipExchangeDAO {

    /**
    * 插入vipExchangeDO
    * @param vipExchangeDO vipExchangeDO
    * @throws DAOException
    */
    public void insertVipExchange(VipExchangeDO vipExchangeDO) throws DAOException;

    /**
    * 查询vipExchangeDOs
    * @param vipExchangeQuery vipExchangeQuery
    * @return vipExchangeQuery
    * @throws DAOException
    */
    public VipExchangeQuery selectVipExchange(VipExchangeQuery vipExchangeQuery) throws DAOException;

    /**
    *  查询记录的数量
    * @param vipExchangeQuery vipExchangeQuery
    * @return int
    * @throws DAOException
    */
    public int countVipExchange(VipExchangeQuery vipExchangeQuery) throws DAOException ;

    /**
    *  根据id 来更新vipExchangeDO
    * @param  vipExchangeDO vipExchangeDO
    * @return int
    * @throws DAOException
    */
    public int updateVipExchange(VipExchangeDO vipExchangeDO) throws DAOException ;

}
