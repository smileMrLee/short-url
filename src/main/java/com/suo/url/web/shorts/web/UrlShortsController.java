package com.suo.url.web.shorts.web;

import com.suo.url.web.shorts.Response;
import com.suo.url.web.shorts.util.UrlShortsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author changle
 *         time 18/12/6.
 *         description to do
 */
@Controller
@RequestMapping("/url")
public class UrlShortsController {
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/shorts/tool")
    public String root(){
        return "url_shorts";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/shorts/generate")
    @ResponseBody
    public Response<String> generateShortUrl(String sourceUrl, String domain, String bakUrl, String isOpenBak){
        String goalUrl = UrlShortsUtil.shortenCodeUrl(sourceUrl, 7);
        redisTemplate.opsForValue().set("shorts:open", isOpenBak);
        redisTemplate.opsForValue().set("shorts:url:" + goalUrl, sourceUrl);
        redisTemplate.opsForValue().set("shorts:url:bak:" + goalUrl, bakUrl);
        return Response.ok("http://" + domain + "/s/"+goalUrl);
    }
}
