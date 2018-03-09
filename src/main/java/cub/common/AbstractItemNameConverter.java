package cub.common;

import cub.entities.RdOptionItem;
import cub.entities.RdOptionItemPK;
import cub.facade.RdOptionItemFacade;

public abstract class AbstractItemNameConverter {

    public String getItemName(String classCode, Object value, RdOptionItemFacade facade) {
        String result = "";
        if (null != value) {
            short s = (Short) value;
            RdOptionItemPK pk = new RdOptionItemPK(Short.valueOf(classCode), s);
            RdOptionItem item = facade.find(pk);
            result = item.getItemName();
        }
        return result;
    }
}
