package cub.entities;

import cub.entities.RdDataColumnPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T09:48:32")
@StaticMetamodel(RdDataColumn.class)
public class RdDataColumn_ { 

    public static volatile SingularAttribute<RdDataColumn, String> columnChnName;
    public static volatile SingularAttribute<RdDataColumn, String> logUserId;
    public static volatile SingularAttribute<RdDataColumn, Date> logDttm;
    public static volatile SingularAttribute<RdDataColumn, RdDataColumnPK> rdDataColumnPK;

}