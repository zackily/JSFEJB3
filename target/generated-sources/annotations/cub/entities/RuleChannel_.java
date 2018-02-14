package cub.entities;

import cub.entities.RuleChannelPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:33")
@StaticMetamodel(RuleChannel.class)
public class RuleChannel_ { 

    public static volatile SingularAttribute<RuleChannel, RuleChannelPK> ruleChannelPK;
    public static volatile SingularAttribute<RuleChannel, String> logUserId;
    public static volatile SingularAttribute<RuleChannel, Date> logDttm;

}