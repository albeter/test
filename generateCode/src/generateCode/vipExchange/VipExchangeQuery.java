

import com.taobao.matrix.qz.common.domain.query.BaseQuery;


import java.util.Date;
import java.util.List;

/**
* User: dingtong.czy
* Date: Mon May 20 09:51:39 CST 2013
*/
public class VipExchangeQuery extends BaseQuery {

    private static final long serialVersionUID ;//fix me

    private List<VipExchangeDO> vipExchangeDOs;

    private Long id ;

    private Long sellerId ;

    private Long awardId ;

    private String awardName ;

    private String awardDetailUrl ;

    private String awardPicUrl ;

    private String rate ;

    private Integer totalCount ;

    private String isDeleted ;

    private String isDisplay ;

    private Date startTime ;

    private Date endTime ;

    private Integer leftCount ;

    private Long legacyCost ;

    private Long activityId ;

    private Long activityDetailId ;


}
