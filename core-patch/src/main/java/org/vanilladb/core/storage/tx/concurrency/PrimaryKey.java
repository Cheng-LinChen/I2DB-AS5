package org.vanilladb.core.storage.tx.concurrency;

import java.util.Map;

import org.vanilladb.core.sql.Constant;



public class PrimaryKey  {

    private String tableName;
    private Map<String, Constant> keyEntryMap;

    public PrimaryKey(String tableName, Map<String, Constant> keyEntryMap) {
      // TODO: Figure it out yourself
	  this.tableName = tableName;
	  this.keyEntryMap = keyEntryMap;
    }

    public String getTableName() {
      // TODO: Figure it out yourself
	  return tableName;
    }

    public Constant getKeyVal(String fld) {
      // TODO: Figure it out yourself
	  return keyEntryMap.get(fld);
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		PrimaryKey other = (PrimaryKey) obj;
		return tableName.equals(other.tableName) && keyEntryMap.equals(other.keyEntryMap);
	}


    @Override
    public int hashCode() {
      // TODO: Figure it out yourself
	  int hash = 7;
	  hash = 31 * hash + (tableName == null ? 0 : tableName.hashCode());
	  hash = 31 * hash + (keyEntryMap == null ? 0 : keyEntryMap.hashCode());
	  return hash;
    }

}

