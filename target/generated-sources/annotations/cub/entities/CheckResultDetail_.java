package cub.entities;

import cub.entities.CheckResultDetailPK;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:33")
@StaticMetamodel(CheckResultDetail.class)
public class CheckResultDetail_ { 

    public static volatile SingularAttribute<CheckResultDetail, BigDecimal> divisor;
    public static volatile SingularAttribute<CheckResultDetail, String> returnValue1;
    public static volatile SingularAttribute<CheckResultDetail, String> returnValue2;
    public static volatile SingularAttribute<CheckResultDetail, CheckResultDetailPK> checkResultDetailPK;
    public static volatile SingularAttribute<CheckResultDetail, Short> resultCode;
    public static volatile SingularAttribute<CheckResultDetail, BigDecimal> dividend;
    public static volatile SingularAttribute<CheckResultDetail, String> logUserId;
    public static volatile SingularAttribute<CheckResultDetail, Date> logDttm;
    public static volatile SingularAttribute<CheckResultDetail, BigDecimal> ratio;

}