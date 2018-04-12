package com.fzu.edu.controller.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bianbian on 2018/2/9.
 */
@Controller
@RequestMapping(value = "/purview",produces = {"application/json;charset=UTF-8"})
public class PurviewController {

    private Logger log = Logger.getLogger(PurviewController.class);
  /*  @Resource
    private PurviewService purviewService;

    @RequestMapping("/showAll")
    @ResponseBody
    public String showAll(HttpServletRequest request, Model model){

        List<Purview> list = purviewService.getAll();
        model.addAttribute("list",list);
        log.info("获得数据"+list);
        return "showAll";
    }*/



}
