package cub.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:33")
@StaticMetamodel(RuleListLog.class)
public class RuleListLog_ { 

    public static volatile SingularAttribute<RuleListLog, String> rtnMessage;
    public static volatile SingularAttribute<RuleListLog, Short> logType;
    public static volatile SingularAttribute<RuleListLog, Short> limitCondition;
    public static volatile SingularAttribute<RuleListLog, String> seqNo;
    public static volatile SingularAttribute<RuleListLog, Date> endDate;
    public static volatile SingularAttribute<RuleListLog, Short> checkColumn;
    public static volatile SingularAttribute<RuleListLog, Short> divisorSource;
    public static volatile SingularAttribute<RuleListLog, BigDecimal> limitRate;
    public static volatile SingularAttribute<RuleListLog, Short> limitReaction;
    public static volatile SingularAttribute<RuleListLog, Short> isLock;
    public static volatile SingularAttribute<RuleListLog, String> ruleChnName;
    public static volatile SingularAttribute<RuleListLog, Date> lockDttm;
    public static volatile SingularAttribute<RuleListLog, Short> checkTiming;
    public static volatile SingularAttribute<RuleListLog, String> ruleNo;
    public static volatile SingularAttribute<RuleListLog, Short> dividendAggregate;
    public static volatile SingularAttribute<RuleListLog, String> lockUserId;
    public static volatile SingularAttribute<RuleListLog, BigDecimal> divisorValue;
    public static volatile SingularAttribute<RuleListLog, String> logUserId;
    public static volatile SingularAttribute<RuleListLog, Short> clientAggregate;
    public static volatile SingularAttribute<RuleListLog, Date> logDttm;
    public static volatile SingularAttribute<RuleListLog, Short> ruleClass;
    public static volatile SingularAttribute<RuleListLog, String> ruleEngName;
    public static volatile SingularAttribute<RuleListLog, Date> startDate;

}