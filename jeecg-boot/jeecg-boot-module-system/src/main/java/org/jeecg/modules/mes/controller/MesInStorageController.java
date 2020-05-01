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
import org.jeecg.modules.mes.entity.MesInStorage;
import org.jeecg.modules.mes.service.IMesInStorageService;

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
 * @Description: mes_in_storage
 * @Author: jeecg-boot
 * @Date:   2020-04-29
 * @Version: V1.0
 */
@Api(tags="mes_in_storage")
@RestController
@RequestMapping("/mes/mesInStorage")
@Slf4j
public class MesInStorageController extends JeecgController<MesInStorage, IMesInStorageService> {
	@Autowired
	private IMesInStorageService mesInStorageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mesInStorage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "mes_in_storage-分页列表查询")
	@ApiOperation(value="mes_in_storage-分页列表查询", notes="mes_in_storage-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MesInStorage mesInStorage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MesInStorage> queryWrapper = QueryGenerator.initQueryWrapper(mesInStorage, req.getParameterMap());
		Page<MesInStorage> page = new Page<MesInStorage>(pageNo, pageSize);
		IPage<MesInStorage> pageList = mesInStorageService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mesInStorage
	 * @return
	 */
	@AutoLog(value = "mes_in_storage-添加")
	@ApiOperation(value="mes_in_storage-添加", notes="mes_in_storage-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MesInStorage mesInStorage) {
		mesInStorageService.save(mesInStorage);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mesInStorage
	 * @return
	 */
	@AutoLog(value = "mes_in_storage-编辑")
	@ApiOperation(value="mes_in_storage-编辑", notes="mes_in_storage-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MesInStorage mesInStorage) {
		mesInStorageService.updateById(mesInStorage);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "mes_in_storage-通过id删除")
	@ApiOperation(value="mes_in_storage-通过id删除", notes="mes_in_storage-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mesInStorageService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "mes_in_storage-批量删除")
	@ApiOperation(value="mes_in_storage-批量删除", notes="mes_in_storage-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mesInStorageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "mes_in_storage-通过id查询")
	@ApiOperation(value="mes_in_storage-通过id查询", notes="mes_in_storage-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MesInStorage mesInStorage = mesInStorageService.getById(id);
		if(mesInStorage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mesInStorage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mesInStorage
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MesInStorage mesInStorage) {
        return super.exportXls(request, mesInStorage, MesInStorage.class, "mes_in_storage");
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
        return super.importExcel(request, response, MesInStorage.class);
    }

}
