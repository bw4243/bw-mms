package org.jeecg.modules.mes.service;

import org.jeecg.modules.mes.entity.MesMachineSets;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: mes_machine_sets
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
public interface IMesMachineSetsService extends IService<MesMachineSets> {

	public List<MesMachineSets> selectByMainId(String mainId);
}
