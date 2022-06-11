package com.geek;

import com.geek.model.NoticeMessage;
import com.geek.service.NoticeMessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AclApplicationTests {

    @Autowired
    NoticeMessageService noticeMessageService;
    @Test
    @WithMockUser(username = "manager")
    void test01() {
        List<NoticeMessage> all = noticeMessageService.findAll();
        assertEquals(0,all.size());
    }

    @Autowired
    JdbcMutableAclService jdbcMutableAclService;
    @Test
    @WithMockUser(username = "geek")
    @Transactional
    @Rollback(value = false)
    void test02(){
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(NoticeMessage.class, 1);
        Permission p = BasePermission.READ;
        MutableAcl acl = jdbcMutableAclService.createAcl(objectIdentity);
        acl.insertAce(acl.getEntries().size(),p,new PrincipalSid("hr"),true);
        jdbcMutableAclService.updateAcl(acl);
    }

    @Test
    @WithMockUser(username = "hr")
    void test03(){
        List<NoticeMessage> all = noticeMessageService.findAll();
        assertNotNull(all);
        assertEquals(1, all.size());
        assertEquals(1,all.get(0).getId());
        NoticeMessage byId = noticeMessageService.findById(1);
        assertNotNull(byId);
        assertEquals(1,byId.getId());
    }


    @Test
    @WithMockUser(username = "geek")
    @Transactional
    @Rollback(value = false)
    void test04(){
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(NoticeMessage.class, 1);
        Permission p = BasePermission.WRITE;
        MutableAcl acl = (MutableAcl) jdbcMutableAclService.readAclById(objectIdentity);
        acl.insertAce(acl.getEntries().size(),p,new PrincipalSid("hr"),true);
        jdbcMutableAclService.updateAcl(acl);
    }

    @Test
    @WithMockUser(username = "hr")
    void test05(){
        NoticeMessage msg = noticeMessageService.findById(1);
        assertNotNull(msg);
        assertEquals(1,msg.getId());
        msg.setContent("geek-1111");
        noticeMessageService.update(msg);
        msg = noticeMessageService.findById(1);
        assertNotNull(msg);
        assertEquals("geek-1111",msg.getContent());
    }

    @Test
    @WithMockUser(username = "geek")
    @Transactional
    @Rollback(value = false)
    void test06(){
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(NoticeMessage.class, 99);
        Permission p = BasePermission.CREATE;
        MutableAcl acl = jdbcMutableAclService.createAcl(objectIdentity);
        acl.insertAce(acl.getEntries().size(),p,new PrincipalSid("manager"),true);
        jdbcMutableAclService.updateAcl(acl);
    }

    @Test
    @WithMockUser(username = "manager")
    void test07(){
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setId(99);
        noticeMessage.setContent("999");
        noticeMessageService.save(noticeMessage);
    }

}
