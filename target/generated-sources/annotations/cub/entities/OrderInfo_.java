package cub.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:33")
@StaticMetamodel(OrderInfo.class)
public class OrderInfo_ { 

    public static volatile SingularAttribute<OrderInfo, String> orderNo;
    public static volatile SingularAttribute<OrderInfo, String> clientId;
    public static volatile SingularAttribute<OrderInfo, String> secCode;
    public static volatile SingularAttribute<OrderInfo, BigDecimal> orderAmt;
    public static volatile SingularAttribute<OrderInfo, Short> prdCode;
    public static volatile SingularAttribute<OrderInfo, String> secCodeSwitchIn;
    public static volatile SingularAttribute<OrderInfo, Short> checkTiming;
    public static volatile SingularAttribute<OrderInfo, String> clientIdSwitchIn;
    public static volatile SingularAttribute<OrderInfo, Short> orderDttm;
    public static volatile SingularAttribute<OrderInfo, BigDecimal> orderQty;
    public static volatile SingularAttribute<OrderInfo, BigDecimal> orderPrice;
    public static volatile SingularAttribute<OrderInfo, Short> tradeType;
    public static volatile SingularAttribute<OrderInfo, Short> channelCode;

}