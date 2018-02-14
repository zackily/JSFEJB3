package cub.entities;

import cub.entities.RuleDivisorPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:33")
@StaticMetamodel(RuleDivisor.class)
public class RuleDivisor_ { 

    public static volatile SingularAttribute<RuleDivisor, RuleDivisorPK> ruleDivisorPK;
    public static volatile SingularAttribute<RuleDivisor, String> scopeCode;
    public static volatile SingularAttribute<RuleDivisor, String> logUserId;
    public static volatile SingularAttribute<RuleDivisor, String> opCode;
    public static volatile SingularAttribute<RuleDivisor, Date> logDttm;

}