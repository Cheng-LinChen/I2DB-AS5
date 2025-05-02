package org.vanilladb.core.storage.tx.concurrency.conservative;

import java.util.List;

import org.vanilladb.core.storage.file.BlockId;
import org.vanilladb.core.storage.record.RecordId;
import org.vanilladb.core.storage.tx.Transaction;
import org.vanilladb.core.storage.tx.concurrency.ConcurrencyMgr;

public class ConservativeConcurrencyMgr extends ConcurrencyMgr {

	public ConservativeConcurrencyMgr(long txNumber) {
		this.txNum = txNumber;
		List<String> fileNameSet = getFilenameSet(txNumber);  // assume it returns List<String>
		for (int i = 0; i < fileNameSet.size(); i++) {
			String fileName = fileNameSet.get(i);
			lockTbl.xLock(fileName, txNum);
		}
	}
	

	@Override
	public void onTxCommit(Transaction tx) {
		// TODO releaseAll locks
		lockTbl.releaseAll(txNum, false);
	}

	@Override
	public void onTxRollback(Transaction tx) {
		// TODO Auto-generated method stub
		lockTbl.releaseAll(txNum, false);
		
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

	public List<String>getFilenameSet(txNumber){
		// TODO
	}

}
