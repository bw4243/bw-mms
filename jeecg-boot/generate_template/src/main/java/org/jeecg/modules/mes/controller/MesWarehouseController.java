package org.jeecg.modules.mes.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.mes.entity.MesWarehouse;
import org.jeecg.modules.mes.service.IMesWarehouseService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: mes_warehouse
 * @Author: jeecg-boot
 * @Date:   2020-04-28
 * @Version: V1.0
 */
@Api(tags="mes_warehouse")
@RestController
@RequestMapping("/mes/mesWarehouse")
@Slf4j
public class MesWarehouseController extends JeecgController<MesWarehouse, IMesWarehouseService> {
	@Autowired
	private IMesWarehouseService mesWarehouseService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mesWarehouse
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "mes_warehouse-分页列表查询")
	@ApiOperation(value="mes_warehouse-分页列表查询", notes="mes_warehouse-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MesWarehouse mesWarehouse,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MesWarehouse> queryWrapper = QueryGenerator.initQueryWrapper(mesWarehouse, req.getParameterMap());
		Page<MesWarehouse> page = new Page<MesWarehouse>(pageNo, pageSize);
		IPage<MesWarehouse> pageList = mesWarehouseService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mesWarehouse
	 * @return
	 */
	@AutoLog(value = "mes_warehouse-添加")
	@ApiOperation(value="mes_warehouse-添加", notes="mes_warehouse-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MesWarehouse mesWarehouse) {
		mesWarehouseService.save(mesWarehouse);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mesWarehouse
	 * @return
	 */
	@AutoLog(value = "mes_warehouse-编辑")
	@ApiOperation(value="mes_warehouse-编辑", notes="mes_warehouse-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MesWarehouse mesWarehouse) {
		mesWarehouseService.updateById(mesWarehouse);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "mes_warehouse-通过id删除")
	@ApiOperation(value="mes_warehouse-通过id删除", notes="mes_warehouse-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mesWarehouseService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "mes_warehouse-批量删除")
	@ApiOperation(value="mes_warehouse-批量删除", notes="mes_warehouse-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mesWarehouseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "mes_warehouse-通过id查询")
	@ApiOperation(value="mes_warehouse-通过id查询", notes="mes_warehouse-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MesWarehouse mesWarehouse = mesWarehouseService.getById(id);
		if(mesWarehouse==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mesWarehouse);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mesWarehouse
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MesWarehouse mesWarehouse) {
        return super.exportXls(request, mesWarehouse, MesWarehouse.class, "mes_warehouse");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, MesWarehouse.class);
    }

}
