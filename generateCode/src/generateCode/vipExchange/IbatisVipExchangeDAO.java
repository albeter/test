

import com.taobao.matrix.ddal.engine.DAOException;
import com.taobao.matrix.qz.common.dao.ibatis.BaseIbatisDAOSupport;
import java.util.List;

/**
* User: dingtong.czy
* Date: Mon May 20 09:51:39 CST 2013
*/
public class IbatisVipExchangeDAO extends BaseIbatisDAOSupport implements VipExchangeDAO {

    @Override
    public void insertVipExchange(VipExchangeDO vipExchangeDO) throws DAOException {
        insert("insertVipExchange", vipExchangeDO);
    }

    @Override
    public VipExchangeQuery selectVipExchange(VipExchangeQuery vipExchangeQuery) throws DAOException {
        int count = countVipExchange(vipExchangeQuery);
        vipExchangeQuery.setTotalItem(count);
        List<VipExchangeDO> results = selectForList("selectVipExchange", vipExchangeQuery);
        vipExchangeQuery.setVipExchangeDOs(results);
        return vipExchangeQuery;
    }

    @Override
    public int countVipExchange(VipExchangeQuery vipExchangeQuery) throws DAOException {
        Integer count = (Integer)selectForObject("countVipExchange", vipExchangeQuery);
        return count == null ? 0 : count;
    }

    @Override
    public int updateVipExchange(VipExchangeDO vipExchangeDO) throws DAOException {
        return update("updateVipExchange", vipExchangeDO);
    }
}









