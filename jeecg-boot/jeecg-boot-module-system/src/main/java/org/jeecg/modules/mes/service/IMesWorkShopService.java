package org.jeecg.modules.mes.service;

import org.jeecg.modules.mes.entity.MesMachineSets;
import org.jeecg.modules.mes.entity.MesWorkShop;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: MES车间表
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
public interface IMesWorkShopService extends IService<MesWorkShop> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(MesWorkShop mesWorkShop,List<MesMachineSets> mesMachineSetsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MesWorkShop mesWorkShop,List<MesMachineSets> mesMachineSetsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
