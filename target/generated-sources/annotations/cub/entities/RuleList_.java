package cub.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:32")
@StaticMetamodel(RuleList.class)
public class RuleList_ { 

    public static volatile SingularAttribute<RuleList, String> rtnMessage;
    public static volatile SingularAttribute<RuleList, Short> limitCondition;
    public static volatile SingularAttribute<RuleList, Date> endDate;
    public static volatile SingularAttribute<RuleList, Short> checkColumn;
    public static volatile SingularAttribute<RuleList, Short> divisorSource;
    public static volatile SingularAttribute<RuleList, BigDecimal> limitRate;
    public static volatile SingularAttribute<RuleList, Short> limitReaction;
    public static volatile SingularAttribute<RuleList, Short> isLock;
    public static volatile SingularAttribute<RuleList, Short> customerAggregate;
    public static volatile SingularAttribute<RuleList, String> ruleChnName;
    public static volatile SingularAttribute<RuleList, Date> lockDttm;
    public static volatile SingularAttribute<RuleList, Short> checkTiming;
    public static volatile SingularAttribute<RuleList, String> ruleNo;
    public static volatile SingularAttribute<RuleList, Short> dividendAggregate;
    public static volatile SingularAttribute<RuleList, String> lockUserId;
    public static volatile SingularAttribute<RuleList, BigDecimal> divisorValue;
    public static volatile SingularAttribute<RuleList, String> logUserId;
    public static volatile SingularAttribute<RuleList, Date> logDttm;
    public static volatile SingularAttribute<RuleList, Short> ruleClass;
    public static volatile SingularAttribute<RuleList, String> ruleEngName;
    public static volatile SingularAttribute<RuleList, Date> startDate;
    public static volatile SingularAttribute<RuleList, String> result2;
    public static volatile SingularAttribute<RuleList, String> result1;

}