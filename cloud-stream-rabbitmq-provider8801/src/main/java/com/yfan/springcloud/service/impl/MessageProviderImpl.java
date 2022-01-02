package com.yfan.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.yfan.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/*
 * @author YFAN
 * @date 2022/1/2/002
 */
@EnableBinding(Source.class) // 定义消息推送管道
@Service
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    @Autowired
    private MessageChannel output;// 消息发送管道

    @Override
    public String send() {
        String uuid = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(uuid).build());
        log.info("**********send:{}",uuid);
        return null;
    }
}
