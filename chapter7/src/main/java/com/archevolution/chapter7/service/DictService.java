package com.archevolution.chapter7.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.archevolution.chapter7.cache.PermCache;
import com.archevolution.chapter7.constant.Constants;
import com.archevolution.chapter7.dao.DictDao;
import com.archevolution.chapter7.model.Dict;

@Service
public class DictService {
	
	private Logger logger = LoggerFactory.getLogger(DictService.class);
	
	@Autowired
	DictDao dictDao;
	
	public List<Dict> findByType(String type){
		return dictDao.findByType(type);
	}
	
	public String findProDescByCode(String code){
		//现在缓存中查询，如果能查询到，则直接返回
		if(PermCache.PRODUCT_TYPE_CACHE != null 
				&& !StringUtils.isEmpty(PermCache.PRODUCT_TYPE_CACHE.get(code))){
			logger.info("Get data from {}", "cache");
			return PermCache.PRODUCT_TYPE_CACHE.get(code);
		}else{
			logger.info("Get data from database");
			//如果缓存中查询不到，则查询书数据库
			PermCache.PRODUCT_TYPE_CACHE.get(code);
			Dict dict = dictDao.findByTypeAndCode(Constants.PRODUCT_TYPE, code);
			
			if(dict != null){
				return dict.getDesc();
			}else{
				return "";
			}
		}
		
	}
}
