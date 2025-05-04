package org.vanilladb.core.storage.tx.concurrency;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.vanilladb.core.sql.Constant;
import org.vanilladb.core.sql.IntegerConstant;
import org.vanilladb.core.storage.file.BlockId;
import org.vanilladb.core.storage.record.RecordId;
import org.vanilladb.core.storage.tx.Transaction;

    



public class ConservativeConcurrencyMgr extends ConcurrencyMgr {

	private static final Object LOCK_ACQUIRE_MUTEX = new Object();
	private static final Logger logger = Logger.getLogger(ConservativeConcurrencyMgr.class.getName());


	public ConservativeConcurrencyMgr(long txNumber) {
		this.txNum = txNumber;
	}
	

	@Override
	public void onTxCommit(Transaction tx) {
		lockTbl.releaseAll(txNum, false);
		logger.info("[DEBUG] Release all locks for tx " + txNum);
	}

	@Override
	public void onTxRollback(Transaction tx) {
		lockTbl.releaseAll(txNum, false);
		logger.info("[DEBUG] Release all locks for tx " + txNum);
		
	}

	@Override
	public void onTxEndStatement(Transaction tx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyFile(String fileName) {
		// TODO Auto-generated method stub
	}

	@Override
	public void readFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertBlock(BlockId blk) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifyBlock(BlockId blk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readBlock(BlockId blk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyRecord(RecordId recId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void readRecord(RecordId recId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyIndex(String dataFileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readIndex(String dataFileName) {
		// TODO Auto-generated method stub
		
	}

	public void prepareConservativeLocks(List<Integer> readItemIds, List<Integer> writeItemIds) {
		synchronized (LOCK_ACQUIRE_MUTEX) {
			Set<PrimaryKey> allReadKeys = new HashSet<>();
			Set<PrimaryKey> allWriteKeys = new HashSet<>();

			for (int iid : readItemIds) {
				allReadKeys.add(createPrimaryKey(iid));
			}
			for (int iid : writeItemIds) {
				allWriteKeys.add(createPrimaryKey(iid));
			}

			for (PrimaryKey pk : allReadKeys) {
				lockTbl.sLock(pk, txNum);
			}

			for (PrimaryKey pk : allWriteKeys) {
				lockTbl.xLock(pk, txNum);
			}
			logger.info("[DEBUG] Lock acquisition completed for tx " + txNum);
		}
	}

	private PrimaryKey createPrimaryKey(int iid) {
		Map<String, Constant> keyMap = new HashMap<>();
		keyMap.put("i_id", new IntegerConstant(iid));
		return new PrimaryKey("item", keyMap);
	}


	@Override
	public void modifyLeafBlock(BlockId blk) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'modifyLeafBlock'");
	}


	@Override
	public void readLeafBlock(BlockId blk) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'readLeafBlock'");
	}
}
