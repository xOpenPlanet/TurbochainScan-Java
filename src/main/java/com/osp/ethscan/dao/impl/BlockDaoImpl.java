package com.osp.ethscan.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.osp.ethscan.bean.BlockBean;
import com.osp.ethscan.bean.BlockRowMapper;
import com.osp.ethscan.dao.BlockDao;

/**
 * 区块的数据库操作
 * 
 * @author zhangmingcheng
 */
@Repository
public class BlockDaoImpl implements BlockDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 取得当前页的区块
	 */
	@Override
	public List<BlockBean> getCurrentPageBlocks(BigInteger startIndex, BigInteger endIndex) {
		List<BlockBean> currentPageblockLists = new ArrayList<>();
		for (BigInteger i = startIndex; i.compareTo(endIndex) > 0; i = i.subtract(new BigInteger("1"))) {
			List<BlockBean> blockLists = jdbcTemplate.query("select * from blocks where number = '" + i + "';",
					new BlockRowMapper());
			if (null != blockLists && blockLists.size() > 0) {
				currentPageblockLists.add(blockLists.get(0));
			}
		}
		return currentPageblockLists;
	}

	/**
	 * 获取当前数据库已维护区块的高度
	 */
	@Override
	public BigInteger getCurrentBlockHeight() {
		String currentBlockHeight = jdbcTemplate
				.queryForObject("select current_block_height from timer where id = '1';", null, String.class);
		return new BigInteger(currentBlockHeight);
	}

	/**
	 * 取得高度为number的区块数据
	 */
	@Override
	public BlockBean getBlockByNumber(BigInteger number) {
		List<BlockBean> blockLists = jdbcTemplate.query("select * from blocks where number = '" + number + "';",
				new BlockRowMapper());
		if (null != blockLists && blockLists.size() > 0) {
			return blockLists.get(0);
		}
		return null;
	}

}
