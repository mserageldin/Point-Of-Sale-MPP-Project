package dataaccess;

import java.io.Serializable;

public interface IDataAccessable<K> extends Serializable {
	
	K getPrimaryKeyValue();
}
