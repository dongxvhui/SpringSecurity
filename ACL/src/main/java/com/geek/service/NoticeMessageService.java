package com.geek.service;

import com.geek.mapper.NoticeMessageMapper;
import com.geek.model.NoticeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeMessageService {

    @Autowired
    NoticeMessageMapper noticeMessageMapper;

    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<NoticeMessage> findAll(){
        List<NoticeMessage> all = noticeMessageMapper.findAll();
        return all;
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public NoticeMessage findById(Integer id){
        return noticeMessageMapper.findById(id);
    }

    @PreAuthorize("hasPermission(#noticeMessage, 'CREATE')")
    public NoticeMessage save(NoticeMessage noticeMessage){
        noticeMessageMapper.save(noticeMessage);
        return noticeMessage;
    }

    @PreAuthorize("hasPermission(#noticeMessage, 'WRITE')")
    public void update(NoticeMessage noticeMessage){
        noticeMessageMapper.update(noticeMessage);
    }
}
