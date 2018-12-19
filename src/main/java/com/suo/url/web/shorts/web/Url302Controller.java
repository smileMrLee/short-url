package com.suo.url.web.shorts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author changle
 *         time 18/12/6.
 *         description to do
 */
@RestController
public class Url302Controller {
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/s/{shortId}", method = RequestMethod.GET)
    public void redirect(@PathVariable String shortId, HttpServletResponse response) throws IOException {
        String isOpenBak = redisTemplate.opsForValue().get("shorts:open").toString();
        String realUrl = null;
        if ("1".equals(isOpenBak)){
            realUrl = redisTemplate.opsForValue().get("shorts:url:bak:" + shortId).toString();
        }else{
            realUrl = redisTemplate.opsForValue().get("shorts:url:" + shortId).toString();
        }

        response.sendRedirect(realUrl);
    }
}
