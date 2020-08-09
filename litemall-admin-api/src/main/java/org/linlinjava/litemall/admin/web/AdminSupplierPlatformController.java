package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallSupplierPlatform;
import org.linlinjava.litemall.db.service.LitemallSupplierPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/supplierplatform")
@Validated
public class AdminSupplierPlatformController {
    private final Log logger = LogFactory.getLog(AdminSupplierPlatformController.class);

    @Autowired
    private LitemallSupplierPlatformService supplierPlatformService;

    @RequiresPermissions("admin:supplierplatform:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "接入平台管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallSupplierPlatform> platformList = supplierPlatformService.querySelective(id, name, page, limit, sort, order);
        return ResponseUtil.okList(platformList);
    }

    private Object validate(LitemallSupplierPlatform brand) {
        String name = brand.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        String desc = brand.getDesc();
        if (StringUtils.isEmpty(desc)) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @RequiresPermissions("admin:supplierplatform:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "接入平台管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallSupplierPlatform brand) {
        Object error = validate(brand);
        if (error != null) {
            return error;
        }
        supplierPlatformService.add(brand);
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:supplierplatform:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "接入平台管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallSupplierPlatform brand = supplierPlatformService.findById(id);
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:supplierplatform:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "接入平台管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallSupplierPlatform brand) {
        Object error = validate(brand);
        if (error != null) {
            return error;
        }
        if (supplierPlatformService.updateById(brand) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:supplierplatform:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "接入平台管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallSupplierPlatform brand) {
        Integer id = brand.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        supplierPlatformService.deleteById(id);
        return ResponseUtil.ok();
    }

}
