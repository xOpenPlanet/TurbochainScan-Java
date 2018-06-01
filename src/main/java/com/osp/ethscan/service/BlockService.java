package com.osp.ethscan.service;

import java.math.BigInteger;
import java.util.List;

import com.osp.ethscan.model.BlockBriefInfo;
import com.osp.ethscan.model.BlockDetailInfo;
import com.osp.ethscan.model.ResponseObject;

/**
 * 区块服务
 * 
 * @author zhangmingcheng
 */
public interface BlockService {
	BigInteger getBlockHeight();
	/**
	 * 取得高度为number区块的困难度
	 * @param number
	 * @return
	 */
	String getBlockDifficulty(BigInteger number);

	BlockDetailInfo getBlockByNumber(BigInteger number,ResponseObject ro);
	
	BigInteger getCurrentBlockHeight();
	
	List<BlockBriefInfo> getCurrentPageBlocks(Integer page,Integer pagesize,ResponseObject ro);
	
	/*
	 * String organizeBlockDetailInfo(Long number);
	 * 
	 * String getBlockBriefList(long startNumber, long endNumber);
	 */
}
