package org.jeecg.modules.mes.service.impl;

import org.jeecg.modules.mes.entity.MesWorkShop;
import org.jeecg.modules.mes.entity.MesMachineSets;
import org.jeecg.modules.mes.mapper.MesMachineSetsMapper;
import org.jeecg.modules.mes.mapper.MesWorkShopMapper;
import org.jeecg.modules.mes.service.IMesWorkShopService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: MES车间表
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
@Service
public class MesWorkShopServiceImpl extends ServiceImpl<MesWorkShopMapper, MesWorkShop> implements IMesWorkShopService {

	@Autowired
	private MesWorkShopMapper mesWorkShopMapper;
	@Autowired
	private MesMachineSetsMapper mesMachineSetsMapper;
	
	@Override
	@Transactional
	public void saveMain(MesWorkShop mesWorkShop, List<MesMachineSets> mesMachineSetsList) {
		mesWorkShopMapper.insert(mesWorkShop);
		if(mesMachineSetsList!=null && mesMachineSetsList.size()>0) {
			for(MesMachineSets entity:mesMachineSetsList) {
				//外键设置
				entity.setWorkShopId(mesWorkShop.getId());
				mesMachineSetsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(MesWorkShop mesWorkShop,List<MesMachineSets> mesMachineSetsList) {
		mesWorkShopMapper.updateById(mesWorkShop);
		
		//1.先删除子表数据
		mesMachineSetsMapper.deleteByMainId(mesWorkShop.getId());
		
		//2.子表数据重新插入
		if(mesMachineSetsList!=null && mesMachineSetsList.size()>0) {
			for(MesMachineSets entity:mesMachineSetsList) {
				//外键设置
				entity.setWorkShopId(mesWorkShop.getId());
				mesMachineSetsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mesMachineSetsMapper.deleteByMainId(id);
		mesWorkShopMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mesMachineSetsMapper.deleteByMainId(id.toString());
			mesWorkShopMapper.deleteById(id);
		}
	}
	
}
