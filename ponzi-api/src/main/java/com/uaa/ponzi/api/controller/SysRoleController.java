package com.uaa.ponzi.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uaa.ponzi.base.controller.BaseController;
import com.uaa.ponzi.bo.SysPermissionBo;
import com.uaa.ponzi.bo.SysRolePermissionBo;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.bo.SysTreeRoleMenuPermissionBo;
import com.uaa.ponzi.common.util.RestResponseUtil;
import com.uaa.ponzi.dto.SysRoleNewDto;
import com.uaa.ponzi.pojo.SysRole;
import com.uaa.ponzi.pojo.SysRoleMenu;
import com.uaa.ponzi.service.SysRoleMenuService;
import com.uaa.ponzi.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sys_role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取所有的角色
     * @return
     */
    @PostMapping("/all_roles")
    @RequiresPermissions("role:link:list")
    public RestResponseUtil allRoles() {
        List<SysRole> allRoles = sysRoleService.allRoles();
        return RestResponseUtil.ok(allRoles);
    }

    /**
     * 添加新角色
     * @param roleNewDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("role:link:add:save")
    public RestResponseUtil add(
            @Valid SysRoleNewDto roleNewDto
            , BindingResult bindingResult) {

        super.valid(bindingResult);

        if (sysRoleService.checkExistsRoleCode(roleNewDto.getRoleCode())) {
            return RestResponseUtil.error("角色编码已存在,请填写其他");
        }

        if (sysRoleService.checkExistsRoleName(roleNewDto.getRoleName())) {
            return RestResponseUtil.error("角色名称已存在,请填写其他");
        }

        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleNewDto, sysRole);
        boolean result = sysRoleService.save(sysRole);

        return result ? RestResponseUtil.ok("添加成功") : RestResponseUtil.error("添加失败");
    }

    /**
     * 根据角色id查找对应的角色和所有权限
     * @param roleId 角色id
     * @return
     */
    @PostMapping("/find_rolepermission/{roleId}")
    public RestResponseUtil findRolePermission(@PathVariable("roleId") String roleId) {
        SysRolePermissionBo sysRolePermissionBO = sysRoleService.findRolePermissionByRoleId(roleId);
        return RestResponseUtil.ok(sysRolePermissionBO);
    }

    /**
     * 根据角色查找拥有的菜单
     * @param roleId
     * @return
     */
    @PostMapping("/find_rolemenu/{roleId}")
    public RestResponseUtil findRoleMenu(@PathVariable("roleId") String roleId) {
        List<SysTreeMenuBo> menuBoList = sysRoleService.treeRoleMenuByRoleId(roleId);
        JSONArray jsonArray = new JSONArray();
        for (SysTreeMenuBo b : menuBoList) {
            jsonArray.add(this.transTopTreeNode(b));
        }
        return RestResponseUtil.ok(jsonArray);
    }

    /**
     * 父节点
     * @param menuBo
     */
    public static JSONObject transTopTreeNode(SysTreeMenuBo menuBo) {
        JSONObject jsonObject = new JSONObject();
        if ("#".equals(menuBo.getPid())) {
            jsonObject.put("path", "/" + menuBo.getRoutePath());
            jsonObject.put("title", menuBo.getMenuName());
            jsonObject.put("name", menuBo.getRouteName());
            jsonObject.put("icon", menuBo.getMenuIcon());
            jsonObject.put("component", "Main");
        }
        JSONArray children = new JSONArray();
        if (!CollectionUtils.isEmpty(menuBo.getChildren())) {
            for (SysTreeMenuBo bo : menuBo.getChildren()) {
                children.add(transMiddleTreeNode(bo));
            }
        } else {
            children.add(transTreeEndNode(menuBo));
        }
        jsonObject.put("children", children);
        return jsonObject;
    }

    /**
     * 中间节点
     * @param menuBo
     */
    private static JSONObject transMiddleTreeNode(SysTreeMenuBo menuBo) {
        JSONObject child = transTreeEndNode(menuBo);
        if (!CollectionUtils.isEmpty(menuBo.getChildren())) {
            JSONArray children = new JSONArray();
            for (SysTreeMenuBo bo : menuBo.getChildren()) {
                children.add(transMiddleTreeNode(bo));
            }
            child.put("children", children);
        }
        return child;
    }

    /**
     * 把菜单转为树状的子结点
     * @param menuBo
     * @return
     */
    private static JSONObject transTreeEndNode(SysTreeMenuBo menuBo) {
        JSONObject child = new JSONObject();
        child.put("path", menuBo.getRoutePath());
        child.put("name", menuBo.getRouteName());
        child.put("meta", menuBo.getMenuName());
        child.put("component", menuBo.getRouteComponent());
        return child;
    }

    /**
     * 查找角色下拥有的菜单权限
     * @param roleId 角色id
     * @return
     */
    @PostMapping("/tree_all_role_menu_permission")
    @RequiresPermissions("role:link:set_menu_permission")
    public RestResponseUtil treeAllRoleMenuPermission(String roleId) {
        List<SysTreeRoleMenuPermissionBo> list = sysRoleService.treeRoleMenuPermissionByRoleId(roleId);
        List<SysRoleMenu> roleMenuList = sysRoleMenuService.findByRoleId(roleId);
        if (!CollectionUtils.isEmpty(list) && !CollectionUtils.isEmpty(roleMenuList)) {
            Map<String, String> menuIdMap = new HashMap<>(roleMenuList.size());
            for (SysRoleMenu roleMenu : roleMenuList) {
                menuIdMap.put(roleMenu.getSysMenuId(), null);
            }

            for (SysTreeRoleMenuPermissionBo bo : list) {
                this.judgeCheckAllAndIndeterminate(bo, menuIdMap);
            }
        }

        return RestResponseUtil.ok(list);
    }

    /**
     *
     * @param bo
     * @param menuIdMap
     * @return 0(checkAll=true,indeterminate=false), 1(checkAll=false,indeterminate=true), 2(checkAll=false,indeterminate=false)
     */
    private int judgeCheckAllAndIndeterminate(SysTreeRoleMenuPermissionBo bo, Map<String, String> menuIdMap) {
        int flag = 0;
        List<SysTreeRoleMenuPermissionBo> rmpbList = bo.getChildren();
        if (menuIdMap.containsKey(bo.getId())) {
            if (!CollectionUtils.isEmpty(rmpbList)) {
                bo.setCheckAll(false);
                bo.setIndeterminate(true);

                int checkAllTrue = 0;
                for (SysTreeRoleMenuPermissionBo b : rmpbList) {
                    flag = this.judgeCheckAllAndIndeterminate(b, menuIdMap);
                    if (flag == 0) {
                        checkAllTrue++;
                    }
                }
                if (checkAllTrue == rmpbList.size()) {
                    bo.setIndeterminate(false);
                    bo.setCheckAll(true);
                    flag = 0;
                }
            } else { // 该菜单为最后菜单结点,
               flag = this.judgeCheckAllAndIndeterminate(bo);
            }
        } else {
            bo.setIndeterminate(false);
            bo.setCheckAll(false);
            flag = 2;
        }
        return flag;
    }

    /**
     *
     * @param bo
     * @return 0(checkAll=true,indeterminate=false), 1(checkAll=false,indeterminate=true)
     */
    private int judgeCheckAllAndIndeterminate(SysTreeRoleMenuPermissionBo bo) {
        int flag = 0;
        List<SysPermissionBo> checkedPermissionList = bo.getCheckedPermissions();
        List<SysPermissionBo> permissionList = bo.getPermissions();
        if (CollectionUtils.isEmpty(checkedPermissionList) && CollectionUtils.isEmpty(permissionList)) {
            bo.setCheckAll(true);
            bo.setIndeterminate(false);
        } else if (!CollectionUtils.isEmpty(checkedPermissionList) && !CollectionUtils.isEmpty(permissionList) && checkedPermissionList.size() != permissionList.size()) {
            bo.setCheckAll(false);
            bo.setIndeterminate(true);
            flag = 1;
        } else if (!CollectionUtils.isEmpty(checkedPermissionList) && !CollectionUtils.isEmpty(permissionList) && checkedPermissionList.size() == permissionList.size()) {
            bo.setCheckAll(true);
            bo.setIndeterminate(false);
        }
        return flag;
    }

    /**
     * 添加或修改角色的菜单权限
     * @param roleId 角色id
     * @param menuIds 菜单id，以','连接
     * @param permissionIds 权限id，以','连接
     * @return
     */
    @PostMapping("/save_or_update_rolemenupermission")
    @RequiresPermissions("role:link:save_set")
    public RestResponseUtil saveOrUpdateRoleMenuPermission(String roleId, String menuIds, String permissionIds) {
        List<String> menuIdList = null;
        List<String> permissionIdList = null;

        if (!StringUtils.isEmpty(menuIds)) {
            menuIdList = Arrays.asList(menuIds.split(","));
        }

        if (!StringUtils.isEmpty(permissionIds)) {
            permissionIdList = Arrays.asList(permissionIds.split(","));
        }

        sysRoleService.saveOrUpdateRoleMenuPermission(roleId, menuIdList, permissionIdList);
        return RestResponseUtil.ok();
    }

    /**
     * 删除角色
     * @param roleId 角色id
     * @return
     */
    @PostMapping("/delete_role")
    @RequiresPermissions("role:link:delete")
    public RestResponseUtil deleteRole(String roleId) {
        sysRoleService.deleteRole(roleId);
        return RestResponseUtil.ok();
    }


    @PostMapping("/check_exists_rolecode")
    public RestResponseUtil checkExistsRoleCode(String roleCode) {
        if (StringUtils.isEmpty(roleCode)) {
            return RestResponseUtil.error("请填写角色编码");
        }

        if (sysRoleService.checkExistsRoleCode(roleCode)) {
            return RestResponseUtil.error("角色编码已存在,请填写其他");
        }

        return RestResponseUtil.ok();
    }

    @PostMapping("/check_exists_rolename")
    public RestResponseUtil checkExistsRoleName(String roleName) {
        if (StringUtils.isEmpty(roleName)) {
            return RestResponseUtil.error("请填写角色名称");
        }

        if (sysRoleService.checkExistsRoleName(roleName)) {
            return RestResponseUtil.error("角色名称已存在,请填写其他");
        }

        return RestResponseUtil.ok();
    }


}
