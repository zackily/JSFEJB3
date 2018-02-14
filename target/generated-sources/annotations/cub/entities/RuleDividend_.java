package cub.entities;

import cub.entities.RuleDividendPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:33")
@StaticMetamodel(RuleDividend.class)
public class RuleDividend_ { 

    public static volatile SingularAttribute<RuleDividend, RuleDividendPK> ruleDividendPK;
    public static volatile SingularAttribute<RuleDividend, String> scopeCode;
    public static volatile SingularAttribute<RuleDividend, String> logUserId;
    public static volatile SingularAttribute<RuleDividend, String> opCode;
    public static volatile SingularAttribute<RuleDividend, Date> logDttm;

}