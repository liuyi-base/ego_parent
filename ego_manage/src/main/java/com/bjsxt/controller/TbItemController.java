package com.bjsxt.controller;


import com.bjsxt.service.TbItemCatService;
import com.bjsxt.service.TbItemService;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TbItemController {

    @Autowired
    private TbItemService tbItemService;

    @Autowired
    private TbItemCatService tbItemCatService;

    @RequestMapping("/item/list")
    public EasyUIDatagrid showItem(int page, int rows) {
        return tbItemService.showItem(page, rows);
    }

    /*** 删除 * @param ids * @return */
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public EgoResult delete(long[] ids) {
        return tbItemService.updateStatus(ids, 3);
    }

    /*** 上架 * @param ids * @return */
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public EgoResult reshelf(long[] ids) {
        return tbItemService.updateStatus(ids, 1);
    }

    /*** 下架 * @param ids * @return */
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public EgoResult instock(long[] ids) {
        return tbItemService.updateStatus(ids, 2);
    }

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITree> showTree(@RequestParam(defaultValue = "0") Long pid) {
        return tbItemCatService.showTree(pid);
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public EgoResult insert(TbItem item, String desc, String itemParams) {
        return tbItemService.insert(item, desc, itemParams);
    }

    @RequestMapping("/rest/item/update")
    @ResponseBody
    public EgoResult update(TbItem item, String desc, String itemParams, long itemParamId) {
        return tbItemService.update(item, desc, itemParams, itemParamId);
    }
}
