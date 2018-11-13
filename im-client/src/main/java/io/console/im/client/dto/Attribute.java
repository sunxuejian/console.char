package io.console.im.client.dto;

import io.netty.util.AttributeKey;

/**
 * @author xuejian.sun
 * @date 2018/11/12 18:41
 */
public interface Attribute {
    AttributeKey<String> user = AttributeKey.newInstance("user");
}
