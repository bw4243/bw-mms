package org.jeecg.modules.mes.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.mes.entity.MesMachineSets;
import org.jeecg.modules.mes.entity.MesWorkShop;
import org.jeecg.modules.mes.vo.MesWorkShopPage;
import org.jeecg.modules.mes.service.IMesWorkShopService;
import org.jeecg.modules.mes.service.IMesMachineSetsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: MES车间表
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
@Api(tags="MES车间表")
@RestController
@RequestMapping("/mes/mesWorkShop")
@Slf4j
public class MesWorkShopController {
	@Autowired
	private IMesWorkShopService mesWorkShopService;
	@Autowired
	private IMesMachineSetsService mesMachineSetsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mesWorkShop
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "MES车间表-分页列表查询")
	@ApiOperation(value="MES车间表-分页列表查询", notes="MES车间表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MesWorkShop mesWorkShop,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MesWorkShop> queryWrapper = QueryGenerator.initQueryWrapper(mesWorkShop, req.getParameterMap());
		Page<MesWorkShop> page = new Page<MesWorkShop>(pageNo, pageSize);
		IPage<MesWorkShop> pageList = mesWorkShopService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mesWorkShopPage
	 * @return
	 */
	@AutoLog(value = "MES车间表-添加")
	@ApiOperation(value="MES车间表-添加", notes="MES车间表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MesWorkShopPage mesWorkShopPage) {
		MesWorkShop mesWorkShop = new MesWorkShop();
		BeanUtils.copyProperties(mesWorkShopPage, mesWorkShop);
		mesWorkShopService.saveMain(mesWorkShop, mesWorkShopPage.getMesMachineSetsList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mesWorkShopPage
	 * @return
	 */
	@AutoLog(value = "MES车间表-编辑")
	@ApiOperation(value="MES车间表-编辑", notes="MES车间表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MesWorkShopPage mesWorkShopPage) {
		MesWorkShop mesWorkShop = new MesWorkShop();
		BeanUtils.copyProperties(mesWorkShopPage, mesWorkShop);
		MesWorkShop mesWorkShopEntity = mesWorkShopService.getById(mesWorkShop.getId());
		if(mesWorkShopEntity==null) {
			return Result.error("未找到对应数据");
		}
		mesWorkShopService.updateMain(mesWorkShop, mesWorkShopPage.getMesMachineSetsList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "MES车间表-通过id删除")
	@ApiOperation(value="MES车间表-通过id删除", notes="MES车间表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mesWorkShopService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "MES车间表-批量删除")
	@ApiOperation(value="MES车间表-批量删除", notes="MES车间表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mesWorkShopService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "MES车间表-通过id查询")
	@ApiOperation(value="MES车间表-通过id查询", notes="MES车间表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MesWorkShop mesWorkShop = mesWorkShopService.getById(id);
		if(mesWorkShop==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mesWorkShop);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "mes_machine_sets集合-通过id查询")
	@ApiOperation(value="mes_machine_sets集合-通过id查询", notes="mes_machine_sets-通过id查询")
	@GetMapping(value = "/queryMesMachineSetsByMainId")
	public Result<?> queryMesMachineSetsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<MesMachineSets> mesMachineSetsList = mesMachineSetsService.selectByMainId(id);
		return Result.ok(mesMachineSetsList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mesWorkShop
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MesWorkShop mesWorkShop) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<MesWorkShop> queryWrapper = QueryGenerator.initQueryWrapper(mesWorkShop, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<MesWorkShop> queryList = mesWorkShopService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<MesWorkShop> mesWorkShopList = new ArrayList<MesWorkShop>();
      if(oConvertUtils.isEmpty(selections)) {
          mesWorkShopList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          mesWorkShopList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<MesWorkShopPage> pageList = new ArrayList<MesWorkShopPage>();
      for (MesWorkShop main : mesWorkShopList) {
          MesWorkShopPage vo = new MesWorkShopPage();
          BeanUtils.copyProperties(main, vo);
          List<MesMachineSets> mesMachineSetsList = mesMachineSetsService.selectByMainId(main.getId());
          vo.setMesMachineSetsList(mesMachineSetsList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "MES车间表列表");
      mv.addObject(NormalExcelConstants.CLASS, MesWorkShopPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("MES车间表数据", "导出人:"+sysUser.getRealname(), "MES车间表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<MesWorkShopPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MesWorkShopPage.class, params);
              for (MesWorkShopPage page : list) {
                  MesWorkShop po = new MesWorkShop();
                  BeanUtils.copyProperties(page, po);
                  mesWorkShopService.saveMain(po, page.getMesMachineSetsList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
    }

}
