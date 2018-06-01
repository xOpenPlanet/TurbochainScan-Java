package com.osp.ethscan.dao;

import java.math.BigInteger;
import java.util.List;

import com.osp.ethscan.bean.BlockBean;

public interface BlockDao {
	BigInteger getCurrentBlockHeight();
	List<BlockBean> getCurrentPageBlocks(BigInteger startIndex,BigInteger endIndex);
	BlockBean getBlockByNumber(BigInteger number);
}
