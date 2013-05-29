
import com.taobao.matrix.qz.base.AbstractExchangeTransactionalTests;
import junit.framework.Assert;
import java.util.Date;

/**
* User: dingtong.czy
* Date: Mon May 20 09:51:39 CST 2013
*/
public class VipExchangeDAOTest extends AbstractExchangeTransactionalTests {

        private VipExchangeDAO vipExchangeDAO;

        private VipExchangeDO vipExchangeDO;

        private VipExchangeQuery vipExchangeQuery;

        @Override
        protected void onSetUpInTransaction() throws Exception {
            vipExchangeDO = new VipExchangeDO();
            vipExchangeDO.setId(165721L);
            vipExchangeDO.setSellerId(551041L);
            vipExchangeDO.setAwardId(874633L);
            vipExchangeDO.setAwardName("awardName");
            vipExchangeDO.setAwardDetailUrl("awardDetailUrl");
            vipExchangeDO.setAwardPicUrl("awardPicUrl");
            vipExchangeDO.setRate("rate");
            vipExchangeDO.setTotalCount(720793);
            vipExchangeDO.setIsDeleted("n");
            vipExchangeDO.setIsDisplay("y");
            vipExchangeDO.setStartTime(new Date());
            vipExchangeDO.setEndTime(new Date());
            vipExchangeDO.setLeftCount(244803);
            vipExchangeDO.setLegacyCost(404991L);
            vipExchangeDO.setActivityId(402334L);
            vipExchangeDO.setActivityDetailId(432954L);
            vipExchangeDAO.insertVipExchange(vipExchangeDO);

            vipExchangeQuery = new VipExchangeQuery();
            vipExchangeQuery.setSellerId(vipExchangeDO.getSellerId());
        }

        @Override
        protected String[] getConfigLocations() {
            return new String[]{"dal/tddl-context.xml", "dal/biz-dao.xml"};
        }


        public void testInsertVipExchange() throws Exception {
            vipExchangeDAO.selectVipExchange(vipExchangeQuery);
            Assert.assertTrue(vipExchangeQuery.getVipExchangeDOs().get(0).getSellerId().equals(vipExchangeDO.getSellerId()));
        }

        public void testSelectVipExchange() throws Exception {
            vipExchangeDAO.selectVipExchange(vipExchangeQuery);
            Assert.assertTrue(vipExchangeQuery.getVipExchangeDOs().get(0).getSellerId().equals(vipExchangeDO.getSellerId()));
            Integer i = vipExchangeDAO.countVipExchange(vipExchangeQuery);
            Assert.assertTrue(vipExchangeQuery.getTotalItem().equals(i));
        }

        public void testCountVipExchange() throws Exception {
            int i = vipExchangeDAO.countVipExchange(vipExchangeQuery);
            Assert.assertTrue(i > 0);
        }

        public void testUpdateVipExchange() throws Exception{
            VipExchangeQuery query = vipExchangeDAO.selectVipExchange(vipExchangeQuery);
            vipExchangeDO.setId(query.getVipExchangeDOs().get(0).getId());
            vipExchangeDO.setSellerId(811171L);
            vipExchangeDAO.updateVipExchange(vipExchangeDO);
            vipExchangeQuery.setSellerId(vipExchangeDO.getSellerId());
            VipExchangeQuery query2 = vipExchangeDAO.selectVipExchange(vipExchangeQuery);
            Assert.assertTrue(vipExchangeDO.getSellerId().equals(query2.getVipExchangeDOs().get(0).getSellerId()));
        }

        public void setVipExchangeDAO(VipExchangeDAO vipExchangeDAO) {
            this.vipExchangeDAO = vipExchangeDAO;
        }
}
