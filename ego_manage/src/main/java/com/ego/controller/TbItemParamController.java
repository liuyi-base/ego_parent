package com.ego.controller;


import com.ego.service.TbItemParamService;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TbItemParamController {
    @Autowired
    private TbItemParamService tbItemParamService;


    @RequestMapping("/item/param/list")
    public EasyUIDatagrid showItemParam(int page, int rows) {
        return tbItemParamService.showItemParam(page, rows);
    }

    @RequestMapping("/item/param/query/itemcatid/{id}")
    public EgoResult showItemParamByCatId(@PathVariable Long id) {
        return tbItemParamService.showItemParamByCatid(id);
    }

    @RequestMapping("/item/param/save/{catId}")
    public EgoResult insert(TbItemParam tbItemParam, @PathVariable Long catId) {
        tbItemParam.setItemCatId(catId);
        return tbItemParamService.insert(tbItemParam);
    }

    @RequestMapping("/item/param/delete")
    public EgoResult delete(Long[] ids) {
        return tbItemParamService.delete(ids);
    }

}
