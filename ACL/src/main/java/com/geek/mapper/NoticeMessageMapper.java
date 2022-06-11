package com.geek.mapper;

import com.geek.model.NoticeMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMessageMapper {
    List<NoticeMessage> findAll();
    NoticeMessage findById(Integer id);
    void save(NoticeMessage noticeMessage);
    void update(NoticeMessage noticeMessage);
}
