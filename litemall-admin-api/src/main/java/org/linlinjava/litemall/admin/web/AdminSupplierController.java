package org.linlinjava.litemall.admin.web;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallSupplier;
import org.linlinjava.litemall.db.domain.LitemallSupplierPlatform;
import org.linlinjava.litemall.db.service.LitemallSupplierPlatformService;
import org.linlinjava.litemall.db.service.LitemallSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/supplier")
@Validated
public class AdminSupplierController {
    private final Log logger = LogFactory.getLog(AdminSupplierController.class);

    @Autowired
    private LitemallSupplierService supplierService;

    @Autowired
    private LitemallSupplierPlatformService supplierPlatformService;

    @RequiresPermissions("admin:supplier:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {

        List<LitemallSupplierPlatform> platformList = supplierPlatformService.all();

        Map<Integer, LitemallSupplierPlatform> platformMap = platformList.stream().collect(Collectors.toMap(LitemallSupplierPlatform::getId, platform->platform));
        List<LitemallSupplier> list = supplierService.querySelective(id, name, page, limit, sort, order);

        List<Object> result = new ArrayList<>(list.size());
        list.forEach(item -> {
            LitemallSupplierPlatform platform = platformMap.get(item.getSupplierPlatformId());
            ObjectNode ret = (ObjectNode)JacksonUtil.toNode((JacksonUtil.toJson(item)));

            if (null != platform ) {
                ret.put("platformName", platform.getName());

            } else {
               ret.put("platformName","未知");
            }
            result.add(ret);
        });
        logger.debug("list result: "+ JacksonUtil.toJson(result));
        return ResponseUtil.okList(result);
    }

    private Object validate(LitemallSupplier brand) {
        String name = brand.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        String desc = brand.getDesc();
        if (StringUtils.isEmpty(desc)) {
            return ResponseUtil.badArgument();
        }

        Integer supplierPlatformId = brand.getSupplierPlatformId();
        if (supplierPlatformId == null) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @RequiresPermissions("admin:supplier:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallSupplier supplier) {
        Object error = validate(supplier);
        if (error != null) {
            return error;
        }
        supplierService.add(supplier);
        return ResponseUtil.ok(supplier);
    }

    @RequiresPermissions("admin:supplier:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallSupplier brand = supplierService.findById(id);
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:supplier:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallSupplier supplier) {
        Object error = validate(supplier);
        if (error != null) {
            return error;
        }
        if (supplierService.updateById(supplier) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(supplier);
    }

    @RequiresPermissions("admin:supplier:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallSupplier brand) {
        Integer id = brand.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        supplierService.deleteById(id);
        return ResponseUtil.ok();
    }

}
