package com.liuliu.service.impl;

import com.liuliu.dao.ResumeDao;
import com.liuliu.pojo.Resume;
import com.liuliu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: liulei
 * @Time: 2021/1/16 8:32
 * @Description
 */

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeDao resumeDao;

    @Override
    public Integer findIsOpenResume(Long id) {
        Resume resumeQ = new Resume();
        resumeQ.setId(id);
        Example<Resume> example = Example.of(resumeQ);
        Optional<Resume> resume = resumeDao.findOne(example);
        return resume.get().getIsOpenResume();
    }
}
