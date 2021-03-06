package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.MenuItemRequest;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.entity.MenuItem;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.NotExistException;
import com.scut.indoorLocation.exception.NotStoreOwnerException;
import com.scut.indoorLocation.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2019/12/31 10:25
 */
@Api(value = "菜单项接口", tags = "菜单项接口")
@RestController
@RequestMapping("/api/menu")
public class MenuItemController {

    @Resource
    private MenuService menuService;

    @ApiOperation("创建菜单项")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> queryStorePaging(@RequestBody MenuItemRequest menuItemRequest) {
        try {
            menuService.createMenuItem(menuItemRequest);
            return ResponseEntity.ok(new SuccessResponse(true, "创建成功"));
        } catch (CreateException e) {
            return ResponseEntity.ok(new SuccessResponse(false, "创建失败"));
        }
    }


    @ApiOperation("分页方式查询店铺的菜单项")
    @RequestMapping(value = "/query/{storeId}/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<IPage<MenuItem>> queryStorePaging(@ApiParam(value = "商铺ID") @PathVariable String storeId,
                                                            @ApiParam(value = "页号") @PathVariable Long pageNo,
                                                            @ApiParam(value = "页大小") @PathVariable Long pageSize) {
        IPage<MenuItem> storesPage = menuService.queryMenuItemByPage(storeId, pageNo, pageSize);
        return ResponseEntity.ok(storesPage);
    }

    /**
     * Add by hxy 2020/02/11
     */
    @ApiOperation("修改菜单信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> modifyMenuItem(@RequestBody MenuItemRequest param) {
        try {
            menuService.modifyMenuInfo(param);
            return ResponseEntity.ok(new SuccessResponse(true, "菜单项信息修改成功"));
        } catch (NotStoreOwnerException e) {
            return ResponseEntity.ok(new SuccessResponse(false, "用户非店铺主人，修改失败"));
        }
        catch(NotExistException ex){
            return ResponseEntity.ok(new SuccessResponse(false, "菜单项不存在，修改失败"));
        }
    }

    /**
     * Add by hxy 2020/01/10
     */

    @ApiOperation("删除菜单项")
    @RequestMapping(value = "/{menuId}", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessResponse> deleteMenuItem(@ApiParam (value="menuId")@PathVariable String menuId) {
        try {
            menuService.deleteMenuItem(menuId);
            return ResponseEntity.ok(new SuccessResponse(true, "菜单项删除成功"));
        } catch (NotStoreOwnerException e) {
            return ResponseEntity.ok(new SuccessResponse(false, "非店铺拥有者，删除失败"));
        } catch (NotExistException ex) {
            return ResponseEntity.ok(new SuccessResponse(false, "菜单项不存在，删除失败"));
        }

    }


}
