package cub.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TR_OPTION_ITEM database table.
 * 
 */
@Entity
@Table(name="TR_OPTION_ITEM")
@NamedQuery(name="TrOptionItem.findAll", query="SELECT t FROM TrOptionItem t")
public class TrOptionItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrOptionItemPK id;

	@Column(name="ITEM_NAME")
	private String itemName;

	public TrOptionItem() {
	}

	public TrOptionItemPK getId() {
		return this.id;
	}

	public void setId(TrOptionItemPK id) {
		this.id = id;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}