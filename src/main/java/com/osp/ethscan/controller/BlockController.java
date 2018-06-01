package com.osp.ethscan.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osp.common.json.JsonUtil;
import com.osp.ethscan.model.BlockDetailInfo;
import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.service.BlockService;

/**
 * 区块控制类
 * @author zhangmingcheng
 */
@Controller
@RequestMapping("/")
public class BlockController {
	
	@Autowired
	private BlockService blockServiceImpl;
	
	/**
	 * 获取当前页区块信息
	 * @param page
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/blocks", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCurrentPageBlocks(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize){
		ResponseObject ro = ResponseObject.getInstance();
		ro.setValue("result", blockServiceImpl.getCurrentPageBlocks(page, pagesize,ro));
		return JsonUtil.beanToJson(ro);
	}
	
    /**
     * 获取区块高度为number的详细信息
     * @param number
     * @return
     */
	@RequestMapping(value = "/get_block_by_number", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getBlockByNumber(@RequestParam(value = "number", defaultValue = "1") BigInteger number){
		ResponseObject ro = ResponseObject.getInstance();
		BlockDetailInfo blockDetailInfo = blockServiceImpl.getBlockByNumber(number,ro);
		if(blockDetailInfo!=null){
			ro.setMessage("success");
			ro.setValue("result", blockDetailInfo);
		}
		return JsonUtil.beanToJson(ro);
	}
	
}
