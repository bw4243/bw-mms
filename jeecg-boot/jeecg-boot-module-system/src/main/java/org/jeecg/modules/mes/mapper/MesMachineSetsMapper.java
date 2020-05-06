package org.jeecg.modules.mes.mapper;

import java.util.List;
import org.jeecg.modules.mes.entity.MesMachineSets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: mes_machine_sets
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
public interface MesMachineSetsMapper extends BaseMapper<MesMachineSets> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MesMachineSets> selectByMainId(@Param("mainId") String mainId);
}
