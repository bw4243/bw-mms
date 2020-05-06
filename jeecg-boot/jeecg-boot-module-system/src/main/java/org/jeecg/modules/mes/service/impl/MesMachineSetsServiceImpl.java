package org.jeecg.modules.mes.service.impl;

import org.jeecg.modules.mes.entity.MesMachineSets;
import org.jeecg.modules.mes.mapper.MesMachineSetsMapper;
import org.jeecg.modules.mes.service.IMesMachineSetsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: mes_machine_sets
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
@Service
public class MesMachineSetsServiceImpl extends ServiceImpl<MesMachineSetsMapper, MesMachineSets> implements IMesMachineSetsService {
	
	@Autowired
	private MesMachineSetsMapper mesMachineSetsMapper;
	
	@Override
	public List<MesMachineSets> selectByMainId(String mainId) {
		return mesMachineSetsMapper.selectByMainId(mainId);
	}
}
