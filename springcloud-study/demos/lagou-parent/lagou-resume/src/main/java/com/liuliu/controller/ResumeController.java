package com.liuliu.controller;

import com.liuliu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liulei
 * @Time: 2021/1/16 8:37
 * @Description
 */

@RestController
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @RequestMapping(method = RequestMethod.GET, path = "/resume/{id}")
    public Integer isOpenResume(@PathVariable Long id){
        return resumeService.findIsOpenResume(id);
    }
}
