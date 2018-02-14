package cub.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:33")
@StaticMetamodel(CommonCurrency.class)
public class CommonCurrency_ { 

    public static volatile SingularAttribute<CommonCurrency, String> currencyDesc;
    public static volatile SingularAttribute<CommonCurrency, String> crtUserId;
    public static volatile SingularAttribute<CommonCurrency, String> updUserId;
    public static volatile SingularAttribute<CommonCurrency, String> currencyStatus;
    public static volatile SingularAttribute<CommonCurrency, Date> crtDate;
    public static volatile SingularAttribute<CommonCurrency, String> tradeCode;
    public static volatile SingularAttribute<CommonCurrency, BigInteger> currDecimal;
    public static volatile SingularAttribute<CommonCurrency, BigDecimal> id;
    public static volatile SingularAttribute<CommonCurrency, String> currencyId;
    public static volatile SingularAttribute<CommonCurrency, String> currencyCode;
    public static volatile SingularAttribute<CommonCurrency, Date> updDate;

}